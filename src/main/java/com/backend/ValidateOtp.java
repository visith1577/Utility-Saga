package com.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/validate-otp")
public class ValidateOtp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String storedEmail = (String) session.getAttribute("email");
        int storedOtp = (int) session.getAttribute("otp");

        int otp = Integer.parseInt(req.getParameter("otp"));

        resp.setContentType("text/plain");
        if (otp == storedOtp) {
            resp.getWriter().write("valid");
            System.out.println("vaild");
        } else {
            resp.getWriter().write("invalid");
            System.out.println("invalid");
        }
    }
}