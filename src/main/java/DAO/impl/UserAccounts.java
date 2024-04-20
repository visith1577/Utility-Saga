package DAO.impl;

import model.UserAccountsModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserAccounts {
    List<UserAccountsModel> getUserAccounts(String nic, String category) throws SQLException;
    List<UserAccountsModel> getUserBills(String nic, String category, int limit, int offset) throws SQLException;
    List<String> getUserAccountsWithStatus(String nic, String category, String status) throws SQLException;
    UserAccountsModel getUserBillByAccount(String nic, String account, String category) throws SQLException;
    void updateAccountStatus(String nic, String account, String status, String table) throws SQLException;
    boolean checkAccountExists(String nic, String account, String category) throws SQLException;
    Map<String, String> getUserAccountsWithIotStatus(String nic, String category) throws SQLException;
}
