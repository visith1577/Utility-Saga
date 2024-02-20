package DAO.dao;

import model.UserModel;
import utils.Connectdb;

import java.sql.SQLException;

public class UserDetailsDao implements DAO.impl.UserDetails {
    private Connectdb connection;

    @Override
    public void registerUser(UserModel user) throws SQLException {

    }
}
