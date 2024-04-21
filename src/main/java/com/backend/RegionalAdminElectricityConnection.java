package com.backend;

import DAO.dao.ElectricityConnectionDao;
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


@WebServlet("/electricity/regional-admin/connections")
public class RegionalAdminElectricityConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ConnectionModel> electricityConnectionRequests;
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        System.out.println("ID: " + id);

        Connection dao = new ElectricityConnectionDao();

        try {
            if (id == null || id.isEmpty()) {
                electricityConnectionRequests = dao.getConnectionRegionalAdmin(session.getAttribute("REGION").toString());
            } else {
                electricityConnectionRequests = dao.getConnectionRegionalAdminByNIC(session.getAttribute("REGION").toString(),id);
            }

            if (electricityConnectionRequests.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Connection Requests: " + electricityConnectionRequests);
                req.setAttribute("electricityConnectionRequests", electricityConnectionRequests);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/electricity/regionalAdmin/newconnection.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
