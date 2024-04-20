package com.backend;

import DAO.dao.RegisterSolarDAO;
import DAO.impl.SolarCompanyImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SolarCompanyModel;
import utils.Connectdb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/UpdateApprovalStatus")
public class UpdateApprovalStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bNum = req.getParameter("companyId");
        String status = req.getParameter("status");

        JsonObject responseData = new JsonObject();

        try {

            System.out.println("bnum : " + bNum);

            SolarCompanyImpl dao = new RegisterSolarDAO();
            dao.updateApprovalStatus(bNum, status.toUpperCase());
//            System.out.println(status);
            SolarCompanyModel model = dao.getApprovalStatus(bNum);

            Gson gson = new Gson();
            JsonElement statusElem = gson.toJsonTree(model.getApprovalStatus());
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
