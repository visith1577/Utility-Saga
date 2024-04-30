package com.backend;


import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.SendEmail;
import java.util.Random;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet({"/forget-password"})
public class ForgetPassword extends HttpServlet {

    private final Dotenv dotenv = Dotenv.load();
    private final String fromEmail = dotenv.get("SMTP_SENDER");



    Random random = new Random();
    int otp = random.nextInt(9000) + 1000;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ForegetPassword Called");
        String email = req.getParameter("email");
        System.out.println("Email: "+ email);

        HttpSession session = req.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String subject = "Confirmation OTP for Password Reset- " + formattedDateTime;
        String body = "Your OTP: \n"+ otp;


            SendEmail email_tmp = new SendEmail(fromEmail, new String[]{email}, subject, body);
            email_tmp.sendEmail("<h1>User Password Reset Confirmation Email- Utility Saga</h1>");
            System.out.println("Email send");
            resp.sendRedirect(req.getHeader("referer"));

    }
}
