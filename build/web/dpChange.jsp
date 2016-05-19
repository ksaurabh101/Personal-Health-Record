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
                $("#secondForm").hide();

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
                    <form role="form" class="form-horizontal">
                        <div class="row">
                            <!-- Page Heading -->
                            <h1 class="page-header">
                                Update Access Policy
                            </h1>
                            <%                                String msg = (String) session.getAttribute("msg");
                                String uKey = request.getParameter("key");
                                String fileName = request.getParameter("fileName");
                                session.setAttribute("fileName", fileName);
                                session.setAttribute("uKey", uKey);
                                if (msg != null) {
                            %>
                            <div class="form-group" id="msg">
                                <label class="text-danger"><%=msg%></label>
                            </div>
                            <%
                                    session.removeAttribute("msg");
                                }
                            %>
                        </div>
                    </form>

                    <form id="firstForm" role="form" class="form-horizontal" method="post" action="PolicyChange">
                        <div class="row">
                            <div class="form-group" id="phrName">
                                <label class="control-label col-sm-2" for="key">PHR File Name : </label>
                                <div class="col-sm-6">
                                    <p class="form-control"><%= fileName%></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="key">Key : </label>
                                <div class="col-sm-6">
                                    <p class="form-control"><%= uKey%></p>
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
                                Update Attribute
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
                    <!--first form ->
                </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- /#page-wrapper -->

            </div>
            <!-- /#wrapper -->
        </div>
    </body>

</html>
