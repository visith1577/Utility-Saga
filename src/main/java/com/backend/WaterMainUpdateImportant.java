package com.backend;

import DAO.dao.WaterAdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricityAdminModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/water/main-admin/update-regional-details")
public class WaterMainUpdateImportant extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request URL: " + req.getRequestURL());
        String region = req.getParameter("editRegion");
//        System.out.println("Empid:"+ empid);
        String contact = req.getParameter("editContact");
//        System.out.println("Empid:"+ uname);
        String email = req.getParameter("editEmail");
//        System.out.println("Empid:"+ firstname);

        ElectricityAdminModel admin = new ElectricityAdminModel();

        admin.setRegion(region);
        admin.setContactNumber(contact);
        admin.setEmail(email);



        WaterAdminDAO dao = new WaterAdminDAO();

        try {
            try {
                dao.updateImportantDetails(admin);
                System.out.println("updateadmin called");
                resp.sendRedirect(req.getHeader("referer"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }

    }
}