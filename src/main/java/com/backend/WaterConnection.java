package com.backend;

import DAO.dao.WaterConnectionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ConnectionModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/water-connection")
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
        String region = req.getParameter("region");

        ConnectionModel wConnection = new ConnectionModel();

        String requester_name = String.join(title, " ",  initial,  " ",  name);
        wConnection.setRequester_name(requester_name);

        wConnection.setCurrent_address(curr_address);
        wConnection.setNic(nic);
        wConnection.setEmail(email);
        wConnection.setMobile(telNum);
        wConnection.setRegion(region);
        wConnection.setAccount_number(accNum);

        ConnectionModel.ConnectionRequirement requirement = ConnectionModel.ConnectionRequirement.valueOf(connection);
        wConnection.setConnection_requirements(requirement);

        wConnection.setNew_address(new_con_address);

        wConnection.setNearest_account(nearest_acc_num);

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
}