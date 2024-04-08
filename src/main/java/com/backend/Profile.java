package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccountsModel;
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/user/user-profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        try {
            if (session.getAttribute("water") != null) {
                List<UserAccountsModel> account_wlist = dao.getUserBills(
                        (String) session.getAttribute("NIC"), "WATER"
                );
                req.setAttribute("water_account_list", account_wlist);
            }

            if (session.getAttribute("electricity") != null) {
                List<UserAccountsModel> account_elist = dao.getUserBills(
                        (String) session.getAttribute("NIC"), "ELECTRICITY"
                );
                req.setAttribute("electricity_account_list", account_elist);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/user.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
