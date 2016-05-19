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
                                Profile
                            </h1>
                            <%                                Connection con;
                                Statement st;
                                ResultSet rs;
                                try {
                                    con = Config.getcon();
                                    st = con.createStatement();
                                    if (patient != null && user == null && admin == null) {
                                        String query = "select * from patient where patientID='" + patient + "'";
                                        rs = st.executeQuery(query);
                                        if (rs.next()) {
                            %>
                            <div class="col-lg-6">
                                <table class="table table-striped">
                                    <tbody>
                                        <tr class="">
                                            <th class="">Patient ID</th>
                                            <td class=""><%=rs.getString("patientID")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Patient Name</th>
                                            <td class=""><%=rs.getString("name")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Date Of Birth</th>
                                            <td class=""><%=rs.getString("dob")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Gender</th>
                                            <td class=""><%=rs.getString("gender")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Marital Status</th>
                                            <td class=""><%=rs.getString("mStatus")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Phone Number</th>
                                            <td class=""><%=rs.getString("pNumber")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Mobile Number</th>
                                            <td class=""><%=rs.getString("mNumber")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Email</th>
                                            <td class=""><%=rs.getString("email")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Address</th>
                                            <td class=""><%=rs.getString("address")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Disease</th>
                                            <td class=""><%=rs.getString("disease")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Joining Date</th>
                                            <td class=""><%=rs.getString("date")%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                    }
                                }
                                if (patient == null && user != null && admin == null) {
                                    String query = "select * from user where userID='" + user + "'";
                                    rs = st.executeQuery(query);
                                    if (rs.next()) {
                            %>
                            <div class="col-lg-6">
                                <table class="table table-striped">
                                    <tbody>
                                        <tr class="">
                                            <th class="">User ID</th>
                                            <td class=""><%=rs.getString("userID")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">User Name</th>
                                            <td class=""><%=rs.getString("name")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Date Of Birth</th>
                                            <td class=""><%=rs.getString("dob")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Gender</th>
                                            <td class=""><%=rs.getString("gender")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Marital Status</th>
                                            <td class=""><%=rs.getString("mStatus")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Phone Number</th>
                                            <td class=""><%=rs.getString("pNumber")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Email</th>
                                            <td class=""><%=rs.getString("email")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Address</th>
                                            <td class=""><%=rs.getString("address")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Joining Date</th>
                                            <td class=""><%=rs.getString("date")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Profession</th>
                                            <td class=""><%=rs.getString("profession")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Speciality</th>
                                            <td class=""><%=rs.getString("Speciality")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Organization</th>
                                            <td class=""><%=rs.getString("organization")%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                    }
                                }
                                if (patient == null && user == null && admin != null) {
                                    String query = "select * from admin where adminID='" + admin + "'";
                                    rs = st.executeQuery(query);
                                    if (rs.next()) {
                            %>
                            <div class="col-lg-6">
                                <table class="table table-striped">
                                    <tbody>
                                        <tr class="">
                                            <th class="">Admin ID</th>
                                            <td class=""><%=rs.getString("adminID")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Admin Name</th>
                                            <td class=""><%=rs.getString("name")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Date Of Birth</th>
                                            <td class=""><%=rs.getString("dob")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Gender</th>
                                            <td class=""><%=rs.getString("gender")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Marital Status</th>
                                            <td class=""><%=rs.getString("mStatus")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Phone Number</th>
                                            <td class=""><%=rs.getString("pNumber")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Email</th>
                                            <td class=""><%=rs.getString("email")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Address</th>
                                            <td class=""><%=rs.getString("address")%></td>
                                        </tr>
                                        <tr class="">
                                            <th class="">Joining Date</th>
                                            <td class=""><%=rs.getString("date")%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                        }
                                    }
                                } catch (Exception e) {
                                    session.setAttribute("msg", "Error : " + e);
                                    response.sendRedirect("loginError.jsp");
                                }
                            %>
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
