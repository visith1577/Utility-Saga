package com.backend;

import DAO.dao.ElectricityComplaintDao;
import DAO.dao.ElectricityConnectionDao;
import DAO.impl.Complaints;
import DAO.impl.Connection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ComplaintModel;
import model.ConnectionModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateRegionalConnectionStatus")
public class UpdateRegionalElecConnectionStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside servlet");
        String bNum = req.getParameter("companyId");
        String status = req.getParameter("status");
        System.out.println("CompanyId: "+ bNum);
        System.out.println("Status: " + status);

        JsonObject responseData = new JsonObject();

        try {

            System.out.println("bnum : " + bNum);

            Connection dao = new ElectricityConnectionDao();
            dao.updateApprovalStatus(bNum, status.toUpperCase());
            System.out.println(status);
            ConnectionModel model = dao.getApprovalStatus(bNum);

            Gson gson = new Gson();
            JsonElement statusElem = gson.toJsonTree(model.getAccountStatus());
            JsonElement id = gson.toJsonTree(bNum);
            responseData.add("status", statusElem);
            responseData.add("bNum", id);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(responseData.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update approval status");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
