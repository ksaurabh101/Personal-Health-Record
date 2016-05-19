/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class textFileUpload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Storage path
        String path = StoragePath.getPath();

        HttpSession session = request.getSession();
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String name = (String) session.getAttribute("loginName");
        String admin = (String) session.getAttribute("admin");
        if (patient != null && user == null && admin == null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {

                String phrName = request.getParameter("phrName");
                String phrData = request.getParameter("phrData");
                String key = request.getParameter("key");
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
                String patientKey = GetKey.getPatientKey(patient);
                if (patientKey.equals(key)) {
                    con = Config.getcon();
                    st = con.createStatement();
                    String query = "select * from filedetails where patientID='" + patient + "' and fileName='" + phrName + "'";
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        session.setAttribute("msg", "File Name Already Exist..");
                        response.sendRedirect("fileUpload.jsp");
                    } else {
                    //Check coorectness of key...

                        //Encrypt Data And Save in File
                        File mainFolder = new File(path + File.separator + "Encrypt");
                        if (!mainFolder.exists()) {
                            mainFolder.mkdir();
                        }
                        File patientFolder = new File(mainFolder + File.separator + patient);
                        if (!patientFolder.exists()) {
                            patientFolder.mkdir();
                        }
                        File tempFolder = new File(patientFolder.getAbsolutePath() + File.separator + "temp");
                        if (!tempFolder.exists()) {
                            tempFolder.mkdir();
                        }
                        File tempFile = new File(tempFolder.getAbsolutePath() + File.separator + phrName);
                        FileOutputStream fos = new FileOutputStream(tempFile);
                        byte[] buf = phrData.getBytes();
                        fos.write(buf);

                        //Encrypt file Locally..
                        if (uploadProcess.encrypt(tempFolder, patientFolder, phrName, patient)) {
                            System.out.println("File Uploaded Successfully");
                            DeleteDirectory.delete(tempFolder);
                        } else {
                            System.out.println("File Not Uploaded Successfully");
                        }

                        //Write Policy For File
                        String policyFile = "policy" + phrName + ".txt";
                        fos = new FileOutputStream(patientFolder.getAbsolutePath() + File.separator + policyFile);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(profession);
                        oos.writeObject(speciality);
                        oos.writeObject(organization);

                        //Upload Policy File to cloud
                        DropboxUpload upload = new DropboxUpload();
                        upload.uploadFile(patientFolder, policyFile, StoragePath.getDropboxDir() + patient);
                        DeleteDirectory.delete(patientFolder);
                        
                        //Update FileDetails 
                        query = "insert into filedetails values('" + patient + "','" + name + "','" + phrName + "','" + permission + "')";
                        st.executeUpdate(query);

                        //Update log 
                        query = "select * from log";
                        int noOfFile = 0;
                        rs = st.executeQuery(query);
                        if (rs.next()) {
                            noOfFile = rs.getInt("noOfFile") + 1;
                        }
                        query = "UPDATE log SET noOfFile='" + noOfFile + "'";
                        st.executeUpdate(query);

                        session.setAttribute("msg", "File Uploaded Successfully");
                        response.sendRedirect("fileUpload.jsp");
                    }
                } else {
                    session.setAttribute("msg", "Key is not Correct");
                    response.sendRedirect("fileUpload.jsp");
                }
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("fileUpload.jsp");
            }
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
