package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fileUpload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/topMenuItem.jsp");
    _jspx_dependants.add("/sideBar.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <title>Personal Health Record</title>\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/sb-admin.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/plugins/morris.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <script src=\"js/jquery.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("            <nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
      out.write("                <!-- Top Menu Items -->\n");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"navbar-header\">\n");
      out.write("            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-ex1-collapse\">\n");
      out.write("                <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("            </button>\n");
      out.write("            <a class=\"navbar-brand\" href=\"home.jsp\">Patient Health Record</a>\n");
      out.write("        </div>\n");
      out.write("        ");

            String patient = (String) session.getAttribute("patient");
            String user = (String) session.getAttribute("user");
            String loginName = (String) session.getAttribute("loginName");
            if ((patient != null || user != null) && loginName != null) {
        
      out.write("            \n");
      out.write("        <ul class=\"nav navbar-right top-nav\">\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-envelope\"></i> <b class=\"caret\"></b></a>\n");
      out.write("                <ul class=\"dropdown-menu message-dropdown\">\n");
      out.write("                    <li class=\"message-preview\">\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <div class=\"media\">\n");
      out.write("                                <span class=\"pull-left\">\n");
      out.write("                                    <img class=\"media-object\" src=\"http://placehold.it/50x50\" alt=\"\">\n");
      out.write("                                </span>\n");
      out.write("                                <div class=\"media-body\">\n");
      out.write("                                    <h5 class=\"media-heading\">\n");
      out.write("                                        <strong>\n");
      out.write("                                            Unknown\n");
      out.write("                                        </strong>\n");
      out.write("                                    </h5>\n");
      out.write("                                    <p class=\"small text-muted\"><i class=\"fa fa-clock-o\"></i> Yesterday at 4:32 PM</p>\n");
      out.write("                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"message-preview\">\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <div class=\"media\">\n");
      out.write("                                <span class=\"pull-left\">\n");
      out.write("                                    <img class=\"media-object\" src=\"http://placehold.it/50x50\" alt=\"\">\n");
      out.write("                                </span>\n");
      out.write("                                <div class=\"media-body\">\n");
      out.write("                                    <h5 class=\"media-heading\"><strong>John Smith</strong>\n");
      out.write("                                    </h5>\n");
      out.write("                                    <p class=\"small text-muted\"><i class=\"fa fa-clock-o\"></i> Yesterday at 4:32 PM</p>\n");
      out.write("                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"message-preview\">\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <div class=\"media\">\n");
      out.write("                                <span class=\"pull-left\">\n");
      out.write("                                    <img class=\"media-object\" src=\"http://placehold.it/50x50\" alt=\"\">\n");
      out.write("                                </span>\n");
      out.write("                                <div class=\"media-body\">\n");
      out.write("                                    <h5 class=\"media-heading\">\n");
      out.write("                                        <strong>\n");
      out.write("                                            Unknown\n");
      out.write("                                        </strong>\n");
      out.write("                                    </h5>\n");
      out.write("                                    <p class=\"small text-muted\"><i class=\"fa fa-clock-o\"></i> Yesterday at 4:32 PM</p>\n");
      out.write("                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"message-footer\">\n");
      out.write("                        <a href=\"#\">Read All New Messages</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-bell\"></i> <b class=\"caret\"></b></a>\n");
      out.write("                <ul class=\"dropdown-menu alert-dropdown\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Alert Name <span class=\"label label-default\">Alert Badge</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Alert Name <span class=\"label label-primary\">Alert Badge</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Alert Name <span class=\"label label-success\">Alert Badge</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Alert Name <span class=\"label label-info\">Alert Badge</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Alert Name <span class=\"label label-warning\">Alert Badge</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Alert Name <span class=\"label label-danger\">Alert Badge</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"divider\"></li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">View All</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-user\"></i>\n");
      out.write("                    ");
      out.print( loginName);
      out.write("\n");
      out.write("                    <b class=\"caret\"></b></a>\n");
      out.write("                <ul class=\"dropdown-menu\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\"><i class=\"fa fa-fw fa-user\"></i> Profile</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\"><i class=\"fa fa-fw fa-envelope\"></i> Inbox</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\"><i class=\"fa fa-fw fa-gear\"></i> Settings</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"divider\"></li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"logout.jsp\"><i class=\"fa fa-fw fa-power-off\"></i> Log Out</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("        ");

        } else {
        
      out.write("\n");
      out.write("        <ul class=\"nav navbar-right top-nav\">\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-user\"></i>\n");
      out.write("                    Sign-Up\n");
      out.write("                    <b class=\"caret\"></b></a>\n");
      out.write("                <ul class=\"dropdown-menu\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"patientRegistration.jsp\"><i class=\"fa fa-fw fa-user\"></i> Patient SignUp</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"userRegistration.jsp\"><i class=\"fa fa-fw fa-user\"></i> User Sign-Up</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-sign-in\"></i>\n");
      out.write("                    Login\n");
      out.write("                    <b class=\"caret\"></b></a>\n");
      out.write("                <ul class=\"dropdown-menu\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"patientLogin.jsp\"><i class=\"fa fa-fw fa-user\"></i> Patient Login</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"userLogin.jsp\"><i class=\"fa fa-fw fa-user\"></i> User Login</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->\n");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"collapse navbar-collapse navbar-ex1-collapse\">\n");
      out.write("            <ul class=\"nav navbar-nav side-nav\">\n");
      out.write("                ");

                    String patientLogin = (String) session.getAttribute("patient");
                    String userLogin = (String) session.getAttribute("user");
                    if (patientLogin != null) {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\" data-toggle=\"collapse\" data-target=\"#patientLogin\"><i class=\"fa fa-fw fa-user\"></i> Patient Login <i class=\"fa fa-fw fa-caret-down\"></i></a>\n");
      out.write("                    <ul id=\"patientLogin\" class=\"collapse\">\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"fileUpload.jsp\"><i class=\"fa fa-fw fa-cloud-upload\"></i> Upload PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-eye\"></i> View PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-refresh\"></i> Update PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-trash\"></i> Remove PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                ");

                } else {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"patientLogin.jsp\"><i class=\"fa fa-fw fa-user\"></i> Patient Login</a>\n");
      out.write("                </li>\n");
      out.write("                ");

                    }
                    if (userLogin != null) {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\" data-toggle=\"collapse\" data-target=\"#userLogin\"><i class=\"fa fa-fw fa-users\"></i> User Login <i class=\"fa fa-fw fa-caret-down\"></i></a>\n");
      out.write("                    <ul id=\"userLogin\" class=\"collapse\">\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-cloud-upload\"></i> Upload PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-eye\"></i> View PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-refresh\"></i> Update PHR</a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                ");
} else {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"userLogin.jsp\"><i class=\"fa fa-fw fa-users\"></i> User Login</a>\n");
      out.write("                </li>\n");
      out.write("                ");

                    }
                    if (patientLogin != null) {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\" data-toggle=\"collapse\" data-target=\"#pALogin\"><i class=\"fa fa-fw fa-user\"></i>  Access Control<i class=\"fa fa-fw fa-caret-down\"></i></a>\n");
      out.write("                    <ul id=\"pALogin\" class=\"collapse\">\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-calendar\"></i> Appointment</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-plus\"></i> Doctor Prescription</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-tint\"></i> Surgery Details</a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                ");

                } else if (userLogin != null) {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\" data-toggle=\"collapse\" data-target=\"#pULogin\"><i class=\"fa fa-fw fa-user\"></i>  Access Control<i class=\"fa fa-fw fa-caret-down\"></i></a>\n");
      out.write("                    <ul id=\"pULogin\" class=\"collapse\">\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-calendar\"></i> Appointment</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-info-circle\"></i> Patient Info</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-tint\"></i> Surgery</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-fw fa-file-pdf-o\"></i> Operation Report</a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                ");
} else {
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"loginError.jsp\"><i class=\"fa fa-fw fa-ambulance\"></i> Access Control</a>\n");
      out.write("                </li>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"loginError.jsp\"><i class=\"fa fa-fw fa-edit\"></i> Dynamic Policy Change</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"loginError.jsp\"><i class=\"fa fa-fw fa-desktop\"></i> Break Glass</a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <!-- /.navbar-collapse -->\n");
      out.write("            </nav>\n");
      out.write("\n");
      out.write("            <div id=\"page-wrapper\">\n");
      out.write("\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <form role=\"form\" class=\"form-horizontal\">\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <!-- Page Heading -->\n");
      out.write("                            <h1 class=\"page-header\">\n");
      out.write("                                File Upload\n");
      out.write("                            </h1>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label col-sm-2\" for=\"file\">File : </label>\n");
      out.write("                                <div class=\"col-sm-6\">\n");
      out.write("                                    <input type=\"file\" id=\"file\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label col-sm-2\" for=\"key\">Key : </label>\n");
      out.write("                                <div class=\"col-sm-6\">\n");
      out.write("                                    <input type=\"text\" class=\"form-control\" id=\"key\" placeholder=\"Enter Key\">\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label col-sm-2\" for=\"permission\">Permission : </label>\n");
      out.write("                                <div class=\"col-sm-6\">\n");
      out.write("                                    <div class=\"checkbox checkbox-inline\">\n");
      out.write("                                        <label>\n");
      out.write("                                            <input type=\"checkbox\" value=\"readOnly\">Read Only\n");
      out.write("                                        </label>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"checkbox checkbox-inline\">\n");
      out.write("                                        <label>\n");
      out.write("                                            <input type=\"checkbox\" value=\"Read&WriteBoth\">Read & Write Both\n");
      out.write("                                        </label>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <!-- Page Heading -->\n");
      out.write("                            <h1 class=\"page-header\">\n");
      out.write("                                Attribute Selection\n");
      out.write("                            </h1>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <div class=\"row\">\n");
      out.write("                                    <div class=\"col-lg-1\"></div>\n");
      out.write("                                    <div class=\"col-lg-2\">\n");
      out.write("                                        <div class=\"panel panel-default\">\n");
      out.write("                                            <div class=\"panel-heading\"><h4>Profession</h4></div>\n");
      out.write("                                            <ul class=\"list-group\">\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Doctor\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Staff\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Physicians\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Insurance\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                            </ul>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-lg-1\"></div>\n");
      out.write("                                    <div class=\"col-lg-4\">\n");
      out.write("                                        <div class=\"panel panel-default\">\n");
      out.write("                                            <div class=\"panel-heading\"><h4>Speciality</h4></div>\n");
      out.write("                                            <ul class=\"list-group\">\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Dentist\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Gynecologist/obstetrician\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">General Physician\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Dermatologist/Cosmetologist\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Ear-nose-throat (ent) Specialist\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Homeopath\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Ayurveda\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Insurance\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                            </ul>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-lg-1\"></div>\n");
      out.write("                                    <div class=\"col-lg-2\">\n");
      out.write("                                        <div class=\"panel panel-default\">\n");
      out.write("                                            <div class=\"panel-heading\"><h4>Organization</h4></div>\n");
      out.write("                                            <ul class=\"list-group\">\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Hospital A\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Hospital B\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Government\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                                <li class=\"list-group-item\"><div class=\"checkbox\">\n");
      out.write("                                                        <label>\n");
      out.write("                                                            <input type=\"checkbox\" value=\"\">Emergency\n");
      out.write("                                                        </label>\n");
      out.write("                                                    </div></li>\n");
      out.write("                                            </ul>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <!-- /.row -->\n");
      out.write("                                <div class=\"row\">\n");
      out.write("                                    <div class=\"col-lg-12\" align=\"center\">\n");
      out.write("                                        <input type=\"reset\" id=\"resetButton\" value=\"Reset\" class=\"btn btn-default\"/>\n");
      out.write("                                        <input type=\"submit\" id=\"submit\" value=\"Submit\" class=\"btn btn-default\"/>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                            <!-- /.container-fluid -->\n");
      out.write("                        </div>\n");
      out.write("                        <!-- /#page-wrapper -->\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <!-- /#wrapper -->\n");
      out.write("\n");
      out.write("                </body>\n");
      out.write("\n");
      out.write("                </html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
