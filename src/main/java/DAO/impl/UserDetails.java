package DAO.impl;

import model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface UserDetails {
    void registerUser(UserModel user) throws SQLException;

    String getPasswordByNic(String nic) throws SQLException;

    String getUnameByNic(String nic) throws SQLException;

    UserModel getUserDetailsByNic(String nic) throws SQLException;

    UserModel getUserFullNameByNic(String nic) throws SQLException;

    void updateUserInfo(UserModel user) throws SQLException;

    void insertImage(UserModel user) throws SQLException;

    void updateImage(UserModel user) throws SQLException;

    void updatePassword(UserModel user) throws SQLException;

    String getImageByNic(String nic) throws SQLException;

    void updateServices(String nic, List<String> services) throws SQLException;

     List<UserModel> getUserDetailsRegionalAdmin(String region) throws SQLException;

    List<UserModel> getUserDetailsByNICRegionalAdmin(String region,String nic) throws SQLException;

    List<UserModel> getWaterDetailsRegionalAdmin(String id) throws SQLException;

    List<UserModel> getWaterDetailsByNICRegionalAdmin(String id, String searchValue) throws SQLException;

    void updateAccountStatus(String accountNumber, String newStatus) throws SQLException;

    void updateWaterAccountStatus(String accountNumber, String newStatus) throws SQLException;

    Integer getTotalElectricityAccountsRegion(String region) throws SQLException;

    Integer getTotalElectricityUsersRegion(String region) throws SQLException;

    Integer getNewElectricityConnections(String region) throws SQLException;

    Integer getNewElectricityComplaints(String region) throws SQLException;

    Integer getTotalWaterAccountsRegion(String region) throws SQLException;

    Integer getTotalWaterUsersRegion(String region) throws SQLException;

    Integer getNewWaterConnections(String region) throws SQLException;

    Integer getNewWaterComplaints(String region) throws SQLException;
}
