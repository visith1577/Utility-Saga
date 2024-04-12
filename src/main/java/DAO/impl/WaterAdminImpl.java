package DAO.impl;


import java.util.List;
import model.ElectricityAdminModel;
public interface WaterAdminImpl {
    public int addWaterAdmin(ElectricityAdminModel admin) throws Exception;

    public List<ElectricityAdminModel> getWaterAdmins() throws Exception;
}
