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
public class userRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String userID = "";
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String mStatus = request.getParameter("mStatus");
        String pNumber = request.getParameter("pNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String profession = request.getParameter("profession");
        String speciality = request.getParameter("speciality");
        String organization = request.getParameter("organization");
        String key = (String) session.getAttribute("userKey");
        session.removeAttribute("userKey");
        String password = request.getParameter("password");
        java.util.Date obj = new java.util.Date();
        Date date = new Date(obj.getYear(), obj.getMonth(), obj.getDay());
        int flag = 0;
        int approval = 0;
        GenerateOTP obj1 = new GenerateOTP();
        String otp = obj1.generateOTP();

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
                String query = "select * from user where email='" + email + "'";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    session.setAttribute("msg", "Email-Id already Exists...Use Another email..");
                    response.sendRedirect("loginError.jsp");
                } else {
                    query = "select * from log";
                    int noOfUser = 0;
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        noOfUser = rs.getInt("noOfUser") + 1;
                        userID = "USER" + String.format("%05d", noOfUser);
                    } else {
                        userID = "USER00001";
                    }
                    query = "UPDATE log SET noOfUser='" + noOfUser + "'";
                    st.executeUpdate(query);
                    //Encrypt Password
                    String ePassword = PassEncrypt.passEncrypt(password);
                    query = "insert into user values('" + userID + "','" + name + "','" + dob + "','" + gender + "','" + mStatus + "','" + pNumber + "','" + email + "','" + address + "','" + profession + "','" + speciality + "','" + organization + "','" + key + "','" + ePassword + "','" + date + "','" + flag + "','" + approval + "','" + otp + "')";
                    st.executeUpdate(query);

                    //Send Verification mail
                    String subject = "Verification Mail";
                    String text = "Hello " + name + ",\nPlz Enter OTP to verify your account.\nOTP : " + otp;
                    SendMail mail = new SendMail(email, subject, text);
                    mail.sendMail();
                    session.setAttribute("msg", "Registration Successful..Plz verify email");
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
