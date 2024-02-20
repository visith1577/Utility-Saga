package com.backend;


import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.EmailValidator;
import utils.SendEmail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/contact-us")
public class Contact extends HttpServlet {

    final String fromEmail = "";
    final String password = "";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String TelephoneNumber = req.getParameter("phone_number");
        String message = req.getParameter("message");

        String[] toEmail = {"example"};
        String[]  userEmail = {email};


        EmailValidator validator = EmailValidator.getInstance();

        if(!validator.isValid(email)){
            resp.sendRedirect("/public/HTML/pages/error.jsp?error=invalidEmail");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String subject  = "Complaint received from " + name + " on " + formattedDateTime;
        String body_admin = "";
        
        String body_client = "";

        try {
            extracted(toEmail, subject, body_admin);
            extracted( userEmail, subject, body_client);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/public/HTML/pages/error.jsp?error=EmailFailure");
        }
    }

    private void extracted(String[] toEmail, String subject, String body) throws MessagingException {
        SendEmail email = new SendEmail(
                this.fromEmail,
                this.password,
                toEmail,
                subject,
                body
        );

        email.setMailServerProperties();
        email.createEmailMessage();
        email.sendEmail();
    }
}
