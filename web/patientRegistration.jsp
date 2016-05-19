<%-- 
    Document   : home
    Created on : Dec 29, 2015, 7:35:19 PM
    Author     : Saurabh
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                    }
                };
                xhttp.open("GET", "noOfPatient.jsp", true);
                xhttp.send();

                $("#submit").click(function () {
                    var password = $("#password").val();
                    var rPassword = $("#rPassword").val();
                    var isValid = true;
                    for (i = 0; i < 13; i++) {
                        if (document.forms[0].elements[i].value == "") {
                            alert("All Fields are required. Please fill this field");
                            document.forms[0].elements[i].focus();
                            isValid = false;
                            break;
                        }
                    }
                    if (password != rPassword)
                    {
                        alert("Password is not Matching..");
                        $("#rPassword").focus();
                        $("#rPassword").css(".bg-warning");
                        isValid = false;
                    }
                    var key = $("#key").val();
                    if (key.size() > 16)
                    {
                        alert("Size is greater than 16..");
                        $("#key").focus();
                        $("#key").css(".bg-warning");
                        isValid = false;
                    }
                    if (key.size() < 16)
                    {
                        alert("Size is less than 16..");
                        $("#key").focus();
                        $("#key").css(".bg-warning");
                        isValid = false;
                    }
                    if (!isCharsInBag(password, "abcdefghijklmnopqrstuvwxyz"))
                    {
                        alert("Password has no Lowercase Character");
                        $("#password").focus();
                        $("#password").css(".bg-warning");
                        isValid = false;
                    }
                    if (!isCharsInBag(password, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
                    {
                        alert("Password has no Uppercase Character");
                        $("#password").focus();
                        $("#password").css(".bg-warning");
                        isValid = false;
                    }
                    if (!isCharsInBag(password, "0123456789"))
                    {
                        alert("Password has no Number Character");
                        $("#password").focus();
                        $("#password").css(".bg-warning");
                        isValid = false;
                    }
                    if (isValid == false)
                    {
                        return false;
                    }
                });
                function isCharsInBag(string, bag) {
                    var i, flag;
                    flag = 0;
                    for (i = 0; i < string.length; i++) {
                        var charval = string.charAt(i);
                        if (bag.indexOf(charval) == -1) {
                            continue;
                        }
                        else {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1) {
                        return true;
                    }
                    else {
                        return false;

                    }
                    return false;
                }
                $('[data-toggle="popover"]').popover();
                $("#generateKEY").click(function () {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            document.getElementById("getKey").innerHTML = xhttp.responseText;
                        }
                    };
                    xhttp.open("GET", "GenerateKey?register=patient", true);
                    xhttp.send();
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
                    <form role="form" action="patientRegistration" method="post">
                        <div class="row">
                            <h1 class="page-header">
                                Registration Form
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
                                    <label for="patientId">Patient ID</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <p class="text-danger form-control" id="demo"></p>
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
                                    <label for="patientMStatus">Marital Status</label>
                                    <br>
                                    <label class="radio-inline">
                                        <input type="radio" name="mStatus" id="patientMStatus" value="Married" checked>Married
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="mStatus" id="mStatus" value="Unmarried">Unmarried
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
                                    <label for="email">Email Address</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                        </span>
                                        <input type="email" class="form-control" id="email" name="email">
                                    </div>
                                </div>
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
                                    <label for="disease">Disease</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-grain"></span>
                                        </span>
                                        <input type="text" id="disease" class="form-control" name="disease">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pwd">Password</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                        </span>
                                        <input type="password" class="form-control" id="password" name="password" data-placement="top" data-toggle="popover" data-trigger="hover" data-content="At least 8 characters long.
                                               Contains A, B, C..., a,b,c...,
                                               0,1,2....">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pwd">Re-Enter Password</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                        </span>
                                        <input type="password" class="form-control" id="rPassword" name="rPassword">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="key">Key</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                        </span>
                                        <p class="text-danger form-control" id="getKey" data-placement="top" data-toggle="popover" data-trigger="hover" data-content="Generate Key & Save It..">
                                            Click Get Key Button
                                        </p>
                                        <!--
                                        <input type="text" class="form-control" id="key" name="key" data-placement="top" data-toggle="popover" data-trigger="hover" data-content="Should be 16 characters long.">
                                        -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" align="center">
                                <!--
                                <input type="button" id="generateID" value="Get-ID" class="btn btn-default"/>
                                -->
                                <input type="button" id="generateKEY" value="Get Key" class="btn btn-default"/>
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
