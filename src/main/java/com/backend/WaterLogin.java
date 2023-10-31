package com.backend;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/water-login")
public class WaterLogin extends HttpServlet{

    private static final long serialVersionUID = 4L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ElectricityLogin.loginDriverUtility(req, resp, "electricityDashboard.jsp", "electricityLogin.jsp");
    }
}
