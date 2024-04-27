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
import java.util.stream.Collectors;


@WebServlet("/super-admin/wregion")
public class WaterRegion extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("region").toUpperCase();
        ElectricityAdminModel admin = new ElectricityAdminModel();
        admin.setRegion(region);
        WaterAdminDAO dao = new WaterAdminDAO();

        try {
            dao.addRegion(admin);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        System.out.println("ID: " + id);

        WaterAdminImpl dao = new WaterAdminDAO();

        try {
            List<String> waterRegion = dao.getRegions().stream()
                    .distinct()
                    .collect(Collectors.toList());

            if (waterRegion.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Water Region " + waterRegion);
                req.setAttribute("waterRegion", waterRegion);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/superadmin/water-regions.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
