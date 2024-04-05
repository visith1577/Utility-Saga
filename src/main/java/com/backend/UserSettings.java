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
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/public/HTML/user/user-settings")
public class UserSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        DAO.impl.UserDetails user = new UserDetailsDao();
        try {
            List<String> account_wlist = dao.getUserAccounts(
                    (String) session.getAttribute("NIC"), "WATER"
            );
            List<String> account_elist = dao.getUserAccounts(
                    (String) session.getAttribute("NIC"), "ELECTRICITY"
            );

            UserModel details = user.getUserDetailsByNic( (String) session.getAttribute("NIC"));

            req.setAttribute("electricity_account_list", account_elist);
            req.setAttribute("water_account_list", account_wlist);
            req.setAttribute("firstname", details.getFirstName());
            req.setAttribute("lastname", details.getLastName());
            req.setAttribute("home", details.getHome());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/setting_profile.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
