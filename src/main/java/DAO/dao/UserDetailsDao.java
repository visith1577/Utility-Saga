package DAO.dao;

import model.UserModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDetailsDao implements DAO.impl.UserDetails {

    @Override
    public void registerUser(UserModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (nic, uname, firstname, lastname, pwd, mobile, home, email, address, provider, region, services) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, user.getNic());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getMobile());
            statement.setString(7, user.getHome());
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getAddress());
            statement.setString(10, user.getProvider().name());
            statement.setString(11, user.getRegion());

            String serviceString = String.join(",", user.getServices());
            statement.setString(12, serviceString);

            statement.executeUpdate();

        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getPasswordByNic(String username) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT pwd FROM users WHERE nic = ?"
            );

            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    storedHash = result.getString("pwd");
                } else {
                    storedHash = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return storedHash;
    }

    @Override
    public void getUserDetailsByNic(String nic) throws SQLException {
        
    }
}
