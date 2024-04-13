package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.impl.UserAccounts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AccountActivity", value = {"/user/account-activity/electricity", "/user/account-activity/water"})
public class AccountActivity extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String activity = req.getParameter("action");
        String account = req.getParameter("accountNumber");

        HttpSession session = req.getSession();

        UserAccounts dao = new UserAccountsDao();

        try {
            // get final word the request uri ends with
            String uri = req.getRequestURI();
            String[] uriParts = uri.split("/");
            String finalWord = uriParts[uriParts.length - 1];


            dao.updateAccountStatus((String) session.getAttribute("NIC"), account, activity, finalWord);
            resp.sendRedirect(req.getHeader("referer"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
