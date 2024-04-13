package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.impl.UserAccounts;
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


@WebServlet("/user/electricity-dashboard")
public class ElectricityDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        UserAccounts dao = new UserAccountsDao();
        try {
            List<String> account_elist = dao.getUserAccountsWithStatus(
                    (String) session.getAttribute("NIC"), "ELECTRICITY", "ACTIVE"
            );

            req.setAttribute("electricity_account_list", account_elist);


            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/electricity/userDashboardElectricity.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
