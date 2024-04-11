package com.backend;

import DAO.dao.UserAccountsDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/user/water-dashboard")
public class WaterDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        try {
            List<String> account_wlist = dao.getUserAccounts(
                    (String) session.getAttribute("NIC"), "WATER"
            );

            req.setAttribute("water_account_list", account_wlist);


            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/water/userDashboardWater.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
