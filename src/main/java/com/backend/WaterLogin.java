package com.backend;

import java.io.*;
import java.sql.*;

import DAO.dao.ElectricityAdminDAO;
import DAO.dao.ElectricityRegionalAdminDAO;
import DAO.dao.WaterAdminDAO;
import DAO.dao.WaterRegionalAdminDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ElectricityAdminModel;
import model.UserRAdmin;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/water-login")
public class WaterLogin extends HttpServlet{
    private static final long serialVersionUID = 4L;

    private static final int SESSION_TIMEOUT_IN_SECONDS = 3600;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loginDriver(req, resp, "AdminDashboard-water.jsp", "waterLogin.jsp");
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
            DAO.impl.WaterRegional userDao = new WaterRegionalAdminDAO();
            DAO.impl.WaterAdminImpl admindao = new WaterAdminDAO();
            String pwdStored = userDao.getPasswordById(id);
            UserRAdmin.Role role = userDao.getUserRoleById(id);
            ElectricityAdminModel model = admindao.getUserDetailsByRegion(id);
            if (pwdStored != null) {
                if(BCrypt.checkpw(pwd, pwdStored)){
//                    System.out.println("===================Password verified--------------------------------");
                    session = req.getSession(true);
                    session.setAttribute("isLoggedIn", true);
                    session.setAttribute("ROLE", role.toString());
                    session.setAttribute("ID", id);
                    session.setAttribute("REGION", id); // region up
                    session.setAttribute("AREAS", id);  // list of areas
                    session.setAttribute("USERNAME", model.getUsername());
                    session.setAttribute("EMPID", model.getEmpId());
                    session.setAttribute("FNAME", model.getFirstname());
                    session.setAttribute("LNAME", model.getLastname());
                    session.setAttribute("MOBILE", model.getMobile());
                    session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                    c.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                    resp.addCookie(c);
                    if (role == UserRAdmin.Role.MAIN) {
                        resp.sendRedirect(req.getContextPath() + "/main-admin/water-accounts");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/public/HTML/water/regionalAdmin/" + dash);
                    }

                } else {
                    req.setAttribute("ID", id);
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    req.setAttribute("errorMessage", "Incorrect Password");
                    RequestDispatcher dispatcher = req.getRequestDispatcher(
                            "/public/HTML/login/" +
                                    login
                    );
                    dispatcher.forward(req, resp);
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
