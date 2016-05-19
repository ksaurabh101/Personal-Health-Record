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
public class SendMessage extends HttpServlet {

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
        String receiverID = request.getParameter("receiverID");
        String message = request.getParameter("message");
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");
        String senderID = "";
        java.util.Date obj = new java.util.Date();
        Date date = new Date(obj.getYear(), obj.getMonth(), obj.getDay());
        int flag = 0;
        if (patient != null) {
            senderID = patient;
        } else if (user != null) {
            senderID = user;
        } else {
            senderID = admin;
        }
        String senderName = (String) session.getAttribute("loginName");
        String receiverName = "";
        Connection con;
        Statement st;
        ResultSet rs;
        if (senderID.equals(receiverID.trim())) {
            session.setAttribute("msg", "Message Not Sent : Receiver ID is same to your Id");
            response.sendRedirect("SendMessage.jsp");
        } else {
            try {
                con = Config.getcon();
                st = con.createStatement();
                if (receiverID.indexOf("PAT")==0) {
                    String query = "select * from patient where patientID='" + receiverID + "' or email='" + receiverID + "'";
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        receiverName = rs.getString("name");
                        query = "insert into message values('" + senderID + "','" + senderName + "','" + receiverID + "','" + receiverName + "','" + date + "','" + message + "','" + flag + "')";
                        st.executeUpdate(query);
                    } else {
                        session.setAttribute("msg", "Patient does not Exist..");
                        response.sendRedirect("SendMessage.jsp");
                    }
                } else if (receiverID.indexOf("USER")==0) {
                    String query = "select * from user where userID='" + receiverID + "' or email='" + receiverID + "'";
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        receiverName = rs.getString("name");
                        query = "insert into message values('" + senderID + "','" + senderName + "','" + receiverID + "','" + receiverName + "','" + date + "','" + message + "','" + flag + "')";
                        st.executeUpdate(query);
                    } else {
                        session.setAttribute("msg", "User does not Exist..");
                        response.sendRedirect("SendMessage.jsp");
                    }
                } else {
                    String query = "select * from admin where adminID='" + receiverID + "' or email='" + receiverID + "'";
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        receiverName = rs.getString("name");
                        query = "insert into message values('" + senderID + "','" + senderName + "','" + receiverID + "','" + receiverName + "','" + date + "','" + message + "','" + flag + "')";
                        st.executeUpdate(query);
                    } else {
                        session.setAttribute("msg", "Admin does not Exist..");
                        response.sendRedirect("SendMessage.jsp");
                    }
                }
                session.setAttribute("msg", "Message Successfully Sent..");
                response.sendRedirect("SendMessage.jsp");
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("loginError.jsp");
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
