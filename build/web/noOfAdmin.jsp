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
            String adminID = "";
            Connection con;
            Statement st;
            ResultSet rs;
            try {
                con=Config.getcon();
                st = con.createStatement();
                String query = "select * from log";
                rs = st.executeQuery(query);
                if (rs.next()) {
                    int noOfAdmin = rs.getInt("noOfAdmin") + 1;
                    adminID = "ADM" + String.format("%05d", noOfAdmin);
                }
                else{
                    adminID = "ADM" + "00001";
                }
            } catch (Exception e) {
                out.println("Error:"+e);
            }
        %>
        <p><%=adminID %></p>
    </body>
</html>
