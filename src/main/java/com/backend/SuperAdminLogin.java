package com.backend;

import java.io.*;
import java.sql.*;

import DAO.dao.ElectricityRegionalAdminDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UserRAdmin;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/salogin")
public class SuperAdminLogin extends HttpServlet{
    private static final long serialVersionUID = 3L;

    private static final int SESSION_TIMEOUT_IN_SECONDS = 3600;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loginDriver(req, resp, "Superadmin-electricity-water.jsp", "administratorLogin.jsp");
    }

    static void loginDriver(HttpServletRequest req, HttpServletResponse resp, String dash, String login) throws ServletException, IOException {
        String id = req.getParameter("Uname").trim();
        String pwd = req.getParameter("Pwd");
        HttpSession session = req.getSession();

        if (session != null){
            session.invalidate();
        }

        Cookie c = new Cookie("active_account" , id);
//        System.out.printf("nic: %s && pwd: %s", nic, pwd);


        try {
            req.removeAttribute("errorMessage");
            DAO.impl.UserRegional userDao = new ElectricityRegionalAdminDAO();
            String pwdStored = userDao.getPasswordSuperAdminById(id);
            if (pwdStored != null) {
//                    System.out.println("===================Password verified--------------------------------");
                    session = req.getSession(true);
                    session.setAttribute("isLoggedIn", true);
                    session.setAttribute("ID", id);
                    session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                    c.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                    resp.addCookie(c);
                    resp.sendRedirect(req.getContextPath() + "/public/HTML/superadmin/" + dash);//Change

            }else {
                req.setAttribute("ID", id);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("errorMessage", "ID not registered");
                RequestDispatcher dispatcher = req.getRequestDispatcher(
                        "/public/HTML/login/" +
                                login
                );
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
