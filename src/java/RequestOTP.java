/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.IOException;
import java.io.PrintWriter;
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
import mail.SendMail;

/**
 *
 * @author Saurabh
 */
public class RequestOTP extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String rName = request.getParameter("emergencyUserName");
        String rEmail = request.getParameter("email");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String pNumber = request.getParameter("pNumber");
        String mNumber = request.getParameter("mNumber");
        String address = request.getParameter("address");
        String patientID = request.getParameter("patientID");
        String name = request.getParameter("name");
        String status = "Pending";
        String note = request.getParameter("note");
        java.util.Date obj = new java.util.Date();
        Date date = new Date(obj.getYear(), obj.getMonth(), obj.getDay());
        String otp = "null";
        HttpSession session = request.getSession();
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
                String query = "select * from requestotp where rEmail='" + rEmail + "' and patientID='" + patientID + "' and status!='Done'";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    session.setAttribute("msg", "You have already one request with this PatientID..Wait for Approval");
                    response.sendRedirect("RequestOTP.jsp");
                } else {
                    query = "select * from log";
                    int noOfReq = 0;
                    rs = st.executeQuery(query);
                    String requestNo = "";
                    if (rs.next()) {
                        noOfReq = rs.getInt("noOfReq") + 1;
                        requestNo = "REQ" + String.format("%05d", noOfReq);
                    } else {
                        requestNo = "REQ00001";
                    }
                    query = "UPDATE log SET noOfReq='" + noOfReq + "'";
                    st.executeUpdate(query);
                    query = "insert into requestotp values('" + requestNo + "','" + rName + "','" + rEmail + "','" + dob + "','" + gender + "','" + pNumber + "','" + mNumber + "','" + address + "','" + patientID + "','" + name + "','" + status + "','" + note + "','" + date + "','" + otp + "')";
                    st.executeUpdate(query);
                    session.setAttribute("msg", "Reguest is Successfully sent");

                    //Send Mail to Emergency User
                    String subject = "Request for OTP";
                    String text = "Hello " + rName + ",\nYour request with\nRequest no : " + requestNo + " for accessing phr of\nPatient Name : " + name + "\nPatient ID : " + patientID + " has been saved";
                    SendMail mail = new SendMail(rEmail, subject, text);
                    mail.sendMail();
                    response.sendRedirect("RequestOTP.jsp");
                }
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("RequestOTP.jsp");
            }
        } else {
            session.setAttribute("msg", "Someone Already Logined..Logout First");
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
