package com.backend;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/electricity/regional-admin/user-accounts")
public class RegionalElecAdminUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> electricityRegionalUsers;
        String id = req.getParameter("id");

        System.out.println("ID: " + id);

        UserDetails dao = new UserDetailsDao();

        try {
            if (id == null || id.isEmpty()) {
                electricityRegionalUsers = dao.getUserDetailsRegionalAdmin();
            } else {
                electricityRegionalUsers = dao.getUserDetailsByNICRegionalAdmin(id);
            }

            if (electricityRegionalUsers.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Regional Users: " + electricityRegionalUsers);
                req.setAttribute("electricityRegionalUsers", electricityRegionalUsers);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/electricity/regionalAdmin/AdminDashboard-electricity.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
