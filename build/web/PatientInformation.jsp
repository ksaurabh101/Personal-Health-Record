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
        <script>
            $(document).ready(function () {
                $("#show").click(function () {
                    var patientID = $("#Id").val();
                    if (patientID.length == 0)
                    {
                        alert("First Select PatientId.");
                    }
                    else {
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                                document.getElementById("demo").innerHTML = xhttp.responseText;
                            }
                        };
                        xhttp.open("GET", "PatientInfo.jsp?patientID=" + patientID, true);
                        xhttp.send();
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

                    <!-- Page Heading -->
                    <form role="form" action="patientLogin" method="post">
                        <div class="row">
                            <h1 class="page-header">
                                Patient Information
                            </h1>
                            <div class="col-lg-5">
                                <%                                    String msg = (String) session.getAttribute("msg");
                                    if (msg != null) {
                                %>
                                <div class="form-group" id="msg">
                                    <label class="text-danger"><%=msg%></label>
                                </div>
                                <%
                                        session.removeAttribute("msg");
                                    }
                                    if (user != null && patient == null && admin == null) {
                                        Connection con;
                                        Statement st;
                                        ResultSet rs;
                                %>
                                <div class="form-group">
                                    <label for="patientId">Patient ID</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <select class="form-control" name="patientID" id="Id">
                                            <%
                                                try {
                                                    con = Config.getcon();
                                                    st = con.createStatement();
                                                    String query = "select * from patient";
                                                    rs = st.executeQuery(query);
                                                    if (rs.next()) {
                                                        do {
                                            %>
                                            <option value="<%=rs.getString("patientID")%>"><%=rs.getString("patientID")%></option>
                                            <%
                                                        } while (rs.next());
                                                    }
                                                } catch (Exception e) {
                                                    session.setAttribute("msg", "Error : " + e);
                                                    response.sendRedirect("loginError.jsp");
                                                }
                                            %>
                                        </select>
                                        <!--<input type="text" id="Id" class="form-control" name="patientID" placeholder="Patient-ID">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-5">
                                <br/>
                                <div class="form-group">
                                    <input type="button" id="show" value="Show" class="btn btn-default"/>
                                </div>
                            </div>
                            <%
                                } else {
                                    session.setAttribute("msg", "You are not login.. Login first");
                                    response.sendRedirect("loginError.jsp");
                                }
                            %>
                        </div>
                    </form>
                    <!-- /.row -->
                    <div id="demo" class="col-lg-6">
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
