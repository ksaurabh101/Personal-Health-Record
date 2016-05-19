/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import com.mchange.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import upload.DropboxUpload;

/**
 *
 * @author Saurabh
 */
public class FileAccess extends HttpServlet {

    public static boolean verify(ArrayList<String> array, String data) {
        for (String tem : array) {
            if (tem.equalsIgnoreCase(data)) {
                return true;
            }
        }
        return false;
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
        File mainFolder = new File(path + File.separator + "Encrypt");
        if (!mainFolder.exists()) {
            mainFolder.mkdir();
        }
        File patientFolder = new File(mainFolder + File.separator + patient);
        if (!patientFolder.exists()) {
            patientFolder.mkdir();
        }
        String fileName = request.getParameter("fileName");
        String action = request.getParameter("action");
        String key = request.getParameter("key");

        File file = new File(patientFolder.getAbsolutePath() + File.separator + fileName);
        //patient action
        if (action.equals("View")) {
            String uKey = GetKey.getPatientKey(patient);
            if (uKey.equals(key)) {
                if (uploadProcess.decrypt(patientFolder, fileName, patient, key) == true) {
                    File outputFolder = new File(patientFolder.getAbsolutePath() + File.separator + "temp");
                    if (!outputFolder.exists()) {
                        outputFolder.mkdir();
                    }
                    File accessFile = new File(outputFolder.getAbsolutePath() + File.separator + fileName);
                    Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + accessFile);

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                    accessFile.delete();
                    for (File dfile : outputFolder.listFiles()) {
                        dfile.deleteOnExit();
                    }
                    DeleteDirectory.delete(outputFolder);
                    DeleteDirectory.delete(patientFolder);
                    out.println("File Successfully Opened...");
                } else {
                    out.println("Decryption Error..");
                }
            } else {
                //session.setAttribute("msg", "Key is Not Correct...");
                out.println("Key is Not Correct...");
            }
        }
        //patient action
        if (action.equals("Remove")) {
            String policyFileName = "policy" + fileName + ".txt";
            //Remove from local storage..
            File policyFile = new File(patientFolder.getAbsolutePath() + File.separator + policyFileName);
            policyFile.delete();
            file.delete();

            //Remove files from cloud...
            DropboxUpload delete = new DropboxUpload();
            delete.removeFile(StoragePath.getDropboxDir() + patient, fileName);
            delete.removeFile(StoragePath.getDropboxDir() + patient, policyFileName);

            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "delete from filedetails where patientID='" + patient + "' and fileName='" + fileName + "'";
                st.executeUpdate(query);

                //Update log 
                query = "select * from log";
                int noOfFile = 0;
                rs = st.executeQuery(query);
                if (rs.next()) {
                    noOfFile = rs.getInt("noOfFile") - 1;
                }
                query = "UPDATE log SET noOfFile='" + noOfFile + "'";
                st.executeUpdate(query);
                out.println("File Is Removed Successfully");
            } catch (Exception e) {
                System.out.println("Error : " + e);
                out.println("Error : " + e);
            }
        }
        //patient action
        if (action.equals("Download")) {
            String uKey = GetKey.getPatientKey(patient);
            if (uKey.equals(key)) {
                if (uploadProcess.decrypt(patientFolder, fileName, patient, key) == true) {
                    File outFolder = new File(patientFolder.getAbsolutePath() + File.separator + "temp");
                    if (!outFolder.exists()) {
                        outFolder.mkdir();
                    }
                    File accessFile = new File(outFolder.getAbsolutePath() + File.separator + fileName);
                    File downloadFolder = new File(StoragePath.getDownLoadFolder());
                    if (!downloadFolder.exists()) {
                        downloadFolder.mkdir();
                    }
                    File downloadFile = new File(downloadFolder.getAbsoluteFile() + File.separator + fileName);
                    FileInputStream fis = new FileInputStream(accessFile);
                    FileOutputStream fos = new FileOutputStream(downloadFile);
                    byte[] buf = new byte[1024];
                    int read;
                    while ((read = fis.read(buf)) != -1) {
                        fos.write(buf, 0, read);
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                    accessFile.delete();
                    for (File dfile : outFolder.listFiles()) {
                        dfile.deleteOnExit();
                    }
                    out.println("File Successfully Downloaded.at Location " + StoragePath.getDownLoadFolder());
                } else {
                    out.println("Decryption Error..");
                }
            } else {
                out.println("Key is Not Correct...");
            }
        }

        //User Actions..
        if (action.equals("Read")) {
            int index = fileName.indexOf(" ");
            String id = fileName.substring(0, index).trim();
            String fName = fileName.substring(index).trim();
            File fPath = new File(path + File.separator + "Encrypt" + File.separator + id);
            if (!fPath.exists()) {
                fPath.mkdir();
            }
            String policyFileName = "policy" + fName + ".txt";

            //Download Policy file from cloud...
            DropboxUpload download = new DropboxUpload();
            download.downloadFile(policyFileName, StoragePath.getDropboxDir() + id, fPath);

            File policyFile = new File(fPath.getAbsolutePath() + File.separator + policyFileName);
            try {
                FileInputStream fis = new FileInputStream(policyFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<String> profession = new ArrayList<>();
                ArrayList<String> speciality = new ArrayList<>();
                ArrayList<String> organization = new ArrayList<>();
                profession = (ArrayList<String>) ois.readObject();
                speciality = (ArrayList<String>) ois.readObject();
                organization = (ArrayList<String>) ois.readObject();

                con = Config.getcon();
                st = con.createStatement();
                String query = "select * from user where userID='" + user + "'";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    String uProfession = rs.getString("profession");
                    String uSpeciality = rs.getString("speciality");
                    String uOrganization = rs.getString("organization");
                    if (verify(profession, uProfession) && verify(speciality, uSpeciality) && verify(organization, uOrganization)) {
                        if (uploadProcess.decrypt(fPath, fName, id) == true) {
                            File outputFolder = new File(fPath.getAbsolutePath() + File.separator + "temp");
                            if (!outputFolder.exists()) {
                                outputFolder.mkdir();
                            }
                            File accessFile = new File(outputFolder.getAbsolutePath() + File.separator + fName);
                            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + accessFile);

                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {

                            }
                            accessFile.delete();
                            for (File dfile : outputFolder.listFiles()) {
                                dfile.deleteOnExit();
                            }
                            DeleteDirectory.delete(outputFolder);
                            out.println("File Successfully Opened...");
                        } else {
                            //session.setAttribute("msg", "Key is Not Correct...");
                            out.println("Key is Not Correct...");
                        }
                    } else {
                        out.println("You are not authorized..");
                    }
                } else {
                    out.println("Error..Try Again..");
                }
            } catch (Exception e) {
                out.println("Error : " + e);
            }
        }
        //User Action..
        if (action.equals("Status")) {
            String value = request.getParameter("value");
            String appointmentNo = request.getParameter("appointmentNo");
            if (user != null && patient == null) {
                try {
                    con = Config.getcon();
                    st = con.createStatement();
                    String query = "UPDATE appointment SET status='" + value + "' where appointmentNo='" + appointmentNo + "'";
                    st.executeUpdate(query);
                    out.println("Status is Updated Successfully");
                } catch (Exception e) {
                    System.out.println("Error=" + e);
                    out.println("Error=" + e);
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
