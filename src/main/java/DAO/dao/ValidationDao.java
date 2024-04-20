package DAO.dao;

import DAO.impl.Validations;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationDao implements Validations {
    @Override
    public boolean isUserNameExists(String username) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE uname = ?");
            stmt.setString(1, username);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isEmailExists(String email) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
            stmt.setString(1, email);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isNICExists(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE nic = ?");
            stmt.setString(1, nic);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }
}
