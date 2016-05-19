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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import path.StoragePath;

/**
 *
 * @author Saurabh
 */
public class EmergencyUserLogin extends HttpServlet {

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

        String fileName = request.getParameter("fileName");
        String patient = request.getParameter("patientID");

//        String path = getServletContext().getRealPath("/");
        //Storage path
        String path = StoragePath.getPath();

        File f = new File(path + File.separator + "Encrypt" + File.separator + patient);
        if (!f.exists()) {
            f.mkdir();
        }

        File file = new File(f.getAbsolutePath() + File.separator + fileName);

        String uKey = GetKey.getPatientKey(patient);

        if (uploadProcess.decrypt(f, fileName, patient, uKey) == true) {
            File outFile = new File(f.getAbsolutePath() + File.separator + "temp");
            if (!outFile.exists()) {
                outFile.mkdir();
            }
            File accessFile = new File(outFile.getAbsolutePath() + File.separator + fileName);
            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + accessFile);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
            accessFile.delete();
            for (File dfile : outFile.listFiles()) {
                dfile.deleteOnExit();
            }
            DeleteDirectory.delete(outFile);
            out.println("File Successfully Opened...");
        } else {
            out.println("Decryption Error..");
        }


        /*
         String requestNo = request.getParameter("requestNo");
         String patientID = request.getParameter("patientID");
         String name = request.getParameter("name");
         String otp = request.getParameter("otp");

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
         String query = "select * from requestotp where requestNo='" + requestNo + "' or rEmail='" + requestNo + "'";
         rs = st.executeQuery(query);
         if (rs.next()) {
         String oneTimePass = rs.getString("otp");
         if (oneTimePass.equals("null")) {
         session.setAttribute("msg", "You are not Approved Yet..Try again");
         response.sendRedirect("breakGlass.jsp");
         } else {
         if (oneTimePass.equals(otp)) {
         if (patientID.equalsIgnoreCase(rs.getString("patientID"))) {
         if (name.equalsIgnoreCase(rs.getString("patientName"))) {
         session.setAttribute("reqNo", rs.getString("requestNo"));
         session.setAttribute("loginName", rs.getString("rName"));
         response.sendRedirect("EmergencyUserView.jsp");
         } else {
         session.setAttribute("msg", "Patient Name is Not Currect");
         response.sendRedirect("breakGlass.jsp");
         }
         } else {
         session.setAttribute("msg", "Patient ID is Not Currect");
         response.sendRedirect("breakGlass.jsp");
         }
         } else {
         session.setAttribute("msg", "OTP is Not Currect");
         response.sendRedirect("breakGlass.jsp");
         }
         }
         } else {
         session.setAttribute("msg", "Incorrect Request No");
         response.sendRedirect("breakGlass.jsp");
         }
         } catch (Exception e) {
         session.setAttribute("msg", "Login Error..Try Again");
         response.sendRedirect("breakGlass.jsp");
         }
         } else {
         session.setAttribute("msg", "Already Login....Logout First");
         response.sendRedirect("loginError.jsp");
         }*/
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
