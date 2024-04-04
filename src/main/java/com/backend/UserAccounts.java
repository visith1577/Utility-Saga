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

@WebServlet("/user-accounts")
public class UserAccounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        try {
            List<String> account_wlist = dao.getUserAccounts(
                    (String) session.getAttribute("NIC"), "WATER"
            );
            List<String> account_elist = dao.getUserAccounts(
                    (String) session.getAttribute("NIC"), "ELECTRICITY"
            );

            req.setAttribute("electricity_account_list", account_elist);
            req.setAttribute("water_account_list", account_wlist);

            String path = req.getRequestURI();

            if(path.equals("/public/HTML/user/settings_profile.jsp")) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/setting_profile.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
