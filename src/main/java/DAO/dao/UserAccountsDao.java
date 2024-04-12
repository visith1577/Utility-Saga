package DAO.dao;

import DAO.impl.UserAccounts;
import model.UserAccountsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    "SELECT account_number FROM " + tableName + " WHERE nic = ?"
            );

            statement.setString(1, nic);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()){
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

    @Override
    public List<UserAccountsModel> getUserBills(String nic, String category, int limit, int offset) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<UserAccountsModel> account_list = new ArrayList<>();

        try {
            String tableName;
            String primaryName;
            switch (category.toUpperCase()) {
                case "WATER":
                    tableName = "wAccount_list";
                    primaryName = "water_bill";
                    break;
                case "ELECTRICITY":
                    tableName = "eAccount_list";
                    primaryName = "electricity_bill";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + category);
            }

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT " + primaryName + ".*" + " FROM " + tableName + " JOIN " + primaryName +
                            " ON " + tableName + ".account_number = " +  primaryName + ".account_number" +
                            " WHERE " + tableName + ".nic = ?" +
                            "LIMIT ? OFFSET ?"
            );

            statement.setString(1, nic);
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()){
                    UserAccountsModel model = new UserAccountsModel();

                    model.setAccount_number(result.getString("account_number"));
                    model.setAmount(result.getString("amount"));
                    model.setBilled_date(result.getString("billedDate"));
                    model.setDueDate(result.getString("dueDate"));
                    UserAccountsModel.Status status = UserAccountsModel.Status.valueOf(
                            result.getString("status")
                    );
                    if (category.equals("WATER")) {
                        model.setType(UserAccountsModel.Acc.WATER);
                    } else {
                        model.setType(UserAccountsModel.Acc.ELECTRICITY);
                    }
                    model.setStatus(status);
                    account_list.add(model);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return account_list;
    }

    @Override
    public UserAccountsModel getUserBillByAccount(String nic, String account, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserAccountsModel model = new UserAccountsModel();
        try {
            String tableName;
            String primaryName;
            switch (category.toUpperCase()) {
                case "WATER":
                    tableName = "wAccount_list";
                    primaryName = "water_bill";
                    break;
                case "ELECTRICITY":
                    tableName = "eAccount_list";
                    primaryName = "electricity_bill";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + category);
            }

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT " + primaryName + ".*" + " FROM " + tableName + " JOIN " + primaryName +
                            " ON " + tableName + ".account_number = " +  primaryName + ".account_number" +
                            " WHERE " + tableName + ".nic = ? AND " + tableName + ".account_number = ?" +
                            " ORDER BY " + primaryName + ".dueDate DESC" + // Sort based on dueDate in descending order
                            " LIMIT 1"
            );

            statement.setString(1, nic);
            statement.setString(2, account);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    model.setAmount(result.getString("amount"));
                    model.setBilled_date(result.getString("billedDate"));
                    model.setDueDate(result.getString("dueDate"));
                    UserAccountsModel.Status status = UserAccountsModel.Status.valueOf(
                            result.getString("status")
                    );
                    model.setStatus(status);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return model;
    }
}
