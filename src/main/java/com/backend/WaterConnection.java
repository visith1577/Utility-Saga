package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import DAO.dao.WaterConnectionDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ConnectionModel;
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user/water-connection")
public class WaterConnection extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("Title");
        String initial = req.getParameter("Initial");
        String name = req.getParameter("Name");

        String curr_address = req.getParameter("Address");
        String accNum = req.getParameter("account-num");

        String nic = req.getParameter("CusNIC");
        String email = req.getParameter("Email");
        String telNum = req.getParameter("Telnum");

        String nearest_acc_num = req.getParameter("NearestAccNum");
        String connection = req.getParameter("connection-status");

        String new_con_address = req.getParameter("NewConnectionAddress");
        String region = req.getParameter("region").trim().toUpperCase();

        ConnectionModel wConnection = new ConnectionModel();

        String requester_name = String.join(title, " ",  initial,  " ",  name);
        wConnection.setRequesterName(requester_name);

        wConnection.setCurrentAddress(curr_address);
        wConnection.setNic(nic);
        wConnection.setEmail(email);
        wConnection.setMobile(telNum);
        wConnection.setRegion(region);
        wConnection.setAccountNumber(accNum);

        ConnectionModel.ConnectionRequirement requirement = ConnectionModel.ConnectionRequirement.valueOf(connection);
        wConnection.setConnectionRequirements(requirement);

        wConnection.setNewAddress(new_con_address);

        wConnection.setNearestAccount(nearest_acc_num);

        WaterConnectionDao dao = new WaterConnectionDao();

        try {
            try {
                dao.saveConnection(wConnection);
                resp.sendRedirect(req.getHeader("referer"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        UserDetails user = new UserDetailsDao();
        try {
            List<String> account_wlist = dao.getUserAccountsWithStatus(
                    (String) session.getAttribute("NIC"), "WATER", "ACTIVE"
            );
            UserModel model = user.getUserFullNameByNic((String) session.getAttribute("NIC"));


            req.setAttribute("water_account_list", account_wlist);
            req.setAttribute("fullName", model.getFullName());
            req.setAttribute("ADDRESS", model.getAddress());


            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/water/water_connection.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
