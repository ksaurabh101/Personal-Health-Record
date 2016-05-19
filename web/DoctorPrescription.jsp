<%-- 
    Document   : home
    Created on : Dec 29, 2015, 7:35:19 PM
    Author     : Saurabh
--%>

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
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/sb-admin.css" rel="stylesheet">
        <link href="css/plugins/morris.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/timeOut.js"></script>
    </head>

    <body>

        <div id="wrapper">
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Top Menu Items -->

                <%@include file="topMenuItem.jsp" %>

                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->

                <%@include file="sideBar.jsp" %>

                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">
                    <div class="row">
                        <div class="page-header">
                            <h1>Doctor's Prescription</h1>
                        </div>
                    </div>
                    <div class="row">
                        <%                                String msg = (String) session.getAttribute("msg");
                            if (msg != null) {
                        %>
                        <div class="form-group" id="msg">
                            <label class="text-danger"><%=msg%></label>
                        </div>
                        <%
                                session.removeAttribute("msg");
                            }
                        %>
                        <%
                            if (user == null && patient != null && admin == null) {
                                Connection con;
                                Statement st;
                                ResultSet rs;
                                try {
                                    con = Config.getcon();
                                    st = con.createStatement();
                                    String query = "select * from doctorPrescription where patientID='" + patient + "'";
                                    rs = st.executeQuery(query);
                        %>
                        <div>
                            <table class="table table-striped col-lg-12">
                                <!--
                                <thead>
                                    <tr class="col-lg-12">
                                        <th class="col-lg-2">User ID</th>
                                        <th class="col-lg-2">User Name</th>
                                        <th class="col-lg-1">Date</th>
                                        <th class="col-lg-1">Time</th>
                                        <th class="col-lg-6">Details</th>
                                    </tr>
                                </thead>
                                -->
                                <%
                                    if (rs.next()) {
                                        do {
                                %>
                                <tbody>
                                    <tr class="col-lg-12">
                                        <td class="col-lg-2"><%=rs.getString("userID")%></td>
                                        <td class="col-lg-2"><%=rs.getString("userName")%></td>
                                        <td class="col-lg-1"><%=rs.getString("date")%></td>
                                        <td class="col-lg-1"><%=rs.getString("time")%></td>
                                        <td class="col-lg-6"><%=rs.getString("details")%></td>
                                    </tr>
                                </tbody>
                                <%
                                    } while (rs.next());
                                %>
                            </table>         
                        </div>
                        <%
                                    } else {
                                        session.setAttribute("msg", "There is no Prescription.");
                                        response.sendRedirect("loginError.jsp");
                                    }
                                } catch (Exception e) {
                                    session.setAttribute("msg", "Error..Try Again");
                                    response.sendRedirect("loginError.jsp");
                                }
                            } else {
                                session.setAttribute("msg", "You are not Patient ... Login first");
                                response.sendRedirect("loginError.jsp");
                            }
                        %>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
