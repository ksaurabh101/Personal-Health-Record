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
                    <form role="form" class="form-horizontal" action="UserWritePHR" method="post">
                        <div class="row">
                            <!-- Page Heading -->
                            <h1 class="page-header">
                                File Upload
                            </h1>
                            <%                                String msg = (String) session.getAttribute("msg");
                                if (msg != null) {
                            %>
                            <div class="form-group" id="msg">
                                <label class="text-danger"><%=msg%></label>
                            </div>
                            <%
                                    session.removeAttribute("msg");
                                }
                                String fileName = request.getParameter("fileName");
                                int index = fileName.indexOf(" ");
                                String id = fileName.substring(0, index).trim();
                                String fName = fileName.substring(index).trim();
                                session.setAttribute("fileName", fileName);
                            %>
                            <div class="form-group" id="id">
                                <label class="control-label col-sm-2" for="id">Patient ID : </label>
                                <div class="col-sm-6">
                                    <p class="form-control"><%=id%></p>
                                </div>
                            </div>
                            <div class="form-group" id="phrName">
                                <label class="control-label col-sm-2" for="phrName">PHR Name : </label>
                                <div class="col-sm-6">
                                    <p class="form-control"><%=fName%></p>
                                </div>
                            </div>
                            <div class="form-group" id="allText">
                                <label class="control-label col-sm-2" for="file">PHR Data : </label>
                                <div class="col-sm-6">
                                    <textarea class="form-control" rows="6" name="phrData" placeholder="Update PHR"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-9" align="center">
                                <input type="reset" id="resetButton" value="Reset" class="btn btn-default"/>
                                <input type="submit" id="submit" value="Submit" class="btn btn-default"/>
                            </div>
                        </div>
                        <!--row -->
                    </form>
                    <!--first form -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
