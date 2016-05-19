<%-- 
    Document   : home
    Created on : Dec 29, 2015, 7:35:19 PM
    Author     : Saurabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Config.Config" %>
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
                    <%                        if (patient != null || user != null || admin != null) {
                    %>
                    <form action="SendMessage" method="post">
                        <div class="row">
                            <h1 class="page-header">
                                Send Message
                            </h1>
                        </div>
                        <div class="row">
                            <%                                    String msg = (String) session.getAttribute("msg");
                                if (msg != null) {
                            %>
                            <div class="form-group" id="msg">
                                <label class="text-danger"><%=msg%></label>
                            </div>
                            <%
                                    session.removeAttribute("msg");
                                }
                                Connection con;
                                Statement st;
                                ResultSet rs;
                            %>
                        </div>
                        <div class="row" id="patientSelect">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label for="receiverID">Send To</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <select class="form-control" name="receiverID">
                                            <option disabled>-----Patient ID's------</option>
                                            <%
                                                try {
                                                    con = Config.getcon();
                                                    st = con.createStatement();
                                                    String query = "select * from patient";
                                                    rs = st.executeQuery(query);
                                                    if (rs.next()) {
                                                        do {
    //                                                        if (patient.equalsIgnoreCase(rs.getString("patientID")) && patient != null) {
    //                                                            continue;
    //                                                        }
%>
                                            <option value="<%=rs.getString("patientID")%>"><%=rs.getString("patientID")%></option>
                                            <%
                                                    } while (rs.next());
                                                }
                                            %>
                                            <option disabled>-----User ID's-----</option>
                                            <%
                                                query = "select * from user";
                                                rs = st.executeQuery(query);
                                                if (rs.next()) {
                                                    do {
    //                                                    if (user.equalsIgnoreCase(rs.getString("userID")) && user != null) {
    //                                                        continue;
    //                                                    }
%>
                                            <option value="<%=rs.getString("userID")%>"><%=rs.getString("userID")%></option>
                                            <%
                                                    } while (rs.next());
                                                }
                                            %>
                                            <option disabled>-----Admin ID's-----</option>
                                            <%
                                                query = "select * from admin";
                                                rs = st.executeQuery(query);
                                                if (rs.next()) {
                                                    do {
    //                                                    if (admin.equalsIgnoreCase(rs.getString("adminID")) && admin != null)
%>
                                            <option value="<%=rs.getString("adminID")%>"><%=rs.getString("adminID")%></option>
                                            <%
                                                        } while (rs.next());
                                                    }
                                                } catch (Exception e) {
                                                    session.setAttribute("msg", "Error : " + e);
                                                    response.sendRedirect("loginError.jsp");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <!--                                    <div class='input-group'>
                                                                            <span class="input-group-addon">@</span>
                                                                            <input type="text" id="Id" class="form-control" name="receiverID" placeholder="Receiver-ID or Email-ID">
                                                                        </div>-->
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-10">
                                <div class="form-group">
                                    <label>Message</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                        </span>
                                        <textarea class="form-control" rows="7" id="message" name="message"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" align="center">
                                <input type="submit" id="submit" value="Send" class="btn btn-default"/>
                            </div>
                        </div>
                    </form>
                    <%
                        } else {
                            session.setAttribute("msg", "Yor are not Login ....Login First");
                            response.sendRedirect("loginError.jsp");
                        }
                    %>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
