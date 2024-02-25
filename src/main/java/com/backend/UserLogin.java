package com.backend;

import java.io.*;
import java.sql.*;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class UserLogin extends HttpServlet{
    private static final long serialVersionUID = 2L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loginDriver(req, resp, "userDashboard.jsp", "userLogin.jsp");
    }

    static void loginDriver(HttpServletRequest req, HttpServletResponse resp, String dash, String login) throws ServletException, IOException {
        String nic = req.getParameter("nic").trim();
        String pwd = req.getParameter("Pwd");
        HttpSession session = req.getSession();

        Cookie c = new Cookie("active_account" ,nic);

        try {
            UserDetails userDao = new UserDetailsDao();
            String pwdStored = userDao.getPasswordByNic(nic);
            if (pwdStored != null) {
                if(BCrypt.checkpw(pwd, pwdStored)){
//                    System.out.println("===================Password verified--------------------------------");
                    session.setAttribute("isLoggedIn", true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(
                            "/public/HTML/dashboard/" +
                                    dash
                    );
                    resp.addCookie(c);
                    dispatcher.forward(req, resp);
                }

            }else {
                session.setAttribute("isLoggedIn", false);
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
