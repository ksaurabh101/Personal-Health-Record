/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import Config.PassEncrypt;
import java.io.IOException;
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
public class patientLogin extends HttpServlet {

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
                    //Encrypt Password
                    String ePassword = PassEncrypt.passEncrypt(password);
                    if (ePassword.equals(rs.getString("password"))) {
                        if (rs.getInt("flag") == 1) {
                            session.setAttribute("patient", rs.getString("patientID"));
                            session.setAttribute("loginName", rs.getString("name"));
                            response.sendRedirect("home.jsp");
                        } else {
                            session.setAttribute("msg", "Email is not verified");
                            response.sendRedirect("PatientVerify.jsp");
                        }
                    } else {
                        session.setAttribute("msg", "Password is Not Currect");
                        response.sendRedirect("patientLogin.jsp");
                    }
                } else {
                    session.setAttribute("msg", "Patient Does Not Exist..Plz Register first");
                    response.sendRedirect("patientRegistration.jsp");
                }
            } catch (Exception e) {
                session.setAttribute("msg", "Error : " + e);
                response.sendRedirect("patientLogin.jsp");
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
