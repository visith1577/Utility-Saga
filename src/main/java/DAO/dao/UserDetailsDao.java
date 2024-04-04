package DAO.dao;

import model.UserModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


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
    public String getPasswordByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT pwd FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);
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
    public String getUnameByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String uname;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT uname FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    uname = result.getString("uname");
                } else {
                    uname = nic;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return uname;
    }

    @Override
    public UserModel getUserDetailsByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserModel user = new UserModel();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT mobile, email, address, region, provider, services FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    user.setMobile(result.getString("mobile"));
                    user.setEmail(result.getString("email"));
                    user.setAddress(result.getString("address"));
                    user.setRegion(result.getString("region"));
                    UserModel.ProviderInfo providerInfo = UserModel.ProviderInfo.valueOf(result.getString("provider"));
                    user.setProvider(providerInfo);
                    user.setServices(new HashSet<>(Collections.singletonList(result.getString("services"))));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return user;
    }
}
