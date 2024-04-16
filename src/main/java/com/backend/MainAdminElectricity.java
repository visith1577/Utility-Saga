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


@WebServlet("/main-admin/electricity-accounts")
public class MainAdminElectricity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ElectricityAdminModel> electricityRegionalAdmins;
        String id = req.getParameter("id");

        System.out.println("ID: " + id);

        ElectricityAdminImpl dao = new ElectricityAdminDAO();

        try {
            if (id == null || id.isEmpty()) {
                electricityRegionalAdmins = dao.getElectricityAdmins(ElectricityAdminModel.Role.REGIONAL);
            } else {
                electricityRegionalAdmins = dao.getAdminsByNIC(id);
            }

            if (electricityRegionalAdmins.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Regional Admins: " + electricityRegionalAdmins);
                req.setAttribute("electricityRegionalAdmins", electricityRegionalAdmins);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/electricity/admin/AdminDashboard-electricity.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
