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
                //$("[type=checkbox]").bootstrapToggle();

                $("#secondForm").hide();
                $("#checkKey").hide();
                $("#selectText").click(function () {
                    $("#allFile").hide();
                    $("#allText").show();
                    $("#secondForm").hide();
                    $("#firstForm").show();
                    $("#phrName").show();
                });

                $("#selectFile").click(function () {
                    $("#allFile").show();
                    $("#allText").hide();
                    $("#secondForm").show();
                    $("#firstForm").hide();
                    $("#phrName").show();
                });
                $("#submit").click(function () {
                    var isValid = true;
                    for (i = 0; i < 50; i++) {
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
            function keyCheck()
            {
                var key = $("#key").val();
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", "KeyCheck?key=" + key, true);
                xhttp.send();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        var result = xhttp.responseText;
                        //document.getElementById("keyNotify").innerHTML = result;
                        var a = "Key is not Correct..";
                        if (result.localeCompare(a) == 1)
                        {
                            alert(result);
                        }
                    }
                };
            }
            function keyCheck1()
            {
                var key = $("#key1").val();
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", "KeyCheck?key=" + key, true);
                xhttp.send();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        var result = xhttp.responseText;
                        //document.getElementById("keyNotify").innerHTML = result;
                        var a = "Key is not Correct..";
                        if (result.localeCompare(a) == 1)
                        {
                            alert(result);
                        }
                    }
                };
            }
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
                    <form role="form" class="form-horizontal">
                        <div class="row">
                            <!-- Page Heading -->
                            <h1 class="page-header">
                                File Upload
                            </h1>
                            <%                                String msg = (String) session.getAttribute("msg");
                                if (msg != null) {
                            %>
                            <div class="form-group" id="msg" style="margin-left: 10px">
                                <label class="text-danger"><%=msg%></label>
                            </div>
                            <%
                                    session.removeAttribute("msg");
                                }
                            %>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="typeOfFile">Data Type : </label>
                                <div class="col-sm-6">
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="typeOfFile" value="Text" id="selectText">Text
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="typeOfFile" value="File" id="selectFile">File
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                    <form id="firstForm" role="form" class="form-horizontal" method="post" action="textFileUpload">
                        <div class="row">
                            <div class="form-group" id="phrName">
                                <label class="control-label col-sm-2" for="key">PHR Name : </label>
                                <div class="col-sm-6">
                                    <input type="text" name="phrName" class="form-control"  placeholder="Enter PHR Name">
                                </div>
                            </div>
                            <div class="form-group" id="allText">
                                <label class="control-label col-sm-2" for="file">PHR Data : </label>
                                <div class="col-sm-6">
                                    <textarea class="form-control" rows="5" name="phrData"></textarea>
                                </div>
                            </div>
                            <div class="form-group" id="checkKey">
                                <label class="control-label col-sm-2" for="file">Check Key : </label>
                                <div class="col-sm-6">
                                    <h4 id="keyNotify" class="text-danger"></h4>
                                </div>  
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="key">Key : </label>
                                <div class="col-sm-6">
                                    <input type="text" name="key" class="form-control" id="key" placeholder="Enter Key" onblur="keyCheck()">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="permission">Permission : </label>
                                <div class="col-sm-6">
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="permission" value="ReadOnly">Read Only
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="permission" value="Read&WriteBoth">Read & Write Both
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="permission" value="Sensitive">Sensitive
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <!-- Page Heading -->
                            <h1 class="page-header">
                                Attribute Selection
                            </h1>
                            <%@include file="AllAttribute.jsp" %>
                        </div>
                        <!--row -->
                        <div class="row">
                            <div class="col-lg-12" align="center">
                                <input type="reset" id="resetButton" value="Reset" class="btn btn-default"/>
                                <input type="submit" id="submit" value="Submit" class="btn btn-default"/>
                            </div>
                        </div>
                        <!--row -->
                    </form>
                    <!--first form -->

                    <form id="secondForm" role="form" class="form-horizontal" method="post" action="fileUpload" enctype="multipart/form-data">
                        <div class="row">
                            <div class="form-group" id="allFile">
                                <label class="control-label col-sm-2" for="file">File : </label>
                                <div class="col-sm-6">
                                    <input type="file" id="file" name="file">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="key">Key : </label>
                                <div class="col-sm-6">
                                    <input type="text" name="key" class="form-control" id="key1" placeholder="Enter Key" onblur="keyCheck1()">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="permission">Permission : </label>
                                <div class="col-sm-6">
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="permission" value="ReadOnly">Read Only
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="permission" value="Read&WriteBoth">Read & Write Both
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <label>
                                            <input type="radio" name="permission" value="Sensitive">Sensitive
                                        </label>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <!-- Page Heading -->
                            <h1 class="page-header">
                                Attribute Selection
                            </h1>
                            <%@include file="AllAttributes.jsp" %>
                        </div>
                        <!--row -->
                        <div class="row">
                            <div class="col-lg-12" align="center">
                                <input type="reset" id="resetButton" value="Reset" class="btn btn-default"/>
                                <input type="submit" id="submit" value="Submit" class="btn btn-default"/>
                            </div>
                        </div>
                        <!--row -->
                    </form>
                    <!--Second form -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
