package com.backend;

import DAO.dao.WaterComplaintsDao;
import DAO.impl.Complaints;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ComplaintModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/water/regional-admin/complaints")
public class RegionalWaterAdminComplaints extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ComplaintModel> waterRegionalComplaints;
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        System.out.println("ID: " + id);

        Complaints dao = new WaterComplaintsDao();

        try {
            if (id == null || id.isEmpty()) {
                waterRegionalComplaints = dao.getComplaints(session.getAttribute("REGION").toString());
            } else {
                waterRegionalComplaints = dao.getComplaintsByComplaintID(session.getAttribute("REGION").toString(),id);
            }

            if (waterRegionalComplaints.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Regional Complaints: " + waterRegionalComplaints);
                req.setAttribute("waterRegionalComplaints", waterRegionalComplaints);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/water/regionalAdmin/complaints-electricity.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
