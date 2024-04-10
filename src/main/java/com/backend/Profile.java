package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import com.google.gson.*;
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
        int pageNumber = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;

        int limit = 5;
        int offset = (pageNumber - 1) * limit;

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        try {
            if (offset < 5) {
                if (session.getAttribute("water") != null) {
                    List<UserAccountsModel> account_wlist = dao.getUserBills(
                            (String) session.getAttribute("NIC"), "WATER", limit, offset
                    );
                    req.setAttribute("water_account_list", account_wlist);
                }

                if (session.getAttribute("electricity") != null) {
                    List<UserAccountsModel> account_elist = dao.getUserBills(
                            (String) session.getAttribute("NIC"), "ELECTRICITY", limit, offset
                    );
                    req.setAttribute("electricity_account_list", account_elist);
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/user.jsp");
                dispatcher.forward(req, resp);
            } else {

                JsonObject responseData = new JsonObject();

                if (session.getAttribute("water") != null) {
                    List<UserAccountsModel> account_wlist = dao.getUserBills(
                            (String) session.getAttribute("NIC"), "WATER", limit, offset
                    );
                    Gson gson = new Gson();
                    JsonElement jsonData = gson.toJsonTree(account_wlist);
                    responseData.add("water_accounts", jsonData);
                }

                if (session.getAttribute("electricity") != null) {
                    List<UserAccountsModel> account_elist = dao.getUserBills(
                            (String) session.getAttribute("NIC"), "ELECTRICITY", limit, offset
                    );
                    Gson gson = new Gson();
                    JsonElement jsonData = gson.toJsonTree(account_elist);
                    responseData.add("electricity_accounts", jsonData);
                }

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write(responseData.toString());
            }

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to retrieve user details\"}");
            throw new RuntimeException(e);
        }
    }
}
