package DAO.dao;

import DAO.impl.UserAccounts;
import model.UserAccountsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAccountsDao implements UserAccounts {

    @Override
    public List<UserAccountsModel> getUserAccounts(String nic, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<UserAccountsModel> account_list = new ArrayList<>();

        try {
            String tableName = selectTable(category);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT  account_number, user_status, meter_status, iot_meter FROM " + tableName + " WHERE nic = ?"
            );

            statement.setString(1, nic);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()){
                    UserAccountsModel model = new UserAccountsModel();
                    model.setAccount_number(result.getString("account_number"));
                    UserAccountsModel.UserStatus user_status = UserAccountsModel.UserStatus.valueOf(
                            result.getString("user_status")
                    );
                    UserAccountsModel.MeterStatus meter_status = UserAccountsModel.MeterStatus.valueOf(
                            result.getString("meter_status")
                    );
                    UserAccountsModel.Iot iot_status = UserAccountsModel.Iot.valueOf(
                            result.getString("iot_meter")
                    );
                    model.setUserStatus(user_status);
                    model.setMeterStatus(meter_status);
                    model.setIot(iot_status);

                    if (category.equals("WATER")) {
                        model.setType(UserAccountsModel.Acc.WATER);
                    } else {
                        model.setType(UserAccountsModel.Acc.ELECTRICITY);
                    }

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

    public List<String> getUserAccountsWithStatus(String nic, String category, String status) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<String> account_list = new ArrayList<>();

        try {
            String tableName = selectTable(category);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT  account_number FROM " + tableName + " WHERE nic = ? AND user_status = ?"
            );

            statement.setString(1, nic);
            statement.setString(2, status);
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
    public Map<String, String> getUserAccountsWithIotStatus(String nic, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Map<String, String> account_list = new HashMap<>();

        try {
            String tableName = selectTable(category);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT  account_number, meter_status FROM " +
                            tableName +
                            " WHERE nic = ? AND " +
                            "user_status = ? AND " +
                            "iot_meter = ? "
            );

            statement.setString(1, nic);
            statement.setString(2, "ACTIVE");
            statement.setString(3, "YES");

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()){
                    account_list.put(result.getString("account_number"), result.getString("meter_status"));
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
    public String getIotIdForAccount(String account, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        String iotId = null;

        try {
            String tableName = selectTable(category);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT iot_id FROM " + tableName + " WHERE account_number = ?"
            );

            statement.setString(1, account);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    iotId = result.getString("iot_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return iotId;
    }

    @Override
    public List<String> getAvailableRegions(String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<String> regions = new ArrayList<>();

        try {
            String tableName;
            switch (category.toUpperCase()) {
                case "WATER":
                    tableName = "water_region";
                    break;
                case "ELECTRICITY":
                    tableName = "electricity_region";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + category);
            }

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT DISTINCT region FROM " + tableName
            );

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()){
                    regions.add(result.getString("region"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return regions;
    }

    @Override
    public Map<String, String> getAccountsWithRegion(String account, String category, String status) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Map<String, String> account_list = new HashMap<>();

        try {
            String tableName = selectTable(category);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT account_number, region FROM " + tableName + " WHERE account_number = ? AND user_status = ?"
            );

            statement.setString(1, account);
            statement.setString(2, status);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    account_list.put(result.getString("account_number"), result.getString("region"));
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
                    "SELECT " + " region, balance, " + primaryName + ".*" + " FROM " + tableName + " JOIN " + primaryName +
                            " ON " + tableName + ".account_number = " +  primaryName + ".account_number" +
                            " WHERE " + tableName + ".nic = ? AND " + tableName + ".account_number = ?" +
                            " ORDER BY " + primaryName + ".dueDate DESC" + // Sort based on dueDate in descending order
                            " LIMIT 1"
            );

            statement.setString(1, nic);
            statement.setString(2, account);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    model.setRegion(result.getString("region"));
                    model.setAmount(result.getString("balance"));
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

    @Override
    public void updateAccountStatus(String nic, String account, String status, String table) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
             String tableName = selectTable(table);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + tableName + " SET user_status = ? WHERE account_number = ? AND nic = ?"
            );

            statement.setString(1, status);
            statement.setString(2, account);
            statement.setString(3, nic);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public boolean checkAccountExists(String nic, String account, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        boolean exists = false;
        try {
            String tableName = selectTable(category);

            PreparedStatement statement;

            if (account != null) {
                statement = connection.prepareStatement(
                        "SELECT account_number FROM " + tableName + " WHERE nic = ? AND account_number = ?"
                );
                statement.setString(2, account);
            } else {
                statement = connection.prepareStatement(
                        "SELECT account_number FROM " + tableName + " WHERE nic = ?"
                );
            }

            statement.setString(1, nic);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    exists = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return exists;
    }




    private String selectTable(String category) {
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

        return  tableName;
    }
}
