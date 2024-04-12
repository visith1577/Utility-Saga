package com.backend;


import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.SendEmail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet({"/user/water-contact-us", "/user/electricity-contact-us"})
public class Contact extends HttpServlet {

    private final Dotenv dotenv = Dotenv.load();
    private final String fromEmail = dotenv.get("SMTP_SENDER");
    private final String toEmailWater = dotenv.get("SMTP_WATER_RECV");
    private final String toEmailElectricity = dotenv.get("SMTP_ELECTRIC_RECV");


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("Sender-name");
        String email = req.getParameter("Sender-email");
        String TelephoneNumber = req.getParameter("Sender-telephone");
        String message = req.getParameter("Sender-message");


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String subject = "Contact Us - " + formattedDateTime;
        String body = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Telephone Number: " + TelephoneNumber + "\n" +
                "Message: " + message;

        if (req.getRequestURI().equals("/user/water-contact-us")) {
            SendEmail email_tmp = new SendEmail(fromEmail, new String[]{toEmailWater}, subject, body);
            email_tmp.sendEmail("<h1>Water Contact Us form Utility Saga</h1>");
            resp.sendRedirect(req.getHeader("referer"));
        } else if (req.getRequestURI().equals("/user/electricity-contact-us")) {
            SendEmail email_tmp = new SendEmail(fromEmail, new String[]{toEmailElectricity}, subject, body);
            email_tmp.sendEmail("<h1>Electricity Contact Us form Utility Saga</h1>");
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}
