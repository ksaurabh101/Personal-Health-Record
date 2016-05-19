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
                    <form class="form-horizontal" role="form">
                        <div class="row">
                            <div class="page-header">
                                <h1>Remove PHR</h1>
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
                            <div class="form-group well well-sm" id="message">

                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-1" for="key">Key : </label>
                                <div class="col-sm-5">
                                    <input type="text" name="key" class="form-control" id="key" placeholder="Enter Key">
                                </div>
                            </div>
                            <%
                                if (user == null && patient != null && admin == null) {
                                    Connection con;
                                    Statement st;
                                    ResultSet rs;
                                    try {
                                        con = Config.getcon();
                                        st = con.createStatement();
                                        String query = "select * from filedetails where patientID='" + patient + "'";
                                        rs = st.executeQuery(query);
                                        if (rs.next()) {
                                            do {
                            %>
                            <div class="form-group">
                                <table class="table table-striped col-lg-6">
                                    <tbody>
                                        <tr class="col-lg-6">
                                            <td class="col-lg-4" align="left"><%=rs.getString("fileName")%></td>
                                            <td class="col-lg-1" align="right"><input type="button" class="btn btn-default" value="View" name="<%=rs.getString("fileName")%>"></td>
                                            <td class="col-lg-1" align="right"><input type="button" class="btn btn-default" value="Remove" name="<%=rs.getString("fileName")%>"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                            } while (rs.next());
                                        } else {
                                            session.setAttribute("msg", "There is no File Uploaded Yet.");
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
                    </form>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
