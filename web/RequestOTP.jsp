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
                $("#DOB").click(function () {
                    $("#DOB").datepicker();
                });
                $("#submit").click(function () {
                    var isValid = true;
                    for (i = 0; i < 10; i++) {
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
                $('[data-toggle="popover"]').popover();
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
                    <form role="form" action="RequestOTP" method="post">
                        <div class="row">
                            <h1 class="page-header">
                                Request Form
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
                                %>
                                <div class="form-group">
                                    <label for="emergencyUserName">Name</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input type="text" id="emergencyUserName" class="form-control" name="emergencyUserName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email Address</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                        </span>
                                        <input type="email" class="form-control" id="email" name="email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patientDOB">Date Of Birth</label>
                                    <div class='input-group date' id='datetimepicker1'>
                                        <input type='text' class="form-control" id="DOB" name="dob" placeholder="DD/MM/YYYY"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patientGender">Gender</label>
                                    <br>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="patientGender" value="Male" checked>Male
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="gender" value="FeMale">Female
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label for="patientPhnumber">Phone Number</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-phone-alt"></span>
                                        </span>
                                        <input type="text" class="form-control" id="phNumber" name="pNumber"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patientPhnumber">Mobile Number</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-phone"></span>
                                        </span>
                                        <input type="text" class="form-control" id="phNumber" name="mNumber"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-1">

                            </div>
                            <div class="col-lg-5">

                                <div class="form-group">
                                    <label>Address</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-home"></span>
                                        </span>
                                        <textarea class="form-control" rows="3" id="address" name="address"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patientId">Patient ID</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <input type="text" id="Id" class="form-control" name="patientID" placeholder="Patient-ID">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patientName">Patient Name</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input type="text" id="name" class="form-control" name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Note</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-info-sign"></span>
                                        </span>
                                        <textarea class="form-control" rows="3" id="note" name="note"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" align="center">
                                <input type="reset" id="resetButton" value="Reset" class="btn btn-default"/>
                                <input type="submit" id="submit" value="Submit" class="btn btn-default"/>
                            </div>
                        </div>
                    </form>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
