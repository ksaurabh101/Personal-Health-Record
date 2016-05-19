/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import Config.PassEncrypt;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
public class patientRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String patientID = "";
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String mStatus = request.getParameter("mStatus");
        String pNumber = request.getParameter("pNumber");
        String mNumber = request.getParameter("mNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String disease = request.getParameter("disease");
        String password = request.getParameter("password");
        String key = (String) session.getAttribute("patientKey");
        session.removeAttribute("patientKey");
        java.util.Date obj = new java.util.Date();
        Date date = new Date(obj.getYear(), obj.getMonth(), obj.getDay());
        int flag = 0;
        GenerateOTP obj1 = new GenerateOTP();
        String otp = obj1.generateOTP();

        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");
        if (key != null) {
            if (patient == null && user == null && admin == null) {
                Connection con;
                Statement st;
                ResultSet rs;
                try {
                    con = Config.getcon();
                    st = con.createStatement();
                    String query = "select * from patient where email='" + email + "'";
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        session.setAttribute("msg", "Email-Id already Exists...Use Another email..");
                        response.sendRedirect("loginError.jsp");
                    } else {
                        query = "select * from log";
                        int noOfPatient = 0;
                        rs = st.executeQuery(query);
                        if (rs.next()) {
                            noOfPatient = rs.getInt("noOfPatient") + 1;
                            patientID = "PAT" + String.format("%05d", noOfPatient);
                        } else {
                            patientID = "PAT00001";
                        }
                        query = "UPDATE log SET noOfPatient='" + noOfPatient + "'";
                        st.executeUpdate(query);
                        //Encrypt Password
                        String ePassword = PassEncrypt.passEncrypt(password);
                        query = "insert into patient values('" + patientID + "','" + name + "','" + dob + "','" + gender + "','" + mStatus + "','" + pNumber + "','" + mNumber + "','" + email + "','" + address + "','" + disease + "','" + ePassword + "','" + key + "','" + date + "','" + flag + "','" + otp + "')";
                        st.executeUpdate(query);

                        //Send Verification mail
                        String subject = "Verification Mail";
                        String text = "Hello " + name + ",\nPlz Enter OTP to verify your account.\nOTP : " + otp;
                        SendMail mail = new SendMail(email, subject, text);
                        mail.sendMail();
                        session.setAttribute("msg", "Registration Successful..Plz verify email");
                        response.sendRedirect("PatientVerify.jsp");
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
        } else {
            session.setAttribute("msg", "Key is not Generated..");
            response.sendRedirect("patientRegistration.jsp");
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
