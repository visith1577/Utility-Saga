package com.backend;

import DAO.dao.RegisterSolarDAO;
import DAO.impl.SolarCompanyImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SolarCompanyModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/super-admin/solar-accounts")
public class SuperAdminSolar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SolarCompanyModel> solarCompanies;
        String id = req.getParameter("id");

        System.out.println("ID: " + id);

        SolarCompanyImpl dao = new RegisterSolarDAO();

        try {
            if (id == null || id.isEmpty()) {
                solarCompanies = dao.getRegisteredCompanies();
            } else {
                solarCompanies = dao.getRegisteredCompaniesByNIC(id);
            }

            if (solarCompanies.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Solar Companies: " + solarCompanies);
                req.setAttribute("solarCompanies", solarCompanies);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/superadmin/Superadmin-solar.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
