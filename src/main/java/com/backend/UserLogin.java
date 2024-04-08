package com.backend;

import java.io.*;
import java.sql.*;
import java.util.Set;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class UserLogin extends HttpServlet{
    private static final long serialVersionUID = 2L;
    private static final int SESSION_TIMEOUT_IN_SECONDS = 3600;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loginDriver(req, resp, "user.jsp", "userLogin.jsp");
    }

    static void loginDriver(HttpServletRequest req, HttpServletResponse resp, String dash, String login) throws ServletException, IOException {
        String nic = req.getParameter("nic").trim();
        String pwd = req.getParameter("Pwd");
        HttpSession session = req.getSession();

        if (session != null){
            session.invalidate();
        }

        Cookie c = new Cookie("active_account" ,nic);
//        System.out.printf("nic: %s && pwd: %s", nic, pwd);


        try {
            req.removeAttribute("errorMessage");
            UserDetails userDao = new UserDetailsDao();
            String pwdStored = userDao.getPasswordByNic(nic);
            String uname = userDao.getUnameByNic(nic);
            UserModel details = userDao.getUserDetailsByNic(nic);
            if (pwdStored != null) {
                if(BCrypt.checkpw(pwd, pwdStored)){
//                    System.out.println("===================Password verified--------------------------------");
                    session = req.getSession(true);
                    session.setAttribute("isLoggedIn", true);
                    session.setAttribute("UNAME", uname);
                    session.setAttribute("NIC", nic);
                    session.setAttribute("TELEPHONE", details.getMobile());
                    session.setAttribute("EMAIL", details.getEmail());
                    setServiceCookie(nic, userDao, session);
                    session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                    c.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                    resp.addCookie(c);
                    resp.sendRedirect(req.getContextPath() + "/public/HTML/user/" + dash);
                } else {
                    req.setAttribute("NIC", nic);
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    req.setAttribute("errorMessage", "Incorrect Password");
                    RequestDispatcher dispatcher = req.getRequestDispatcher(
                        "/public/HTML/login/" +
                                login
                    );
                    dispatcher.forward(req, resp);
                }
            }else {
                req.setAttribute("NIC", nic);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("errorMessage", "NIC not registered");
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

    static void setServiceCookie(String nic, UserDetails dao, HttpSession session) throws SQLException {
        UserModel currentUser = dao.getUserDetailsByNic(nic);

        Set<String> services = currentUser.getServices();
        for (Object item: services) {
            session.setAttribute(item.toString(), item.toString());
            System.out.printf("%s in ::::", item);
        }
    }
}
