package DAO.dao;

import DAO.impl.SummaryReport;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SummaryReportDao implements SummaryReport {
    @Override
    public void insertSummary(String summary, String rep, String nic) throws SQLException {

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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table_name + " (nic, summary) VALUES (?, ?)");
            statement.setString(1, nic);
            statement.setString(2, summary);
            statement.executeUpdate();
            statement.close();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getSummary(String nic, String rep) throws SQLException {
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
            PreparedStatement statement = connection.prepareStatement("SELECT summary FROM " + table_name + " WHERE nic = ?");
            statement.setString(1, nic);
            try (var result = statement.executeQuery()) {
                if (result.next()) {
                    summaryString = result.getString("summary");
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
    public boolean checkSummaryExists(String nic, String rep) throws SQLException {
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
            PreparedStatement statement = connection.prepareStatement("SELECT summary FROM " + table_name + " WHERE nic = ?");
            statement.setString(1, nic);
            try (var result = statement.executeQuery()) {
                exists = result.next();
            }
            statement.close();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return exists;
    }
}
