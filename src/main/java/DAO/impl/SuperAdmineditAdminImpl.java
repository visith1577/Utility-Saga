package DAO.impl;

import model.SuperAdmineditAdmin;

import java.sql.SQLException;

public interface SuperAdmineditAdminImpl {
    void createElecAdmin(SuperAdmineditAdmin admin) throws SQLException;
    void deleteElecAdmin(String elecId) throws SQLException;
    void updateElecAdmin(SuperAdmineditAdmin admin) throws SQLException;

    void createWaterAdmin(SuperAdmineditAdmin admin) throws SQLException;
    void deleteWaterAdmin(String waterId) throws SQLException;
    void updateWaterAdmin(SuperAdmineditAdmin admin) throws SQLException;

    void createSolarComp(SuperAdmineditAdmin admin) throws SQLException;
    void deleteSolarComp(String solarId) throws SQLException;
    void updateSolarComp(SuperAdmineditAdmin admin) throws SQLException;
}
