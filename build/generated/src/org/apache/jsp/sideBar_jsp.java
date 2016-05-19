package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sideBar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
