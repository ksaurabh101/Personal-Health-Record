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
        <script src="js/fileAccess.js"></script>
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
                            <h1>Doctor Appointment</h1>
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
                            if (user != null && patient == null && admin==null) {
                                Connection con;
                                Statement st;
                                ResultSet rs;
                                try {
                                    con=Config.getcon();
                                    st = con.createStatement();
                                    String query = "select * from appointment where userID='" + user + "'";
                                    rs = st.executeQuery(query);
                        %>
                        <div>
                            <table class="table table-striped">
                                <thead>
                                    <tr class="">
                                        <th class="">Appo. No</th>
                                        <th class="">Patient ID</th>
                                        <th class="">Patient Name</th>
                                        <th class="">Date</th>
                                        <th class="">Time</th>
                                        <th class="">Problem Description</th>
                                        <th class="">Status</th>
                                        <th class="">Change Status</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <%
                            if (rs.next()) {
                                do {
                        %>
                        <div>
                            <table class="table table-striped">
                                <tbody>
                                    <tr class="">
                                        <td class=""><%=rs.getString("appointmentNo")%></td>
                                        <td class=""><%=rs.getString("patientID")%></td>
                                        <td class=""><%=rs.getString("patientName")%></td>
                                        <td class=""><%=rs.getString("date")%></td>
                                        <td class=""><%=rs.getString("time")%></td>
                                        <td class=""><%=rs.getString("problem")%></td>
                                        <td class=""><%=rs.getString("status")%></td>
                                        <td class="">
                                            <div class="dropdown">
                                                <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown">Change Status
                                                    <span class="caret"></span></button>
                                                <ul class="list-group dropdown-menu">
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="status" id="<%=rs.getString("appointmentNo")%>" value="Approved">Approved
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="status" id="<%=rs.getString("appointmentNo")%>" value="Missed">Missed
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="status" id="<%=rs.getString("appointmentNo")%>" value="Cancel">Cancel
                                                            </label>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>         
                        </div>
                        <%
                                        } while (rs.next());
                                    } else {
                                        session.setAttribute("msg", "There is no Appointment.");
                                        response.sendRedirect("loginError.jsp");
                                    }
                                } catch (Exception e) {
                                    session.setAttribute("msg", "Error..Try Again");
                                    response.sendRedirect("loginError.jsp");
                                }
                            } else {
                                session.setAttribute("msg", "You are not login.. Login first");
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
