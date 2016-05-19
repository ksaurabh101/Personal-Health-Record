<%-- 
    Document   : home
    Created on : Dec 29, 2015, 7:35:19 PM
    Author     : Saurabh
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Config.Config" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <title>Personal Health Record</title>
    </head>

    <body >
        <div class="container-fluid">
            <%
                String patient = (String) session.getAttribute("patient");
                String patientId = request.getParameter("patientID");
                String user = (String) session.getAttribute("user");
                String admin = (String) session.getAttribute("admin");
                if (user != null && patient == null && admin == null) {
                    Connection con;
                    Statement st;
                    ResultSet rs;
                    try {
                        con = Config.getcon();
                        st = con.createStatement();
                        String query = "select * from patient where patientID='" + patientId + "'";
                        rs = st.executeQuery(query);

                        if (rs.next()) {
            %>
            <table class="table table-striped">
                <tbody>
                    <tr class="">
                        <th class="">Patient ID</th>
                        <td class=""><%=rs.getString("patientID")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Patient Name</th>
                        <td class=""><%=rs.getString("name")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Date Of Birth</th>
                        <td class=""><%=rs.getString("dob")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Gender</th>
                        <td class=""><%=rs.getString("gender")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Marital Status</th>
                        <td class=""><%=rs.getString("mStatus")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Phone Number</th>
                        <td class=""><%=rs.getString("pNumber")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Mobile Number</th>
                        <td class=""><%=rs.getString("mNumber")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Email</th>
                        <td class=""><%=rs.getString("email")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Address</th>
                        <td class=""><%=rs.getString("address")%></td>
                    </tr>
                    <tr class="">
                        <th class="">Disease</th>
                        <td class=""><%=rs.getString("disease")%></td>
                    </tr>
                </tbody>
            </table>
            <%
            } else {
            %>
            <h4 class="text-danger">There is no Patient With This Id.</h4>
            <%
                }
            } catch (Exception e) {
            %>
            <h4 class="text-danger">Error..Try Again.</h4>
            <%
                }
            } else {
            %>
            <h4 class="text-danger">You are not Login... Login First.</h4>
            <%
                }
            %>
        </div>
    </body>

</html>
