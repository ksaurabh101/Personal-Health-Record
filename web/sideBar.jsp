<%-- 
    Document   : sideBar
    Created on : Dec 29, 2015, 7:59:58 PM
    Author     : Saurabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <%
                    String patientLogin = (String) session.getAttribute("patient");
                    String userLogin = (String) session.getAttribute("user");
                    String adminLogin = (String) session.getAttribute("admin");
                    if (patientLogin != null && userLogin == null && adminLogin == null) {
                %>
                <li>
                    <a href="#" data-toggle="collapse" data-target="#patientLogin"><i class="fa fa-fw fa-user"></i> Patient Control<i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="patientLogin" class="collapse">
                        <li>
                            <a href="fileUpload.jsp"><i class="fa fa-fw fa-cloud-upload"></i> Upload PHR</a>
                        </li>
                        <li>
                            <a href="PatientViewPHR.jsp"><i class="fa fa-fw fa-eye"></i> View PHR</a>
                        </li>
                        <li>
                            <a href="PatientUpdatePHR.jsp"><i class="fa fa-fw fa-refresh"></i> Update PHR</a>
                        </li>
                        <li>
                            <a href="PatientRemovePHR.jsp"><i class="fa fa-fw fa-trash"></i> Remove PHR</a>
                        </li>
                        <li>
                            <a href="PatientViewRequest.jsp"><i class="fa fa-fw fa-list-alt"></i> View Request</a>
                        </li>
                        <li>
                            <a href="ViewMessage.jsp"><i class="fa fa-fw fa-envelope"></i> View Message</a>
                        </li>
                        <li>
                            <a href="SendMessage.jsp"><i class="fa fa-fw fa-mail-forward"></i> Send Message</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" data-toggle="collapse" data-target="#pALogin"><i class="fa fa-fw fa-user"></i>  Access Control<i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="pALogin" class="collapse">
                        <li>
                            <a href="PatientAppintment.jsp"><i class="fa fa-fw fa-calendar"></i> Appointment</a>
                        </li>
                        <li>
                            <a href="DoctorPrescription.jsp"><i class="fa fa-fw fa-plus"></i> Doctor Prescription</a>
                        </li>
                        <li>
                            <a href="SurgeryDetails.jsp"><i class="fa fa-fw fa-tint"></i> Surgery Details</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="DynamicPolicyView.jsp"><i class="fa fa-fw fa-edit"></i> Dynamic Policy Change</a>
                </li>
                <%
                    }
                    if (userLogin != null && patientLogin == null && adminLogin == null) {
                %>
                <li>
                    <a href="#" data-toggle="collapse" data-target="#userLogin"><i class="fa fa-fw fa-user"></i> User Control<i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="userLogin" class="collapse">
                        <li>
                            <a href="UserViewPHR.jsp"><i class="fa fa-fw fa-eye"></i> View PHR</a>
                        </li>
                        <li>
                            <a href="ViewMessage.jsp"><i class="fa fa-fw fa-envelope"></i> View Message</a>
                        </li>
                        <li>
                            <a href="SendMessage.jsp"><i class="fa fa-fw fa-mail-forward"></i> Send Message</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" data-toggle="collapse" data-target="#pULogin"><i class="fa fa-fw fa-user"></i>  Access Control<i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="pULogin" class="collapse">
                        <li>
                            <a href="DoctorAppointment.jsp"><i class="fa fa-fw fa-calendar"></i> Appointment</a>
                        </li>
                        <li>
                            <a href="PatientInformation.jsp"><i class="fa fa-fw fa-info-circle"></i> Patient Info</a>
                        </li>
                        <li>
                            <a href="SurgeryUpdate.jsp"><i class="fa fa-fw fa-tint"></i> Surgery</a>
                        </li>
                        <li>
                            <a href="PrescriptionUpdate.jsp"><i class="fa fa-fw fa-plus"></i> Doctor Prescription</a>
                        </li>
                        <li>
                            <a href="OperationReport.jsp"><i class="fa fa-fw fa-file-pdf-o"></i> Operation Report</a>
                        </li>
                    </ul>
                </li>
                <%}
                    if (userLogin == null && patientLogin == null && adminLogin == null) {
                %>
                <li>
                    <a href="patientLogin.jsp"><i class="fa fa-fw fa-user"></i> Patient Login</a>
                </li>
                <li>
                    <a href="userLogin.jsp"><i class="fa fa-fw fa-user"></i> User Login</a>
                </li>
                <li>
                    <a href="breakGlass.jsp"><i class="fa fa-fw fa-desktop"></i> Break Glass</a>
                </li>
                <%
                    }
                    if (patientLogin == null && userLogin == null && adminLogin != null) {
                %>
                <li>
                    <a href="#" data-toggle="collapse" data-target="#pULogin"><i class="fa fa-fw fa-user"></i>  Access Control<i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="pULogin" class="collapse">
                        <li>
                            <a href="AdminRegistration.jsp"><i class="fa fa-fw fa-user"></i> Admin Registration</a>
                        </li>
                        <li>
                            <a href="ViewPatient.jsp"><i class="fa fa-fw fa-users"></i> View Patient</a>
                        </li>
                        <li>
                            <a href="ViewUser.jsp"><i class="fa fa-fw fa-users"></i> View User</a>
                        </li>
                        <li>
                            <a href="AdminViewRequest.jsp"><i class="fa fa-fw fa-list-alt"></i> View Request</a>
                        </li>
                        <li>
                            <a href="RemovePatient.jsp"><i class="fa fa-fw fa-user"></i> Remove Patient</a>
                        </li>
                        <li>
                            <a href="RemoveUser.jsp"><i class="fa fa-fw fa-user"></i> Remove User</a>
                        </li>
                        <li>
                            <a href="ViewMessage.jsp"><i class="fa fa-fw fa-envelope"></i> View Message</a>
                        </li>
                        <li>
                            <a href="SendMessage.jsp"><i class="fa fa-fw fa-mail-forward"></i> Send Message</a>
                        </li>
                    </ul>
                </li>
                <%
                    }
                %>
                <li>
                    <a href="AboutUs.jsp"><i class="fa fa-fw fa-users"></i> About Us</a>
                </li>
                <li>
                    <a href="ContactUs.jsp"><i class="fa fa-fw fa-mail-forward"></i> Contact Us</a>
                </li>
            </ul>
        </div>
    </body>
</html>
