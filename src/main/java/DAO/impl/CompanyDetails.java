package DAO.impl;

import model.CompanyModel;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDetails {
    Integer addCompany(CompanyModel company) throws SQLException;
    void updateCompany(CompanyModel company) throws SQLException;
    void deleteCompanyById(Integer id) throws SQLException;
    CompanyModel selectCompanyById(Integer id) throws SQLException;
    List<CompanyModel> selectAllCompanies() throws SQLException;
}
