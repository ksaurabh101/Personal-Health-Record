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
                $("#DOB").click(function () {
                    $("#DOB").datepicker();
                });
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                    }
                };
                xhttp.open("GET", "noOfUser.jsp", true);
                xhttp.send();

                $("#submit").click(function () {
                    var password = $("#password").val();
                    var rPassword = $("#rPassword").val();
                    var isValid = true;
                    for (i = 0; i < 14; i++) {
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
                    xhttp.open("GET", "GenerateKey?register=user", true);
                    xhttp.send();
                });
                $("#staff").hide();
                $("#physicians").hide();
                $("#insurance").hide();
                $("input").click(function () {
                    var value = $(this).attr('value');
                    if (value == 'Staff') {
                        $("#staff").show();
                        $("#physicians").hide();
                        $("#insurance").hide();
                    }
                    if (value == 'Physician') {
                        $("#staff").hide();
                        $("#physicians").show();
                        $("#insurance").hide();
                    }
                    if (value == 'Insurance') {
                        $("#staff").hide();
                        $("#physicians").hide();
                        $("#insurance").show();
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
                    <form role="form" action="userRegistration" method="post">
                        <div class="row">
                            <!-- Page Heading -->
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
                                    <label for="userId">User's ID</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">@</span>
                                        <p class="text-danger form-control" id="demo"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="uname">User's Name</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input type="text" id="name" class="form-control" name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="DOB">Date Of Birth</label>
                                    <div class='input-group date' id='datetimepicker1'>
                                        <input type='text' class="form-control" id="DOB" name="dob"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Gender">Gender</label>
                                    <br>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="patientGender" value="Male" checked>Male
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="gender" value="FeMale">Female
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label for="mStatus">Marital Status</label>
                                    <br>
                                    <label class="radio-inline">
                                        <input type="radio" name="mStatus" id="patientMStatus" value="Male" checked>Married
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="mStatus" id="mStatus" value="FeMale">Unmarried
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label for="phNumber">Phone Number</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-phone"></span>
                                        </span>
                                        <input type="text" class="form-control" id="phNumber" name="pNumber"/>
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
                                    <label>Address</label>
                                    <div class='input-group'>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-home"></span>
                                        </span>
                                        <textarea class="form-control" rows="3" id="address" name="address"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-1">

                            </div>
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <div class="panel-group" id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                                        Profession
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse1" class="panel-collapse collapse in">
                                                <ul class="list-group">
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="profession" value="Staff">Staff
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="profession" value="Physicians">Physician
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="profession" value="Insurance">Insurance
                                                            </label>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                                                        Speciality
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse2" class="panel-collapse collapse">
                                                <div id="staff">
                                                    <ul class="list-group">
                                                        <li class="list-group-item"> 
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="NURSING SISTER">NURSING SISTER
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="O.T. ASSISTANT">O.T. ASSISTANT
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="MIDWIFE">MIDWIFE
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="HEALTH VISITOR">HEALTH VISITOR
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="RADIOGRAPHER">RADIOGRAPHER
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="DRESSER">DRESSER
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="LABORATORY TECHNICIAN">LABORATORY TECHNICIAN
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="PHARMACIST">PHARMACIST
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="CLERK">CLERK
                                                                </label>
                                                            </div>
                                                        </li><li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="THERAPIST">THERAPIST
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="DIETICIAN">DIETICIAN
                                                                </label>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div id="physicians">
                                                    <ul class="list-group">

                                                        <!-- Physician List -->
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Allergy and asthma">Allergy and asthma
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Anesthesiology">Anesthesiology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Cardiology">Cardiology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Dermatology">Dermatology 
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Endocrinology">Endocrinology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Gastroenterology">Gastroenterology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="General surgery">General surgery
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Hematology">Hematology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Immunology">Immunology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Nephrology">Nephrology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Neurology">Neurology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Obstetrics/gynecology">Obstetrics/gynecology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Oncology">Oncology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Ophthalmology">Ophthalmology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Orthopedics">Orthopedics
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Otorhinolaryngology">Otorhinolaryngology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Psychiatry">Psychiatry
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Pulmonary">Pulmonary
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Radiology">Radiology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Rheumatology">Rheumatology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Urology">Urology
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Dentist">Dentist
                                                                </label>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div id="insurance">
                                                    <ul class="list-group">
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Life Insurance">Life Insurance
                                                                </label>
                                                            </div>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <div class="radio">
                                                                <label>
                                                                    <input type="radio" name="speciality" value="Health Insurance">Health Insurance
                                                                </label>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                                                        Organization
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse3" class="panel-collapse collapse">
                                                <ul class="list-group">
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="organization" value="Hospital A">Hospital A
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="organization" value="Hospital B">Hospital B
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="organization" value="Government">Government
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" name="organization" value="Private">Private
                                                            </label>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
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
                            </div>
                        </div>
                        <!-- /.row -->
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
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
