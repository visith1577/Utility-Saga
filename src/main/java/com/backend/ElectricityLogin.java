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

@WebServlet("/elogin")
public class ElectricityLogin extends HttpServlet{
    private static final long serialVersionUID = 3L;

    private static final int SESSION_TIMEOUT_IN_SECONDS = 3600;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loginDriver(req, resp, "AdminDashboard-electricity.jsp");
    }

    static void loginDriver(HttpServletRequest req, HttpServletResponse resp, String dash) throws ServletException, IOException {
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
            String pwdStored = userDao.getPasswordById(id);
            UserRAdmin.Role role = userDao.getUserRoleById(id);
            if (pwdStored != null) {
                if(BCrypt.checkpw(pwd, pwdStored)){
//                    System.out.println("===================Password verified--------------------------------");
                    session = req.getSession(true);
                    session.setAttribute("isLoggedIn", true);
                    session.setAttribute("ROLE", role.toString());
                    session.setAttribute("ID", id);
                    session.setAttribute("REGION", id); // region up
                    session.setAttribute("AREAS", id);  // list of areas
                    session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                    c.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                    resp.addCookie(c);
                    if (role == UserRAdmin.Role.MAIN) {
                        resp.sendRedirect(req.getContextPath() + "/main-admin/electricity-accounts");//Change
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/electricity/regional-admin/user-accounts");
                    }

                } else {
                    req.setAttribute("ID", id);
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    req.setAttribute("errorMessage", "Incorrect Password");
                    RequestDispatcher dispatcher = req.getRequestDispatcher(
                            "/public/HTML/login/" +
                                    "electricityLogin.jsp"
                    );
                    dispatcher.forward(req, resp);
                }
            }else {
                req.setAttribute("ID", id);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("errorMessage", "ID not registered");
                RequestDispatcher dispatcher = req.getRequestDispatcher(
                        "/public/HTML/login/" +
                                "electricityLogin.jsp"
                );
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
