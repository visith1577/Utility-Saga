package DAO.impl;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface WaterRegional {
    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;

    String getPasswordById(String region) throws SQLException;

    UserRAdmin.Role getUserRoleById(String region) throws SQLException;

    String getPasswordByNic(String nic) throws SQLException;

    void updatePassword(ElectricityAdminModel user) throws SQLException;
}
