/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.Config;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import path.StoragePath;
import upload.DropboxUpload;

/**
 *
 * @author Saurabh
 */
public class fileUpload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String phrName = "";
        String key = "";
        String permission = "Read";
        //Path for storage
//        String path = getServletContext().getRealPath("/");
        String path = StoragePath.getPath();

        ArrayList<String> profession = new ArrayList<>();
        ArrayList<String> speciality = new ArrayList<>();
        ArrayList<String> organization = new ArrayList<>();
        HttpSession session = request.getSession();

        String patient = (String) session.getAttribute("patient");
        String user = (String) session.getAttribute("user");
        String name = (String) session.getAttribute("loginName");
        String admin = (String) session.getAttribute("admin");
        if (patient != null && user == null && admin == null) {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                ServletFileUpload upload = new ServletFileUpload();

                Connection con;
                Statement st;
                ResultSet rs;
                boolean error = false;
                try {
                    FileItemIterator itr = upload.getItemIterator(request);

                    while (itr.hasNext()) {
                        FileItemStream item = itr.next();
                        if (item.isFormField()) {
                            String fieldName = item.getFieldName();
                            InputStream is = item.openStream();
                            byte b[] = new byte[is.available()];
                            is.read(b);
                            String value = new String(b);
                            if (fieldName.equals("permission")) {
                                permission = value;
                            } else if (fieldName.equals("key")) {
                                key = value;
                            } else if (fieldName.equals("profession")) {
                                profession.add(value);
                            } else if (fieldName.equals("speciality")) {
                                speciality.add(value);
                            } else if (fieldName.equals("organization")) {
                                organization.add(value);
                            } else {
                                System.out.println(fieldName + " : " + value);
                            }

                        } else {
                            phrName = item.getName();
                            try {
                                con = Config.getcon();
                                st = con.createStatement();
                                String query = "select * from filedetails where patientID='" + patient + "' and fileName='" + phrName + "'";
                                rs = st.executeQuery(query);
                                if (rs.next()) {
                                    session.setAttribute("msg", "File Name Already Exist..");
                                    //response.sendRedirect("fileUpload.jsp");
                                    error = true;
                                } else {
                                    //Upload File   
                                    if (uploadProcess.encrypt(path, item, patient) == true) {
                                        System.out.println("File Uploaded Successfully");
                                        session.setAttribute("msg", "File Uploaded Successfully");
                                    } else {
                                        session.setAttribute("msg", "File not Uploaded Successfully");
                                        //response.sendRedirect("fileUpload.jsp");
                                        error = true;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Error=" + e);
                                session.setAttribute("msg", "Error : " + e);
                                //response.sendRedirect("fileUpload.jsp");
                                error = true;
                            }
                        }
                    }
                    if (error == false) {
//                        String path = getServletContext().getRealPath("/");
                        File mainFolder = new File(path + File.separator + "Encrypt");
                        if (!mainFolder.exists()) {
                            mainFolder.mkdir();
                        }
                        File patientFolder = new File(mainFolder + File.separator + patient);
                        if (!patientFolder.exists()) {
                            patientFolder.mkdir();
                        }
                        //Write Policy For File
                        String policyFile = "policy" + phrName + ".txt";
                        FileOutputStream fos = new FileOutputStream(patientFolder.getAbsolutePath() + File.separator + policyFile);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(profession);
                        oos.writeObject(speciality);
                        oos.writeObject(organization);
                        
                        //Upload Policy File to cloud
                        DropboxUpload uploadDB = new DropboxUpload();
                        uploadDB.uploadFile(patientFolder, policyFile, StoragePath.getDropboxDir() + patient);
                        DeleteDirectory.delete(patientFolder);
                        
                        //Update FieldDetails
                        try {
                            con = Config.getcon();
                            st = con.createStatement();
                            String query = "insert into filedetails values('" + patient + "','" + name + "','" + phrName + "','" + permission + "')";
                            st.executeUpdate(query);

                            //Update log 
                            query = "select * from log";
                            int noOfFile = 0;
                            rs = st.executeQuery(query);
                            if (rs.next()) {
                                noOfFile = rs.getInt("noOfFile") + 1;
                            }
                            query = "UPDATE log SET noOfFile='" + noOfFile + "'";
                            st.executeUpdate(query);

                            session.setAttribute("msg", "File Uploaded Successfully");
                            //response.sendRedirect("fileUpload.jsp");

                        } catch (Exception e) {
                            System.out.println("Error=" + e);
                            session.setAttribute("msg", "Error : " + e);
                            //response.sendRedirect("fileUpload.jsp");
                        }
                        response.sendRedirect("fileUpload.jsp");
                    } else {
                        response.sendRedirect("fileUpload.jsp");
                    }

                } catch (Exception e) {
                    session.setAttribute("msg", "Error : " + e);
                    response.sendRedirect("fileUpload.jsp");
                }
            }
        } else {
            session.setAttribute("msg", "You Are Not allowed to upload Login First");
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
