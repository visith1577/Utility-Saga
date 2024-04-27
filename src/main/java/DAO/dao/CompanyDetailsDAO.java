package DAO.dao;

import DAO.impl.CompanyDetails;
import model.CompanyModel;
import model.ItemModel;
import utils.DBConnectionManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CompanyDetailsDAO implements CompanyDetails {

    private static final String SELECT_COMPANY_BY_USERNAME = "SELECT * FROM solar_company WHERE uname=?";
    private static final String ADD_COMPANY = "INSERT INTO solar_company (name, email, contact, address_id ,user_name, password ) VALUES (?, ?, ?, ? , ?, ?)";
    private DBConnectionManager dbConnectionManager;

    public CompanyDetailsDAO() {
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
    public Integer addCompany(CompanyModel company) throws SQLException {
        Integer id = null;
        try (PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(ADD_COMPANY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getEmail());
            preparedStatement.setString(3, company.getContact());
            preparedStatement.setInt(4, company.getAddressId());
            preparedStatement.setString(5, company.getUserName());
            preparedStatement.setString(6, company.getPassword());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        }
        return id;
    }

    @Override
    public void updateCompany(CompanyModel company) throws SQLException {

    }

    @Override
    public void deleteCompanyById(Integer id) throws SQLException {

    }

    @Override
    public CompanyModel selectCompanyById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<CompanyModel> selectAllCompanies() throws SQLException {
        return List.of();
    }


    @Override
    public CompanyModel selectCompanyByUserName(String userName) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        CompanyModel companyModel = null;
        try {
            preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(SELECT_COMPANY_BY_USERNAME);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                companyModel = new CompanyModel();
                companyModel.setId(rs.getInt("id"));
                companyModel.setName(rs.getString("name"));
                companyModel.setEmail(rs.getString("email"));
                companyModel.setContact(rs.getString("contact"));
                companyModel.setAddressId(rs.getInt("address_id"));
                companyModel.setUserName(rs.getString("user_name"));
                companyModel.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyModel;
    }
}
