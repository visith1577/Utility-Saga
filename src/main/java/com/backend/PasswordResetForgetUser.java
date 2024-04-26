package com.backend;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.sql.SQLException;

@WebServlet("/user/reset-password")
public class PasswordResetForgetUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pwd = req.getParameter("newPassword");
        System.out.println("pwd: "+pwd);
        String email = (String) session.getAttribute("email");
        System.out.println("email: "+email);

        try {
            UserModel model = new UserModel();
            UserDetails userDao = new UserDetailsDao();
            model.setEmail(email);

            System.out.println("===================Password verified--------------------------------");
            String bcryptHashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            model.setPassword(bcryptHashedPwd);

            userDao.resetPassword(model);
            System.out.println("resetPasswordCalled successfully");

            resp.sendRedirect(req.getContextPath() + "/public/HTML/login/userLogin.jsp");
            session.invalidate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}