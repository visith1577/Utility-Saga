package com.backend;

import DAO.dao.ElectricityAdminDAO;
import DAO.impl.ElectricityAdminImpl;
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


@WebServlet("/electricity/main-admin/region")
public class ElectricityRegion extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("region").toUpperCase();
        ElectricityAdminModel admin = new ElectricityAdminModel();
        admin.setRegion(region);
        ElectricityAdminDAO dao = new ElectricityAdminDAO();

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

        ElectricityAdminImpl dao = new ElectricityAdminDAO();

        try {
            List<String> electricityRegion = dao.getRegions().stream()
                    .distinct()
                    .collect(Collectors.toList());

            if (electricityRegion.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Region " + electricityRegion);
                req.setAttribute("electricityRegion", electricityRegion);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/electricity/admin/electricity-regions.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
