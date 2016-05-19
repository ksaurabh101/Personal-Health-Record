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
public class ForgotOTP extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Connection con;
        Statement st;
        ResultSet rs;
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");

        String requestNo = request.getParameter("requestNo");
        String patientID = request.getParameter("patientID");

        if (user == null && patient == null && admin == null) {
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "select * from requestotp where requestNo='" + requestNo + "' or rEmail='" + requestNo + "'";
                rs = st.executeQuery(query);
                String receiverName = "";
                String name = "";
                String receiver = "",
                        subject = "",
                        text = "";
                if (rs.next()) {
                    receiver = rs.getString("rEmail");
                    receiverName = rs.getString("rName");
                    name = rs.getString("patientName");
                }
                subject = "New OTP for Request no " + requestNo;
                GenerateOTP obj = new GenerateOTP();
                String otp = obj.generateOTP();
                text = "Hello " + receiverName + ",\nYour new OTP\nRequest no : " + requestNo + "\nOTP : " + otp + "\nPatientID : " + patientID + "\nPatient Name : " + name;
                query = "UPDATE requestotp SET otp='" + otp + "' where requestNo='" + requestNo + "'";
                st.executeUpdate(query);
                SendMail mail = new SendMail(receiver, subject, text);
                mail.sendMail();
                session.setAttribute("msg", "New OTP has been sent");
                response.sendRedirect("breakGlass.jsp");
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("ForgotOTP.jsp");
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
