package DAO.impl;

import java.sql.SQLException;

public interface UserDetails {
    void registerUser(model.UserDetails user) throws SQLException;
}
