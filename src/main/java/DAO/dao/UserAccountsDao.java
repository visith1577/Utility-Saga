package DAO.dao;

import DAO.impl.UserAccounts;
import model.UserModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserAccountsDao implements UserAccounts {

    @Override
    public List<String> getUserAccounts(String nic, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<String> account_list = new ArrayList<>();

        try {
            String tableName;
            switch (category.toUpperCase()) {
                case "WATER":
                    tableName = "wAccount_list";
                    break;
                case "ELECTRICITY":
                    tableName = "eAccount_list";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + category);
            }

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT account_number FROM" + tableName + " WHERE nic = ?"
            );

            statement.setString(1, nic);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    account_list.add(result.getString("account_number"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return account_list;
    }
}
