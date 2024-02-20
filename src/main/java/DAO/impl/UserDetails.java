package DAO.impl;

import model.UserModel;

import java.sql.SQLException;

public interface UserDetails {
    void registerUser(UserModel user) throws SQLException;
}
