<%-- 
    Document   : home
    Created on : Dec 29, 2015, 7:35:19 PM
    Author     : Saurabh
--%>

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
                $("#submit").click(function () {
                    var isValid = true;
                    for (i = 0; i < 2; i++) {
                        if (document.forms[0].elements[i].value == "") {
                            alert("All Fields are required. Please fill this field");
                            document.forms[0].elements[i].focus();
                            isValid = false;
                            break;
                        }
                    }
                    if (isValid == false)
                    {
                        return false;
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

                    <%                        if (user == null && patient == null && admin==null) {
                    %>
                    <!-- Page Heading -->
                    <form role="form" action="ForgotOTP" method="post">
                        <div class="row">
                            <h1 class="page-header">
                                Forgot OTP
                            </h1>
                            <div class="col-lg-5">
                                <%
                                    String msg = (String) session.getAttribute("msg");
                                    if (msg != null) {
                                %>
                                <div class="form-group" id="msg">
                                    <label class="text-danger"><%=msg%></label>
                                </div>
                                <%
                                        session.removeAttribute("msg");
                                    }
                                %>
                                <div class="form-group">
                                    <label for="requestNo">Request No</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <input type="text" id="requestNo" class="form-control" name="requestNo" placeholder="Request-No or Email-ID">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patientId">Patient ID</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <input type="text" id="patientID" class="form-control" name="patientID" placeholder="Patient-ID">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="submit" id="submit" value="Go" class="btn btn-default"/>
                                </div>
                                
                            </div>
                        </div>
                    </form>
                    <!-- /.row -->
                    <%
                        }
                    else{
                        session.setAttribute("msg", "Someone is Login ...Logout First");
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
