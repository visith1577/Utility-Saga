package com.backend;

import DAO.dao.ElectricityAdminDAO;
import DAO.impl.ElectricityAdminImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ElectricityAdminModel;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.sql.SQLException;

@WebServlet("/electricity/reset-password")
public class PasswordResetForgetElectricity extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pwd = req.getParameter("newPassword");
        System.out.println("pwd: "+pwd);
        String email = (String) session.getAttribute("email");
        System.out.println("email: "+email);

        try {
            ElectricityAdminModel model = new ElectricityAdminModel();
            ElectricityAdminImpl dao = new ElectricityAdminDAO();
            model.setEmail(email);

            System.out.println("===================Password verified--------------------------------");
            String bcryptHashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            model.setPassword(bcryptHashedPwd);

            dao.resetPassword(model);
            System.out.println("resetPasswordCalled successfully");

            resp.sendRedirect(req.getContextPath() + "/public/HTML/login/electricityLogin.jsp");
            session.invalidate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}