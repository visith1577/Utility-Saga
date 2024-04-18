package com.backend;

import java.io.*;
import java.sql.SQLException;

import DAO.dao.WaterAdminDAO;
import DAO.impl.WaterAdminImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ElectricityAdminModel;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/water/regional-admin/pwd-reset")
public class PasswordResetWaterRegional extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String oldPwd = req.getParameter("password");
        String newPwd = req.getParameter("re-new-password");
        String region = (String) session.getAttribute("REGION");
        System.out.println("NewPwd: "+newPwd);
        System.out.println("REGION: "+region);

        try {
            ElectricityAdminModel model = new ElectricityAdminModel();
            WaterAdminImpl userDao = new WaterAdminDAO();
            String pwdStored = userDao.getPasswordByNic(region);
            System.out.println("getPasswordByNic called ");
            model.setRegion(region);

            if (pwdStored != null) {
                if(BCrypt.checkpw(oldPwd, pwdStored)){
                    System.out.println("===================Password verified--------------------------------");
                    String bcryptHashedPwd = BCrypt.hashpw(newPwd, BCrypt.gensalt());
                    model.setPassword(bcryptHashedPwd);

                    userDao.updatePassword(model);
                    System.out.println("updatePassword called ");

                    resp.sendRedirect(req.getContextPath() + "/public/HTML/login/waterLogin.jsp");
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
