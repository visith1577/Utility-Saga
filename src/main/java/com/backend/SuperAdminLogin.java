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

        loginDriver(req, resp, "/super-admin/main-electricity-accounts", "administratorLogin.jsp");
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
            UserRAdmin.Role role = userDao.getUserSuperAdminRoleById(id);
            System.out.println("PwdStored: "+pwdStored);
            System.out.println("role: "+role);
            System.out.println("id: "+id);
            System.out.println("pwd: "+pwd);
            System.out.println(pwdStored);
            if (pwdStored != null && pwdStored.equals(pwd)) {
//                    System.out.println("===================Password verified--------------------------------");
                    session = req.getSession(true);
                    session.setAttribute("isLoggedIn", true);
                    session.setAttribute("ROLE", role);
                    session.setAttribute("ID", id);
                    session.setAttribute("REGION", id); // region up
                    session.setAttribute("AREAS", id);  // list of areas
                    session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                    c.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                    resp.addCookie(c);
                    if (role == UserRAdmin.Role.SUPERADMIN) {
                        resp.sendRedirect(req.getContextPath()  + dash);//Change
                    }
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
