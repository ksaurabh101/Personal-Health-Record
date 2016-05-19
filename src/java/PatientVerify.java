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

/**
 *
 * @author Saurabh
 */
public class PatientVerify extends HttpServlet {

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

        String patientID = request.getParameter("patientID");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String admin = (String) session.getAttribute("admin");
        if (user == null && patient == null && admin == null) {
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "select * from patient where patientID='" + patientID + "' or email='" + patientID + "'";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    if (password.equals(rs.getString("otp"))) {
                        String value = "null";
                        query = "update patient set otp='" + value + "',flag=1 where patientID='" + patientID + "' or email='" + patientID + "'";
                        st.executeUpdate(query);
                        session.setAttribute("msg", "Email is verified.");
                        response.sendRedirect("patientLogin.jsp");
                    } else {
                        session.setAttribute("msg", "OTP is Not Currect");
                        response.sendRedirect("PatientVerify.jsp");
                    }
                } else {
                    session.setAttribute("msg", "Patient Does Not Exist..Plz Register first");
                    response.sendRedirect("patientRegistration.jsp");
                }
            } catch (Exception e) {
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("PatientVerify.jsp");
            }
        } else {
            session.setAttribute("msg", "Multiple Login not allowed..Logout First");
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
