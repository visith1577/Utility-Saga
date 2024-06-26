package com.backend;

import DAO.dao.ElectricityConnectionDao;
import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
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

@WebServlet("/user/electricity-connection")
public class ElectricityConnection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("Title");
        String initial = req.getParameter("Initial").trim();
        String name = req.getParameter("Name").trim();

        String curr_address = req.getParameter("Address").trim();
        String accNum = req.getParameter("account-num");

        String nic = req.getParameter("CusNIC").trim();
        String email = req.getParameter("Email").trim();
        String telNum = req.getParameter("Telnum").trim();

        String nearest_acc_num = req.getParameter("NearestAccNum");
        String connection = req.getParameter("connection-status");

        String required_type = req.getParameter("RequiredType");
        String new_con_address = req.getParameter("NewConnectionAddress");
        String region = req.getParameter("region").trim().toUpperCase();


        ConnectionModel eConnection = new ConnectionModel();

        String requester_name = title + " " + initial + " " + name;
        connection(curr_address, accNum, nic, email, telNum, connection, region, eConnection, requester_name);

        if (required_type != null && new_con_address != null) {
            eConnection.setConnectionType(required_type);
            eConnection.setNewAddress(new_con_address);
        } else {
            eConnection.setConnectionType(null);
            eConnection.setNewAddress(null);
        }

        eConnection.setNearestAccount(nearest_acc_num);


        ElectricityConnectionDao dao = new ElectricityConnectionDao();

        try {
            try {
                dao.saveConnection(eConnection);
                resp.sendRedirect(req.getHeader("referer"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }

    }

    static void connection(String curr_address, String accNum, String nic, String email, String telNum, String connection, String region, ConnectionModel eConnection, String requester_name) {
        eConnection.setRequesterName(requester_name);

        eConnection.setCurrentAddress(curr_address);
        eConnection.setNic(nic);
        eConnection.setEmail(email);
        eConnection.setMobile(telNum);
        eConnection.setRegion(region);
        eConnection.setAccountNumber(accNum);

        ConnectionModel.ConnectionRequirement requirement = ConnectionModel.ConnectionRequirement.valueOf(connection);
        eConnection.setConnectionRequirements(requirement);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        UserDetails user = new UserDetailsDao();
        try {
            List<String> account_elist = dao.getUserAccountsWithStatus(
                    (String) session.getAttribute("NIC"), "ELECTRICITY", "ACTIVE"
            );
            List<String> regions = dao.getAvailableRegions("ELECTRICITY");
            UserModel model = user.getUserFullNameByNic((String) session.getAttribute("NIC"));


            req.setAttribute("electricity_account_list", account_elist);
            req.setAttribute("regions", regions);
            req.setAttribute("fullName", model.getFullName());
            req.setAttribute("ADDRESS", model.getAddress());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/electricity/electricity_connection.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"error\": \"Failed to update user image\"}");
            req.setAttribute("errorMessage", "{\"error\": \"Failed to update user image\"}");
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}
