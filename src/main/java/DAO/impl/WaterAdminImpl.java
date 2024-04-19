package DAO.impl;


import java.sql.SQLException;
import java.util.List;
import model.ElectricityAdminModel;
public interface WaterAdminImpl {
    public int addWaterAdmin(ElectricityAdminModel admin) throws Exception;

    public List<ElectricityAdminModel> getWaterAdmins(ElectricityAdminModel.Role role) throws Exception;

    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;

    String getPasswordByNic(String nic) throws SQLException;

    void updatePassword(ElectricityAdminModel user) throws SQLException;

    public ElectricityAdminModel getUserDetailsByRegion(String region) throws SQLException;

    public int updateAdminDetails(ElectricityAdminModel admin) throws Exception;
}
