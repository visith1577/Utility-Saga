package DAO.impl;

import model.UserAccountsModel;

import java.sql.SQLException;
import java.util.List;

public interface UserBillPaymentImpl {
    List<UserAccountsModel> getUserAccounts(String nic, String category) throws SQLException;

}
