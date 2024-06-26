package DAO.impl;

import java.sql.SQLException;
import java.util.List;

import model.ElectricityAdminModel;

public interface ElectricityAdminImpl {
        public int addElectricityAdmin(ElectricityAdminModel admin) throws Exception;

    int addRegion(ElectricityAdminModel admin) throws Exception;

    int updateAdminDetails(ElectricityAdminModel admin) throws Exception;

    public List<ElectricityAdminModel> getElectricityAdmins(ElectricityAdminModel.Role role) throws Exception;

    int resetPassword(ElectricityAdminModel admin) throws SQLException;

    List<String> getRegions() throws Exception;

    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;

    String getPasswordByNic(String nic) throws SQLException;

    void updatePassword(ElectricityAdminModel user) throws SQLException;

    public ElectricityAdminModel getUserDetailsByRegion(String region) throws SQLException;

    int updateImportantDetails(ElectricityAdminModel admin) throws Exception;

    void updateAccountStatus(String region, String newStatus) throws SQLException;

    String getStatusByRegion(String region) throws SQLException;
}
