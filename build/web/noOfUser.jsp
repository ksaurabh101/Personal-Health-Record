<%-- 
    Document   : noOfPatient
    Created on : Jan 1, 2016, 11:19:09 PM
    Author     : Saurabh
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Config.Config" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String patientID = "";
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con = Config.getcon();
                st = con.createStatement();
                String query = "select * from log";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    int noOfUser = rs.getInt("noOfUser") + 1;
                    patientID = "USER" + String.format("%05d", noOfUser);
                } else {
                    patientID = "USER" + "00001";
                }
            } catch (Exception e) {
                out.println("Error:" + e);
            }
        %>
        <p><%=patientID%></p>
    </body>
</html>
