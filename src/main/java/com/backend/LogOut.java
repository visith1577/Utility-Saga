package com.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LogOut extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        HttpSession session = req.getSession(false);

        if (session != null) {
            // Invalidate the session (log out the user)
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/public/HTML/login/userSelector.jsp");
    }
}
