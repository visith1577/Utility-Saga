package DAO.impl;

import model.ElectricityAdminModel;
import model.SolarCompanyModel;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface SolarCompanyImpl {
    void registerCompany(SolarCompanyModel company) throws SQLException;

    public List<SolarCompanyModel> getRegisteredCompanies() throws Exception;
}
