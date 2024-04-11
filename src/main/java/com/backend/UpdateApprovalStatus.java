package com.backend;

import DAO.dao.RegisterSolarDAO;
import DAO.impl.SolarCompanyImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SolarCompanyModel;
import utils.Connectdb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateApprovalStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bNum= req.getParameter("bNum");
        String status= req.getParameter("status");

        try{
            Connection connection= Connectdb.getConnection();
            PreparedStatement stmt= connection.prepareStatement("UPDATE solar_company SET approval_status=? WHERE bnum=?");

            stmt.setString(1,status);
            stmt.setString(2,bNum);

            SolarCompanyImpl dao =  new RegisterSolarDAO();


            try{
                dao.updateApprovalStatus(bNum,status);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
