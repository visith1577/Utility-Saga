package com.backend;

import java.io.*;
import java.sql.SQLException;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/user/pwd-reset")
public class PasswordReset extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String oldPwd = req.getParameter("password");
        String newPwd = req.getParameter("re-new-password");
        String nic = (String) session.getAttribute("NIC");

        try {
            UserModel model = new UserModel();
            UserDetails userDao = new UserDetailsDao();
            String pwdStored = userDao.getPasswordByNic(nic);
            model.setNic(nic);

            if (pwdStored != null) {
                if(BCrypt.checkpw(oldPwd, pwdStored)){
                    System.out.println("===================Password verified--------------------------------");
                    String bcryptHashedPwd = BCrypt.hashpw(newPwd, BCrypt.gensalt());
                    model.setPassword(bcryptHashedPwd);

                    userDao.updatePassword(model);

                    resp.sendRedirect(req.getContextPath() + "/public/HTML/login/userLogin.jsp");
                    session.invalidate();
                } else {
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    req.setAttribute("errorMessage", "Incorrect Password");
                    req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
                }
            } else {
                resp.sendRedirect(req.getHeader("referer"));
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to retrieve user details\"}");
            throw new RuntimeException(e);
        }
    }
}
