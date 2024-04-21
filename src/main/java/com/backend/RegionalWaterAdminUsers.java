package com.backend;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/water/regional-admin/user-accounts")
public class RegionalWaterAdminUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> waterRegionalUsers;
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        System.out.println("ID: " + id);

        UserDetails dao = new UserDetailsDao();

        try {
            if (id == null || id.isEmpty()) {
                waterRegionalUsers = dao.getWaterDetailsRegionalAdmin(session.getAttribute("REGION").toString());
            } else {
                waterRegionalUsers = dao.getWaterDetailsByNICRegionalAdmin(session.getAttribute("REGION").toString(), id);
            }

            if (waterRegionalUsers.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Water Regional Users: " + waterRegionalUsers);
                req.setAttribute("waterRegionalUsers", waterRegionalUsers);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/water/regionalAdmin/AdminDashboard-water.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
