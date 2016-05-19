/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import path.StoragePath;
import upload.DropboxUpload;

/**
 *
 * @author Saurabh
 */
public class FileUpdate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fileName = request.getParameter("fileName");
        //String key = request.getParameter("key");
        HttpSession session = request.getSession();
        Connection con;
        Statement st;
        ResultSet rs;
        //Storage path
        String path = StoragePath.getPath();

        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");
        if (patient != null && user == null && admin == null) {

            File patientFolder = new File(path + File.separator + "Encrypt" + File.separator + patient);
            if (!patientFolder.exists()) {
                patientFolder.mkdir();
            }
            String policyFileName = "policy" + fileName + ".txt";
            //Remove files from local Storage
            File file = new File(patientFolder.getAbsolutePath() + File.separator + fileName);
            File policyFile = new File(patientFolder.getAbsolutePath() + File.separator + policyFileName);
//            String uKey = getKey(patient);
            policyFile.delete();
            file.delete();

            //Remove files from cloud...
            DropboxUpload delete = new DropboxUpload();
            delete.removeFile(StoragePath.getDropboxDir() + patient, fileName);
            delete.removeFile(StoragePath.getDropboxDir() + patient, policyFileName);

            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "delete from filedetails where patientID='" + patient + "' and fileName='" + fileName + "'";
                st.executeUpdate(query);

                //Update log 
                query = "select * from log";
                int noOfFile = 0;
                rs = st.executeQuery(query);
                if (rs.next()) {
                    noOfFile = rs.getInt("noOfFile") - 1;
                }
                query = "UPDATE log SET noOfFile='" + noOfFile + "'";
                st.executeUpdate(query);
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("PatientUpdatePHR.jsp");
            }
            response.sendRedirect("FileUpdate.jsp");
        } else {
            session.setAttribute("msg", "You Are Not allowed to upload Login First");
            response.sendRedirect("loginError.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
