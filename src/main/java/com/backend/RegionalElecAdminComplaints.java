package com.backend;

import DAO.dao.ElectricityComplaintDao;
import DAO.impl.Complaints;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ComplaintModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/electricity/regional-admin/complaints")
public class RegionalElecAdminComplaints extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ComplaintModel> electricityRegionalComplaints;
        String id = req.getParameter("id");

        System.out.println("ID: " + id);

        Complaints dao = new ElectricityComplaintDao();

        try {
            if (id == null || id.isEmpty()) {
                electricityRegionalComplaints = dao.getComplaints();
            } else {
                electricityRegionalComplaints = dao.getComplaintsByComplaintID(id);
            }

            if (electricityRegionalComplaints.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Regional Complaints: " + electricityRegionalComplaints);
                req.setAttribute("electricityRegionalComplaints", electricityRegionalComplaints);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/electricity/regionalAdmin/complaints-electricity.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
