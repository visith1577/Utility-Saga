package DAO.impl;

import java.sql.SQLException;
import java.util.List;

import model.ElectricityAdminModel;

public interface ElectricityAdminImpl {
        public int addElectricityAdmin(ElectricityAdminModel admin) throws Exception;

        public List<ElectricityAdminModel> getElectricityAdmins(ElectricityAdminModel.Role role) throws Exception;

    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;

    String getPasswordByNic(String nic) throws SQLException;

    void updatePassword(ElectricityAdminModel user) throws SQLException;
}
