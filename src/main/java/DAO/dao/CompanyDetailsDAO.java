package DAO.dao;

import DAO.impl.CompanyDetails;
import model.CompanyModel;
import utils.DBConnectionManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CompanyDetailsDAO implements CompanyDetails {

    private DBConnectionManager dbConnectionManager;
    private static final String ADD_COMPANY = "INSERT INTO company (name, email, contact, address_id) VALUES (?, ?, ?, ?)";

    @Override
    public Integer addCompany(CompanyModel company) throws SQLException {
        Integer id = null;
        try {
            dbConnectionManager = DBConnectionManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(ADD_COMPANY, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getEmail());
            preparedStatement.setString(3, company.getContact());
            preparedStatement.setInt(4, company.getAddressId());
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
}
