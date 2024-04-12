package DAO.impl;

import java.sql.SQLException;

public interface Validations {
    boolean isUserNameExists(String username) throws SQLException;
    boolean isEmailExists(String email) throws SQLException;
    boolean isNICExists(String nic) throws SQLException;
}
