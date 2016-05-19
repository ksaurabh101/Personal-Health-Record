/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
public class DynamicPolicyView extends HttpServlet {

    public static String getPermission(String patientID, String filName) {
        Connection con;
        Statement st;
        ResultSet rs;
        String permission = "";
        try {
            con = Config.getcon();
            st = con.createStatement();
            String query = "select * from filedetails where patientID='" + patientID + "' and fileName='" + filName + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                permission = rs.getString("permission");
                System.out.println("Permission : " + permission);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return permission;
    }

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

//        String path = getServletContext().getRealPath("/");
        //Storage path
        String path = StoragePath.getPath();

        File main = new File(path + File.separator + "Encrypt");
        if (!main.exists()) {
            main.mkdir();
        }
        File f = new File(main + File.separator + patient);
        if (!f.exists()) {
            f.mkdir();
        }
        File fTemp = new File(f.getAbsolutePath() + File.separator + "temp");
        if (!fTemp.exists()) {
            fTemp.mkdir();
        }

        String fileName = request.getParameter("fileName");
        String action = request.getParameter("action");
        String key = request.getParameter("key");

        File policyFile = new File(f.getAbsolutePath() + File.separator + "policy" + fileName + ".txt");
        File outputFile = new File(fTemp.getAbsolutePath() + File.separator + "policy" + fileName + ".txt");
        if (action.equals("View")) {

            String uKey = GetKey.getPatientKey(patient);
            if (uKey.equals(key)) {
                FileInputStream fis = new FileInputStream(policyFile);
                ObjectInputStream ois = new ObjectInputStream(fis);

                ArrayList<String> profession = new ArrayList<>();
                ArrayList<String> speciality = new ArrayList<>();
                ArrayList<String> organization = new ArrayList<>();
                try {
                    profession = (ArrayList<String>) ois.readObject();
                    speciality = (ArrayList<String>) ois.readObject();
                    organization = (ArrayList<String>) ois.readObject();
                    String save = "Permission : " + getPermission(patient, fileName) + " : : :";
                    save += "Profession : [";
                    for (String s : profession) {
                        save += s + " ,";
                    }
                    save += "]" + " : : : " + "Speciality : [";
                    for (String s : speciality) {
                        save += s + " ,";
                    }
                    save += "]" + " : : : " + "Organization : [";
                    for (String s : organization) {
                        save += s + " ,";
                    }
                    save += "]";
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    byte buf[] = save.getBytes();
                    fos.write(buf);
                    Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + outputFile);
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        session.setAttribute("msg", "Error : " + e);
                    }
                    outputFile.delete();
                    for (File dfile : fTemp.listFiles()) {
                        dfile.deleteOnExit();
                    }
                    DeleteDirectory.delete(fTemp);
                } catch (Exception e) {
                    out.println("Error : " + e);
                }
                out.println("Policy is Successfully Shown.");
            } else {
                out.println("Key is Not correct...");
            }
        } else {
            out.println("Not in View Action...");
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
