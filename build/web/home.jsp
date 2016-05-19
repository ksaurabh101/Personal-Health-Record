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
                setTimeout(function () {
                    $('#msg').fadeOut('fast');
                }, 1000);
            });
        </script>
    </head>

    <body >


        <div id="wrapper">
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Top Menu Items -->

                <%@include file="topMenuItem.jsp" %>

                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->

                <%@include file="sideBar.jsp" %>

                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper" style="background-color: gainsboro">

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#myCarousel" data-slide-to="1"></li>
                                    <li data-target="#myCarousel" data-slide-to="2"></li>
                                    <li data-target="#myCarousel" data-slide-to="3"></li>
                                    <li data-target="#myCarousel" data-slide-to="4"></li>
                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                    <div class="item active">
                                        <img src="resources/img/doctor1.jpg" alt="Doctor">
                                        <div class="carousel-caption">
                                            <h3>Doctor</h3>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <img src="resources/img/ctscan.jpg" alt="CT-Scan">
                                        <div class="carousel-caption">
                                            <h3>CT-Scan</h3>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <img src="resources/img/surgeryoperation.jpg" alt="Surgery Operation">
                                        <div class="carousel-caption">
                                            <h3>Surgery Operation</h3>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <img src="resources/img/pills.jpg" alt="Pills">
                                        <div class="carousel-caption">
                                            <h3>Pills</h3>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <img src="resources/img/medrecord.png" alt="Medical Record">
                                        <div class="carousel-caption">
                                            <h3>Medical Record</h3>
                                        </div>
                                    </div>
                                </div>

                                <!-- Left and right controls -->
                                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

    </body>

</html>
