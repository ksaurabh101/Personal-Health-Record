/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import Config.PassEncrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Saurabh
 */
public class AdminRegistration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String adminID = "";
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String mStatus = request.getParameter("mStatus");
        String pNumber = request.getParameter("pNumber");
        String mNumber = request.getParameter("mNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        java.util.Date obj = new java.util.Date();
        Date date = new Date(obj.getYear(), obj.getMonth(), obj.getDay());
        
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");
        if (patient == null && user == null && admin != null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "select * from log";
                int noOfAdmin = 0;
                rs = st.executeQuery(query);
                if (rs.next()) {
                    noOfAdmin = rs.getInt("noOfAdmin") + 1;
                    adminID = "ADM" + String.format("%05d", noOfAdmin);
                } else {
                    adminID = "ADM00001";
                }
                query = "UPDATE log SET noOfAdmin='" + noOfAdmin + "'";
                st.executeUpdate(query);
                //Encrypt Password
                String ePassword = PassEncrypt.passEncrypt(password);
                query = "insert into admin values('" + adminID + "','" + name + "','" + dob + "','" + gender + "','" + mStatus + "','" + pNumber + "','" + mNumber + "','" + email + "','" + address + "','" + ePassword + "','" + date + "')";
                st.executeUpdate(query);
                session.setAttribute("msg", "Registration Successful");
                response.sendRedirect("home.jsp");
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("loginError.jsp");
            }
        } else {
            session.setAttribute("msg", "First Login as Admin..");
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
