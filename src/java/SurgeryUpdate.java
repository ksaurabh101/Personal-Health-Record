/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
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

/**
 *
 * @author Saurabh
 */
public class SurgeryUpdate extends HttpServlet {

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

        String patientId = request.getParameter("patientId");
        String patientName = request.getParameter("patientName");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String problemDescription = request.getParameter("problemDescription");
        String solution = request.getParameter("solution");

        HttpSession session = request.getSession();
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String usertName = (String) session.getAttribute("loginName");
        String admin = (String) session.getAttribute("admin");
        if (patient == null && user != null && admin == null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "select * from patient where patientID='" + patientId + "' or email='" + patientId + "'";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    if (patientName.equalsIgnoreCase(rs.getString("name"))) {
                        query = "insert into surgerydetails values('" + patientId + "','" + patientName + "','" + user + "','" + usertName + "','" + problemDescription + "','" + date + "','" + time + "','" + solution + "')";
                        st.executeUpdate(query);
                        session.setAttribute("msg", "Surgery Details Successfully Updated");
                        response.sendRedirect("SurgeryUpdate.jsp");
                    } else {
                        session.setAttribute("msg", "Patient Name is Not correct");
                        response.sendRedirect("SurgeryUpdate.jsp");
                    }
                } else {
                    session.setAttribute("msg", "There is no Patient with this Patient-Id or Email-ID");
                    response.sendRedirect("SurgeryUpdate.jsp");
                }
            } catch (Exception e) {
                System.out.println("Error=" + e);
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("loginError.jsp");
            }
        } else {
            session.setAttribute("msg", "You are not Login..Login First");
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
