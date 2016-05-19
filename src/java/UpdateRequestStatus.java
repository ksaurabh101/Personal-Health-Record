/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.File;
import java.io.IOException;
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
import mail.GenerateOTP;
import mail.SendMail;

/**
 *
 * @author Saurabh
 */
public class UpdateRequestStatus extends HttpServlet {

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
        String action = request.getParameter("action");
        String value = request.getParameter("value");

//PatientViewRequest.jsp..
        if (action.equals("Status")) {
            if (user == null && patient != null && admin == null) {
                try {
                    con = Config.getcon();
                    st = con.createStatement();
                    String query = "select * from requestotp where requestNo='" + requestNo + "'";
                    rs = st.executeQuery(query);
                    String patientID = "";
                    String name = "";
                    String receiverName = "";
                    String receiver = "",
                            subject = "",
                            text = "";
                    if (rs.next()) {
                        receiver = rs.getString("rEmail");
                        receiverName = rs.getString("rName");
                        patientID = rs.getString("patientID");
                        name = rs.getString("patientName");
                    }
                    subject = "Response for Request no " + requestNo;
                    if (value.equals("Approved")) {
                        GenerateOTP obj = new GenerateOTP();
                        String otp = obj.generateOTP();
                        text = "Hello " + receiverName + ",\nYour request has been " + value + " for \nRequest no : " + requestNo + "\nOne time password(OTP) is : " + otp + "\nPatientID : " + patientID + "\nPatient Name : " + name;
                        query = "UPDATE requestotp SET status='" + value + "', otp='" + otp + "' where requestNo='" + requestNo + "'";
                        st.executeUpdate(query);
                        SendMail mail = new SendMail(receiver, subject, text);
                        mail.sendMail();
                    } else {
                        text = "Hello " + receiverName + ",\nYour request has been " + value;
                        query = "UPDATE requestotp SET status='" + value + "' where requestNo='" + requestNo + "'";
                        st.executeUpdate(query);
                        SendMail mail = new SendMail(receiver, subject, text);
                        mail.sendMail();
                    }

                    out.println("Status is Updated Successfully");
                } catch (Exception e) {
                    System.out.println("Error=" + e);
                    out.println("Error : " + e);
                }
            }
        }
        //ViewUser.jsp...
        if (action.equals("Approval")) {
            if (user == null && patient == null && admin != null) {
                int flag = 0;
                if (value.equals("Approved")) {
                    flag = 1;
                }
                try {
                    con = Config.getcon();
                    st = con.createStatement();
                    String query = "select * from user where userID='" + requestNo + "'";
//                    System.out.println(query);
                    rs = st.executeQuery(query);
                    String receiverName = "";
                    String receiver = "",
                            subject = "",
                            text = "";
                    if (rs.next()) {
                        receiver = rs.getString("email");
                        receiverName = rs.getString("name");
                    }
                    subject = "Account Verification";
                    text = "Hello " + receiverName + ",\nYour account is " + value;
                    SendMail mail = new SendMail(receiver, subject, text);
                    mail.sendMail();
                    query = "UPDATE user SET approval=" + flag + " where userID='" + requestNo + "'";
//                    System.out.println(query);
                    st.executeUpdate(query);
                    out.println("Approval is Updated Successfully");
                } catch (Exception e) {
                    System.out.println("Error=" + e);
                    out.println("Error : " + e);
                }
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
