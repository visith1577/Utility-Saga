package com.backend;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/store-login")
public class StoreLogin extends HttpServlet{
    private static final long serialVersionUID = 2L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserLogin.loginDriver(req, resp, "SolarPowerCompanyDashboard.jsp", "solarLogin.jsp");
    }
}
