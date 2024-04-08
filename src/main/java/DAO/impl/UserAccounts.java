package DAO.impl;

import java.sql.SQLException;
import java.util.List;

public interface UserAccounts {
    List<String> getUserAccounts(String nic, String category) throws SQLException;

    List<model.UserAccounts> getUserBills(String nic, String category) throws SQLException;
}
