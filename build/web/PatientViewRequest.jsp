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
        <script>
            $(document).ready(function () {
                $("input").click(function () {
                    if ($(this).attr('name') == "status")
                    {
                        var value = $(this).attr('value');
                        var requestNo = $(this).attr('id');
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
//                    document.getElementById("message").innerHTML = xhttp.responseText;
//                    $("#message").show();
                                alert(xhttp.responseText);
                            }
                        };
                        xhttp.open("GET", "UpdateRequestStatus?action=Status&value=" + value + "&requestNo=" + requestNo, true);
                        xhttp.send();
                        //window.location.href = "PatientViewRequest.jsp";
                    }
                });
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
                    <div class="row">
                        <div class="page-header">
                            <h1>View Request</h1>
                        </div>
                    </div>
                    <div class="row">
                        <%                                String msg = (String) session.getAttribute("msg");
                            if (msg != null) {
                        %>
                        <div class="form-group" id="msg">
                            <label class="text-danger"><%=msg%></label>
                        </div>
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
                                String query = "select * from requestotp where patientID='" + patient + "' order by date desc";
                                rs = st.executeQuery(query);
                                if (rs.next()) {
                                    do {
                    %>
                    <div class="row">
                        <div class="col-lg-5">
                            <table class="table table-bordered">
                                <tbody>
                                    <tr class="">
                                        <th class="">Request No :</th>
                                        <td class=""><%=rs.getString("requestNo")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Name :</th>
                                        <td class=""><%=rs.getString("rName")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Email :</th>
                                        <td class=""><%=rs.getString("rEmail")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Date Of Birth :</th>
                                        <td class=""><%=rs.getString("dob")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Gender :</th>
                                        <td class=""><%=rs.getString("gender")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Phone No :</th>
                                        <td class=""><%=rs.getString("pNumber")%></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-lg-1"></div>
                        <div class="col-lg-5">
                            <table class="table table-bordered">
                                <tbody>
                                    <tr class="">
                                        <th class="">Mobile No :</th>
                                        <td class=""><%=rs.getString("mNumber")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Address :</th>
                                        <td class=""><%=rs.getString("address")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Note :</th>
                                        <td class=""><%=rs.getString("note")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Status :</th>
                                        <td class=""><%=rs.getString("status")%></td>
                                    </tr>
                                    <tr class="">
                                        <th class="">Change Status :</th>
                                        <td class="">
                                            <div class="dropdown">
                                                <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown">Change Status
                                                    <span class="caret"></span></button>
                                                <ul class="list-group dropdown-menu">
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="status" id="<%=rs.getString("requestNo")%>" value="Approved">Approve
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="status" id="<%=rs.getString("requestNo")%>" value="Canceled">Cancel
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
                    </div>
                    <div class="row">
                        <p class="well well-sm"></p>
                    </div>
                    <%
                                    } while (rs.next());
                                } else {
                                    session.setAttribute("msg", "There is no Request.");
                                    response.sendRedirect("loginError.jsp");
                                }
                            } catch (Exception e) {
                                session.setAttribute("msg", "Error..Try Again");
                                response.sendRedirect("PatientViewPHR.jsp");
                            }
                        } else {
                            session.setAttribute("msg", "You are not login.. Login first");
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
