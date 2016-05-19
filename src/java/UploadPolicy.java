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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import path.StoragePath;

/**
 *
 * @author Saurabh
 */
public class UploadPolicy extends HttpServlet {

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
        String phrName = (String) session.getAttribute("phrName");

        String permission = request.getParameter("permission");

        String[] profession = request.getParameterValues("profession");
        String[] speciality = request.getParameterValues("speciality");
        String[] organization = request.getParameterValues("organization");
        String admin = (String) session.getAttribute("admin");
        if (patient != null && user == null && admin == null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                File mainFolder = new File(path + File.separator + "Encrypt");
                if (!mainFolder.exists()) {
                    mainFolder.mkdir();
                }
                //Write Policy For File
                FileOutputStream fos = new FileOutputStream(mainFolder.getAbsolutePath() + File.separator + "policy" + phrName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(profession);
                oos.writeObject(speciality);
                oos.writeObject(organization);

                //Update FileDetails 
                String query = "insert into filedetails values('" + patient + "','" + name + "','" + phrName + "','" + permission + "')";
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
