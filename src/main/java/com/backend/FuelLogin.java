package com.backend;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/fuel-login")
public class FuelLogin extends HttpServlet{
    private static final long serialVersionUID = 6L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserLogin.loginDriver(req, resp, "userDashboard.jsp", "userLogin.jsp");
    }
}
