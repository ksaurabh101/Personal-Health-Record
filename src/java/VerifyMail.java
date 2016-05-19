/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mail.GenerateOTP;
import mail.SendMail;

/**
 *
 * @author Saurabh
 */
public class VerifyMail extends HttpServlet {

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
        String email = request.getParameter("email");
        String userVerify = (String) session.getAttribute("userVerify");
        session.removeAttribute("userVerify");
        GenerateOTP obj = new GenerateOTP();
        String otp = obj.generateOTP();

        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");
        if (patient == null && user == null && admin == null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "update " + userVerify + " set otp='" + otp + "' where email='" + email + "'";
                st.executeUpdate(query);
//                System.out.println("first : " + query);
                query = "select * from " + userVerify + " where email='" + email + "'";
//                System.out.println(query);
                rs = st.executeQuery(query);
                String receiverName = "";
                if (rs.next()) {
                    receiverName = rs.getString("name");
                }
                String subject = "Verification Mail";
                String text = "Hello " + receiverName + ",\nPlz Enter OTP to verify your account.\nOTP : " + otp;
                SendMail mail = new SendMail(email, subject, text);
                mail.sendMail();
                session.setAttribute("msg", "OTP sent..Plz verify email");
                if (userVerify.equals("patient")) {
                    response.sendRedirect("PatientVerify.jsp");
                }
                if (userVerify.equals("user")) {
                    response.sendRedirect("UserVerify.jsp");
                }
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("loginError.jsp");
            }
        } else {
            session.setAttribute("msg", "You Are Already Logined..Logout First");
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
