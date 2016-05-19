<%-- 
    Document   : topMenuItem
    Created on : Dec 29, 2015, 8:01:57 PM
    Author     : Saurabh
--%>

<%@page import="Config.Config"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp">Patient Health Record</a>
        </div>
        <%
            String patient = (String) session.getAttribute("patient");
            String user = (String) session.getAttribute("user");
            String admin = (String) session.getAttribute("admin");
            String loginName = (String) session.getAttribute("loginName");
            if ((patient != null || user != null || admin != null) && loginName != null) {
                try {
                    Connection conn;
                    Statement stm;
                    ResultSet rss;
                    conn = Config.getcon();
                    stm = conn.createStatement();
                    if (patient != null) {
                        String query = "select * from message where receiverID='" + patient + "' and status=0 order by date desc";
                        rss = stm.executeQuery(query);
                    } else if (user != null) {
                        String query = "select * from message where receiverID='" + user + "' and status=0 order by date desc";
                        rss = stm.executeQuery(query);
                    } else {
                        String query = "select * from message where receiverID='" + admin + "' and status=0 order by date desc";
                        rss = stm.executeQuery(query);
                    }
                    rss.last();
                    int count = rss.getRow();
                    if (patient != null) {
                        String query = "select * from message where receiverID='" + patient + "' and status=0 order by date desc";
                        rss = stm.executeQuery(query);
                    } else if (user != null) {
                        String query = "select * from message where receiverID='" + user + "' and status=0 order by date desc";
                        rss = stm.executeQuery(query);
                    } else {
                        String query = "select * from message where receiverID='" + admin + "' and status=0 order by date desc";
                        rss = stm.executeQuery(query);
                    }
        %>            
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i><sup class="text-danger"><b> <%=count%></b></sup> <b class="caret"></b></a>
                <ul class="dropdown-menu message-dropdown">
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                <div class="media-body">
                                    <div>
                                        <%
                                            if (rss.next()) {
                                                do {
                                        %> 
                                        <h5 class="media-heading">
                                            <strong>
                                                <%=rss.getString("senderName")%>
                                            </strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> <%=rss.getString("date")%></p>
                                        <p><%=rss.getString("message")%></p>
                                        <%
                                            } while (rss.next());
                                        } else {
                                        %>
                                        <strong class="text-danger bg-danger">
                                            There is No messages
                                        </strong>
                                    </div>
                                    <%
                                            }
                                        } catch (Exception e) {
                                            session.setAttribute("msg", "Error..Try Again");
                                            //response.sendRedirect("loginError.jsp");
                                        }
                                    %>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-footer">
                        <a href="ViewMessage.jsp">Read All New Messages</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                    <%= loginName%>
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="profile.jsp"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <!--
                    <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    -->
                    <li class="divider"></li>
                    <li>
                        <a href="logout.jsp"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
        <%
        } else {
        %>
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                    Sign-Up
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="patientRegistration.jsp"><i class="fa fa-fw fa-user"></i> Patient SignUp</a>
                    </li>
                    <li>
                        <a href="userRegistration.jsp"><i class="fa fa-fw fa-user"></i> User Sign-Up</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-sign-in"></i>
                    Login
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="patientLogin.jsp"><i class="fa fa-fw fa-user"></i> Patient Login</a>
                    </li>
                    <li>
                        <a href="userLogin.jsp"><i class="fa fa-fw fa-user"></i> User Login</a>
                    </li>
                    <li>
                        <a href="AdminLogin.jsp"><i class="fa fa-fw fa-user"></i> Admin Login</a>
                    </li>
                </ul>
            </li>
        </ul>
        <%
            }
        %>
    </body>
</html>
