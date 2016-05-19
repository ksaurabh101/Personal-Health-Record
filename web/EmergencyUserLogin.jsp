<%-- 
    Document   : home
    Created on : Dec 29, 2015, 7:35:19 PM
    Author     : Saurabh
--%>

<%@page import="path.StoragePath"%>
<%@page import="auth.AuthChek"%>
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
        <script>
            $(document).ready(function () {
                $("input").click(function () {
                    var fileName = $(this).attr('name');
                    var patientID = $(this).attr('id');
                    if ($(this).attr('value') == "View")
                    {
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                                alert(xhttp.responseText);
                            }
                        };
                        xhttp.open("GET", "EmergencyUserLogin?fileName=" + fileName + "&patientID=" + patientID, true);
                        xhttp.send();
                    }
                })
            });
        </script>
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
                                <h1>View PHR</h1>
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
                                String requestNo = request.getParameter("requestNo");
                                String patientID = request.getParameter("patientID");
                                String name = request.getParameter("name");
                                String otp = request.getParameter("otp");

                                if (user == null && patient == null && admin == null) {
                                    Connection con;
                                    Statement st;
                                    ResultSet rs;
                                    try {
                                        con = Config.getcon();
                                        st = con.createStatement();
                                        String query = "select * from requestotp where requestNo='" + requestNo + "' or rEmail='" + requestNo + "'";
                                        rs = st.executeQuery(query);
                                        if (rs.next()) {
                                            String oneTimePass = rs.getString("otp");
                                            if (oneTimePass.equals("null")) {
                                                session.setAttribute("msg", "You are not Approved Yet..Try again");
                                                response.sendRedirect("breakGlass.jsp");
                                            } else {
                                                if (oneTimePass.equals(otp)) {
                                                    if (patientID.equalsIgnoreCase(rs.getString("patientID"))) {
                                                        if (name.equalsIgnoreCase(rs.getString("patientName"))) {

                                                            query = "select * from filedetails where patientID='" + patientID + "'";
                                                            rs = st.executeQuery(query);
                                                            if (rs.next()) {
                                                                do {
                                                                    String permission = rs.getString("permission");
//                                                                    String path = getServletContext().getRealPath("/");
                                                                    //Storage path
                                                                    String path = StoragePath.getPath();

                                                                    AuthChek obj = new AuthChek(path, patientID, rs.getString("fileName"));
                                                                    if (!permission.equals("Sensitive") && obj.emergencyCheck()) {
                            %>
                            <div class="form-group">
                                <table class="table table-striped col-lg-6 ">
                                    <tbody>
                                        <tr class="col-lg-6">
                                            <td class="col-lg-4" align="left"><%=rs.getString("fileName")%></td>
                                            <td class="col-lg-1" align="right"><input type="button" class="btn btn-default" id="<%=patientID%>" value="View" name="<%=rs.getString("fileName")%>"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                                                    }
                                                                } while (rs.next());
                                                                String value = "null";
                                                                String status = "Done";
                                                                query = "update requestotp set otp='" + value + "', status='" + status + "' where requestNo='" + requestNo + "' or rEmail='" + requestNo + "'";
                                                                st.executeUpdate(query);
                                                            } else {
                                                                session.setAttribute("msg", "There is no File Uploaded Yet.");
                                                                response.sendRedirect("loginError.jsp");
                                                            }

                                                        } else {
                                                            session.setAttribute("msg", "Patient Name is Not Currect");
                                                            response.sendRedirect("breakGlass.jsp");
                                                        }
                                                    } else {
                                                        session.setAttribute("msg", "Patient ID is Not Currect");
                                                        response.sendRedirect("breakGlass.jsp");
                                                    }
                                                } else {
                                                    session.setAttribute("msg", "OTP is Not Currect");
                                                    response.sendRedirect("breakGlass.jsp");
                                                }
                                            }
                                        } else {
                                            session.setAttribute("msg", "Incorrect Request No");
                                            response.sendRedirect("breakGlass.jsp");
                                        }
                                    } catch (Exception e) {
                                        session.setAttribute("msg", "Login Error..Try Again" + e);
                                        response.sendRedirect("breakGlass.jsp");
                                    }
                                } else {
                                    session.setAttribute("msg", "Already Login....Logout First");
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
