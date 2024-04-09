package DAO.impl;

import model.UserAccountsModel;

import java.sql.SQLException;
import java.util.List;

public interface UserAccounts {
    List<String> getUserAccounts(String nic, String category) throws SQLException;
    List<UserAccountsModel> getUserBills(String nic, String category) throws SQLException;
    UserAccountsModel getUserBillByAccount(String nic, String account, String category) throws SQLException;
}
