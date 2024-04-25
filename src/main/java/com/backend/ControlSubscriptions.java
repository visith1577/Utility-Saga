package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import DAO.impl.UserAccounts;
import DAO.impl.UserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ControlSubscriptions", value = {"/user/control-subscriptions/water", "/user/control-subscriptions/electricity"})
public class ControlSubscriptions extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String nic = session.getAttribute("NIC").toString();
        String action = req.getParameter("action");
        String uri = req.getRequestURI();
        String[] uriParts = uri.split("/");
        String subscriptionType = uriParts[uriParts.length -1];

        UserDetails user = new UserDetailsDao();
        UserAccounts accounts = new UserAccountsDao();

        try {
            if (action.equals("subscribe")) {

                if (subscriptionType.equals("water")
                                && accounts.checkAccountExists(nic, null,"water")){
                    user.updateServices(nic, List.of("water", "electricity"));
                    session.setAttribute("water", "water");
                } else if (subscriptionType.equals("electricity")
                        && accounts.checkAccountExists(nic, null,"electricity")){
                    user.updateServices(nic, List.of("water", "electricity"));
                    session.setAttribute("electricity", "electricity");
                }

            } else if (action.equals("unsubscribe")) {

                if (subscriptionType.equals("water")) {
                    user.updateServices(nic, List.of("electricity"));
                    session.removeAttribute("water");
                } else if (subscriptionType.equals("electricity")) {
                    user.updateServices(nic, List.of("water"));
                    session.removeAttribute("electricity");
                }
            }

            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
