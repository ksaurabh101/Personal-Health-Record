/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UserWritePHR extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Storage path
        String path = StoragePath.getPath();

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String fileName = (String) session.getAttribute("fileName");
        session.removeAttribute("fileName");
        String phrData = request.getParameter("phrData");
        String admin = (String) session.getAttribute("admin");
        if (patient == null && user != null && admin == null) {
            try {
                int index = fileName.indexOf(" ");
                String id = fileName.substring(0, index).trim();
                String fName = fileName.substring(index).trim();
                File patientFolder = new File(path + File.separator + "Encrypt" + File.separator + id);
                if (!patientFolder.exists()) {
                    patientFolder.mkdir();
                }
                File oldFile = new File(patientFolder.getAbsolutePath() + File.separator + fName);
                String key = GetKey.getPatientKey(id);

                if (uploadProcess.decrypt(patientFolder, fName, id, key) == true) {

                    //Remove file from cloud...
                    DropboxUpload delete = new DropboxUpload();
                    delete.removeFile(StoragePath.getDropboxDir() + id, fName);
                    
                    //Remove file from Local storage
                    oldFile.delete();
                    
                    File outputFolder = new File(patientFolder.getAbsolutePath() + File.separator + "temp");
                    if (!outputFolder.exists()) {
                        outputFolder.mkdir();
                    }
                    File accessFile = new File(outputFolder.getAbsolutePath() + File.separator + fName);
                    FileOutputStream fos = new FileOutputStream(accessFile, true);
                    byte[] buf = phrData.getBytes();
                    fos.write(buf);
                    if (uploadProcess.encrypt(outputFolder, patientFolder, fName, id) == true) {
                        accessFile.delete();
                        for (File dfile : outputFolder.listFiles()) {
                            dfile.deleteOnExit();
                        }
                        DeleteDirectory.delete(outputFolder);
                        DeleteDirectory.delete(patientFolder);
                        session.setAttribute("msg", " File Updated Successfully");
                        response.sendRedirect("UserViewPHR.jsp");
                    }
                } else {
                    session.setAttribute("msg", "Key is Not Correct...");
                    response.sendRedirect("UserViewPHR.jsp");
                }
            } catch (Exception e) {
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("UserViewPHR.jsp");
            }
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
