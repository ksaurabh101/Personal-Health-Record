/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import path.StoragePath;
import upload.DropboxUpload;

/**
 *
 * @author Saurabh
 */
public class PolicyChange extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Storage path
        String path = StoragePath.getPath();

        HttpSession session = request.getSession();
        String phrName = (String) session.getAttribute("fileName");
        String key = (String) session.getAttribute("uKey");

        session.removeAttribute("fileName");
        session.removeAttribute("uKey");

        String permission = request.getParameter("permission");

        String[] professio = request.getParameterValues("profession");
        String[] specialit = request.getParameterValues("speciality");
        String[] organizatio = request.getParameterValues("organization");

        ArrayList<String> profession = new ArrayList<>();
        ArrayList<String> speciality = new ArrayList<>();
        ArrayList<String> organization = new ArrayList<>();
        for (String s : professio) {
            profession.add(s);
        }
        for (String s : specialit) {
            speciality.add(s);
        }
        for (String s : organizatio) {
            organization.add(s);
        }

        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String name = (String) session.getAttribute("loginName");
        String admin = (String) session.getAttribute("admin");
        if (patient != null && user == null && admin == null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "";
                File patientFolder = new File(path + File.separator + "Encrypt" + File.separator + patient);
                if (!patientFolder.exists()) {
                    patientFolder.mkdir();
                }
                //Remove file from local storage
                String policyFileName = "policy" + phrName + ".txt";
                File policyFile = new File(patientFolder.getAbsolutePath() + File.separator + policyFileName);
                policyFile.delete();

                //Remove file from cloud...
                DropboxUpload delete = new DropboxUpload();
                delete.removeFile(StoragePath.getDropboxDir() + patient, policyFileName);

                //Write Policy For File
                policyFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(policyFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(profession);
                oos.writeObject(speciality);
                oos.writeObject(organization);

                //Upload new Policy file to cloud
                DropboxUpload upload = new DropboxUpload();
                upload.uploadFile(patientFolder, policyFileName, StoragePath.getDropboxDir() + patient);
                DeleteDirectory.delete(patientFolder);

                //Update FileDetails 
                query = "update filedetails set permission='" + permission + "'";
                st.executeUpdate(query);

                session.setAttribute("msg", "File Updated Successfully");
                response.sendRedirect("DynamicPolicyView.jsp");
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("DynamicPolicyView.jsp");
            }
        } else {
            session.setAttribute("msg", "You Are Not allowed to Update Login First");
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
