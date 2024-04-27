package DAO.dao;

import model.CompanyModel;
import model.SolarCompanyModel;
import DAO.impl.SolarCompanyImpl;

import utils.Connectdb;
import utils.DBConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class RegisterSolarDAO implements SolarCompanyImpl {

    private DBConnectionManager dbConnectionManager;

    private static final String SELECT_COMPANY_BY_USERNAME = "SELECT * FROM solar_company WHERE email = ?";

    public RegisterSolarDAO(){
        try {
            dbConnectionManager = DBConnectionManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException("IOException : ", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException : ", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException : ", e);
        }
    }

    @Override
    public void registerCompany(SolarCompanyModel company) throws SQLException {
        Connection connection = Connectdb.getConnection();
        System.out.println("connected");
        try {
            System.out.println("Preparing statement");
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO solar_company (cname, bnum, ownernic, uname, pwd, mobile, companytelnum, email, address, district, remarks) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
            );
            stmt.setString(1, company.getCompanyName());
            stmt.setString(2, company.getBNum());
            stmt.setString(3, company.getOwnerNIC());
            stmt.setString(4, company.getUsername());
            stmt.setString(5, company.getPwd());
            stmt.setString(6, company.getMobile());
            stmt.setString(7, company.getLandNo());
            stmt.setString(8, company.getEmail());
            stmt.setString(9, company.getAddress());
            stmt.setString(10, company.getDistrict());
            stmt.setString(11, company.getRemarks());

            stmt.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public List<SolarCompanyModel> getRegisteredCompanies() throws Exception {
        List<SolarCompanyModel> companies = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String sql = "SELECT * FROM solar_company";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
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
    public void updateApprovalStatus(String bnum, String status) throws SQLException {
        Connection connection = Connectdb.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement("UPDATE solar_company SET approval_status = ? WHERE bnum = ?")) {

            stmt.setString(1, status);
            stmt.setString(2, bnum);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update approval status: " + e.getMessage());
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public SolarCompanyModel getApprovalStatus(String bnum) throws SQLException {
        Connection connection = Connectdb.getConnection();
        SolarCompanyModel model = new SolarCompanyModel();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT approval_status FROM solar_company WHERE bnum = ?");
            stmt.setString(1, bnum);

            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    SolarCompanyModel.ApprovalStatus status = SolarCompanyModel.ApprovalStatus.valueOf(result.getString("approval_status"));
                    model.setApprovalStatus(status);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Failed to update approval status: " + e.getMessage());
        } finally {
            Connectdb.closeConnection(connection);
        }
        return model;
    }

    @Override
    public SolarCompanyModel getRegisteredCompanyByUserName(String userName) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        SolarCompanyModel company = null;
        try {
            preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(SELECT_COMPANY_BY_USERNAME);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                company = new SolarCompanyModel();
                company.setId(rs.getInt("id"));
                company.setCompanyName(rs.getString("cname"));
                company.setBNum(rs.getString("bnum"));
                company.setOwnerNIC(rs.getString("ownernic"));
                company.setUsername(rs.getString("uname"));
                company.setPwd(rs.getString("pwd"));
                company.setMobile(rs.getString("mobile"));
                company.setLandNo(rs.getString("companytelnum"));
                company.setEmail(rs.getString("email"));
                company.setAddress(rs.getString("address"));
                company.setDistrict(rs.getString("district"));
                company.setRemarks(rs.getString("remarks"));
                company.setApprovalStatus(SolarCompanyModel.ApprovalStatus.valueOf(rs.getString("approval_status").toUpperCase()));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return company;
    }

    @Override
    public List<SolarCompanyModel> getRegisteredCompaniesByNIC(String ownerNIC) throws Exception {
        List<SolarCompanyModel> companies = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String sql = "SELECT * FROM solar_company WHERE ownernic = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, ownerNIC);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
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

}
