package DAO.impl;

import model.ElectricityAdminModel;
import model.SolarCompanyModel;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface SolarCompanyImpl {
    void registerCompany(SolarCompanyModel company) throws SQLException;

    List<SolarCompanyModel> getRegisteredCompanies() throws Exception;

    void  updateApprovalStatus(String bNum, String status) throws Exception;

    List<SolarCompanyModel> getRegisteredCompaniesByNIC(String ownerNIC) throws Exception;

    SolarCompanyModel getApprovalStatus(String bnum) throws SQLException;
}
