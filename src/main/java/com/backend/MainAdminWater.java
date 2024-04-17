package com.backend;

import DAO.dao.WaterAdminDAO;
import DAO.impl.WaterAdminImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricityAdminModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/main-admin/water-accounts")
public class MainAdminWater extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ElectricityAdminModel> waterRegionalAdmins;
        String id = req.getParameter("id");

        System.out.println("ID: " + id);

        WaterAdminImpl dao = new WaterAdminDAO();

        try {
            if (id == null || id.isEmpty()) {
                waterRegionalAdmins = dao.getWaterAdmins(ElectricityAdminModel.Role.REGIONAL);
            } else {
                waterRegionalAdmins = dao.getAdminsByNIC(id);
            }

            if (waterRegionalAdmins.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Water Regional Admins: " + waterRegionalAdmins);
                req.setAttribute("waterRegionalAdmins", waterRegionalAdmins);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/water/admin/AdminDashboard-water.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
