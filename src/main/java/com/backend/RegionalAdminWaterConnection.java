package com.backend;

import DAO.dao.ElectricityConnectionDao;
import DAO.dao.WaterConnectionDao;
import DAO.impl.Connection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ConnectionModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/water/regional-admin/connections")
public class RegionalAdminWaterConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ConnectionModel> waterConnectionRequests;
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        System.out.println("ID: " + id);

        Connection dao = new WaterConnectionDao();

        try {
            if (id == null || id.isEmpty()) {
                waterConnectionRequests = dao.getConnectionRegionalAdmin(session.getAttribute("REGION").toString());
            } else {
                waterConnectionRequests = dao.getConnectionRegionalAdminByNIC(session.getAttribute("REGION").toString(),id);
            }

            if (waterConnectionRequests.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Water Connection Requests: " + waterConnectionRequests);
                req.setAttribute("waterConnectionRequests", waterConnectionRequests);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/water/regionalAdmin/newconnection.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
