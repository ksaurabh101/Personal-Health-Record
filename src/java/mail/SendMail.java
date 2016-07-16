/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    String d_email = "",
            d_password = "",
            d_host = "smtp.mail.yahoo.com",
            d_port = "465",
            m_subject = "",
            m_to = "",
            m_text = "";

    public SendMail(String email, String subject, String m1_text) {
        m_to = email;
        m_subject = subject;
        m_text = m1_text;
    }

    public void sendMail() {
        Properties props = new Properties();
        try {
//            props.put("http.proxySet", "true");
//
//            props.put("http.proxyHost", "192.168.1.107");
//
//            props.put("http.proxyPort", "3128");
//
//            props.put("http.proxyUser", "ipg_2011053");
//
//            props.put("http.proxyPassword", "");
//
//            props.put("https.proxySet", "true");
//
//            props.put("https.proxyHost", "192.168.1.107");
//
//            props.put("https.proxyPort", "3128");
//
//            props.put("https.proxyUser", "ipg_2011053");
//
//            props.put("https.proxyPassword", "");
//
//            props.setProperty("proxySet", "true");
//            props.setProperty("socksProxyHost", "192.168.1.107");
//            props.setProperty("socksProxyPort", "3128");
//            props.setProperty("socksProxyUser", "ipg_2011053");
//            props.setProperty("socksProxyPassword", "");

            props.put("mail.smtp.user", d_email);
            props.put("mail.smtp.host", d_host);
            props.put("mail.smtp.port", d_port);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.debug", "true");
            props.put("mail.smtp.socketFactory.port", d_port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
        } catch (Exception e) {
            System.out.println("Send Mail Error : " + e);
        }
        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new mail.SendMail.SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
//          session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(msg);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}
