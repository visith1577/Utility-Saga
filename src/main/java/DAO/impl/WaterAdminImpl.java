package DAO.impl;


import java.sql.SQLException;
import java.util.List;
import model.ElectricityAdminModel;
public interface WaterAdminImpl {
    public int addWaterAdmin(ElectricityAdminModel admin) throws Exception;

    public List<ElectricityAdminModel> getWaterAdmins(ElectricityAdminModel.Role role) throws Exception;

    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;
}
