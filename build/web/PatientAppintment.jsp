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
                            <h1>Patient Appointment</h1>
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
                                    String query = "select * from appointment where patientID='" + patient + "'";
                                    rs = st.executeQuery(query);
                        %>
                        <div>
                            <table class="table table-striped col-lg-9">
                                <thead>
                                    <tr class="col-lg-9">
                                        <th class="col-lg-2">Appointment No</th>
                                        <th class="col-lg-2">Doctor ID</th>
                                        <th class="col-lg-2">Doctor Name</th>
                                        <th class="col-lg-1">Date</th>
                                        <th class="col-lg-1">Time</th>
                                        <th class="col-lg-1">Status</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <%
                            if (rs.next()) {
                                do {
                        %>
                        <div>
                            <table class="table table-striped col-lg-9">
                                <tbody>
                                    <tr class="col-lg-9">
                                        <td class="col-lg-2"><%=rs.getString("appointmentNo")%></td>
                                        <td class="col-lg-2"><%=rs.getString("userID")%></td>
                                        <td class="col-lg-2"><%=rs.getString("userName")%></td>
                                        <td class="col-lg-1"><%=rs.getString("date")%></td>
                                        <td class="col-lg-1"><%=rs.getString("time")%></td>
                                        <td class="col-lg-1"><%=rs.getString("status")%></td>
                                    </tr>
                                </tbody>
                            </table>         
                        </div>
                        <%
                            } while (rs.next());
                        } else {
                            %>
                        <div class="form-group">
                            <label class="text-danger">There is no appointment...Make an appointment</label>
                        </div>
                        <%
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
                    <div class="row">
                        <a href="RequestAppointment.jsp" class="btn btn-default">New Appointment</a>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
