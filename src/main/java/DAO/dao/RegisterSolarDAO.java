package DAO.dao;

import model.ElectricityAdminModel;
import model.SolarCompanyModel;
import DAO.impl.SolarCompanyImpl;

import utils.Connectdb;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class RegisterSolarDAO implements SolarCompanyImpl{
    @Override
    public void registerCompany(SolarCompanyModel company) throws SQLException {
        Connection connection = Connectdb.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                 "INSERT INTO solar_company (cname, bnum, ownernic, uname, pwd, mobile, companytelnum, email, address, district, remarks) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
            );
            stmt.setString(1, company.getCompanyName());
            stmt.setString(2, company.getBNum());
            stmt.setString(3, company.getOwnerNIC());
            stmt.setString(4,company.getUsername());
            stmt.setString(5, company.getPwd());
            stmt.setString(6, company.getMobile());
            stmt.setString(7, company.getLandNo());
            stmt.setString(8,company.getEmail());
            stmt.setString(9,company.getAddress());
            stmt.setString(10, company.getDistrict());
            stmt.setString(11, company.getRemarks());

            stmt.executeUpdate();
        }
        finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public List<SolarCompanyModel> getRegisteredCompanies() throws Exception {
        List<SolarCompanyModel> companies=  new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String sql= "SELECT * FROM solar_company";
        PreparedStatement stmt=  connection.prepareStatement(sql);

        ResultSet rs= stmt.executeQuery();

        while(rs.next()){
            SolarCompanyModel company = new SolarCompanyModel();
            company.setCompanyName(rs.getString(2));
            company.setBNum(rs.getString(3));
            company.setOwnerNIC(rs.getString(4));
            company.setUsername(rs.getString(5));
            company.setPwd(rs.getString(6));
            company.setMobile(rs.getString(7));
            company.setLandNo(rs.getString(8));
            company.setEmail(rs.getString(9));
            company.setAddress(rs.getString(10));
            company.setDistrict(rs.getString(11));
            company.setRemarks(rs.getString(12));
            company.setApprovalStatus(SolarCompanyModel.ApprovalStatus.valueOf(rs.getString(13).toUpperCase()));

            companies.add(company);
        }


        rs.close();
        stmt.close();
        connection.close();
        return companies;
    }

    @Override
    public boolean updateApprovalStatus(String bnum, String status) throws SQLException {
        boolean rowsUpdated = false;

        try (Connection connection = Connectdb.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE solar_company SET approval_status = ? WHERE bnum = ?")) {

            stmt.setString(1, status);
            stmt.setString(2, bnum);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            rowsUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to update approval status: " + e.getMessage());
        }

        return rowsUpdated;
    }

}
