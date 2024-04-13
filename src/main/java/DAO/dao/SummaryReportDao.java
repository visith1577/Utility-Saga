package DAO.dao;

import DAO.impl.SummaryReport;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SummaryReportDao implements SummaryReport {
    @Override
    public void insertSummary(String summary, String rep, String nic, String account_number) throws SQLException {

        Connection connection = Connectdb.getConnection();
        try {
            String table_name;
            switch (rep.toUpperCase()) {
                case "WATER":
                    table_name = "water_summary";
                    break;
                case "ELECTRICITY":
                    table_name = "electricity_summary";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + rep);
            }
            // insert summary into database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table_name + " (nic, data, account_number) VALUES (?, ?, ?)");
            statement.setString(1, nic);
            statement.setString(2, summary);
            statement.setString(3, account_number);
            statement.executeUpdate();
            statement.close();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getSummary(String rep, String nic, String account_number) throws SQLException {
        Connection connection = Connectdb.getConnection();
        String summaryString;
        try {
            String table_name;
            switch (rep.toUpperCase()) {
                case "WATER":
                    table_name = "water_summary";
                    break;
                case "ELECTRICITY":
                    table_name = "electricity_summary";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + rep);
            }
            // get summary from database
            PreparedStatement statement = connection.prepareStatement("SELECT data FROM " + table_name + " WHERE account_number = ? AND nic = ?");
            statement.setString(1, account_number);
            statement.setString(2, nic);
            try (var result = statement.executeQuery()) {
                if (result.next()) {
                    summaryString = result.getString("data");
                } else {
                    summaryString = null;
                }
            }
            statement.close();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return summaryString;
    }

    @Override
    public boolean checkSummaryExists(String rep, String nic, String account_number) throws SQLException {
        Connection connection = Connectdb.getConnection();
        boolean exists;
        try {
            String table_name;
            switch (rep.toUpperCase()) {
                case "WATER":
                    table_name = "water_summary";
                    break;
                case "ELECTRICITY":
                    table_name = "electricity_summary";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + rep);
            }
            PreparedStatement statement = connection.prepareStatement("SELECT data FROM " + table_name + " WHERE nic = ? AND account_number = ?");
            statement.setString(1, nic);
            statement.setString(2, account_number);
            try (var result = statement.executeQuery()) {
                exists = result.next();
            }
            statement.close();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        // System.out.println("Summary exists: " + exists + " for " + rep + " account: " + account_number + " of user: " + nic + ".");
        return exists;
    }
}
