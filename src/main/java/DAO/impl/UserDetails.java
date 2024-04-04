package DAO.impl;

import model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface UserDetails {
    void registerUser(UserModel user) throws SQLException;

    String getPasswordByNic(String nic) throws SQLException;

    String getUnameByNic(String nic) throws SQLException;

    UserModel getUserDetailsByNic(String nic) throws SQLException;
}
