package control;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * author: LamHP
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
        int otpvalue = 0;
        HttpSession mySession = request.getSession();

        if (email != null && !email.isEmpty()) {
            // sending otp
            Random rand = new Random();
            otpvalue = rand.nextInt(1255650);

            String to = email;
            // Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("hoangplam03@gmail.com", "eqfa efbv venm tmxu"); // Use your app-specific password here
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("hoangplam03@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to, false));
                message.setSubject("OTP Verification");
                message.setText("Your OTP is: " + otpvalue);
                // send message
                Transport.send(message);
                System.out.println("Message sent successfully");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            request.setAttribute("message", "OTP is sent to your email id");

            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", email);
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("message", "Please enter a valid email address.");
            dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}
