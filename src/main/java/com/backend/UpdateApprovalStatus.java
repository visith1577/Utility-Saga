package com.backend;

import DAO.dao.RegisterSolarDAO;
import DAO.impl.SolarCompanyImpl;
import com.google.gson.Gson;
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
        String bNum = req.getParameter("bNum");
        String status = req.getParameter("status");

        try {
            SolarCompanyImpl dao = new RegisterSolarDAO();
            boolean success = dao.updateApprovalStatus(bNum, status);
//            System.out.println(status);
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(success);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonResponse);
            System.out.println(status);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update approval status");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
