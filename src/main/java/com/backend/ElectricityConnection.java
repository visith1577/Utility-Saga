package com.backend;

import DAO.dao.ElectricityConnectionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ConnectionModel;

import java.io.IOException;
import java.sql.SQLException;

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
        String region = req.getParameter("region").trim();


        ConnectionModel eConnection = new ConnectionModel();

        String requester_name = title + " " + initial + " " + name;
        eConnection.setRequester_name(requester_name);

        eConnection.setCurrent_address(curr_address);
        eConnection.setNic(nic);
        eConnection.setEmail(email);
        eConnection.setMobile(telNum);
        eConnection.setRegion(region);
        eConnection.setAccount_number(accNum);

        ConnectionModel.ConnectionRequirement requirement = ConnectionModel.ConnectionRequirement.valueOf(connection);
        eConnection.setConnection_requirements(requirement);

        if (required_type != null && new_con_address != null) {
            eConnection.setConnection_type(required_type);
            eConnection.setNew_address(new_con_address);
        } else {
            eConnection.setConnection_type(null);
            eConnection.setNew_address(null);
        }

        eConnection.setNearest_account(nearest_acc_num);


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
}
