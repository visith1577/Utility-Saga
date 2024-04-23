package DAO.dao;

import DAO.impl.Analytics;
import model.IoTModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnalyticDao implements Analytics {

    @Override
    public List<IoTModel> getMeterDataMonthly(String meter) throws SQLException {
        List<IoTModel> meterData = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        try {
            String tableName = meter + "_meter";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT MAX(date) as date, MIN(time) as time, MAX(data) as data FROM " + tableName +
                            " WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 13 MONTH) AND  CURDATE() GROUP BY MONTH(date), YEAR(date)"
            );

            dataDriver(meterData, statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return meterData;
    }

    @Override
    public List<IoTModel> getDataForCurrentDate(String account) throws SQLException {
        List<IoTModel> elecMeterData = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        try {
            String tableName = account + "_meter";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT date, time, data FROM " + tableName +
                            " WHERE date = CURDATE()" +
                            "UNION " +
                            "(SELECT date, time, data FROM " + tableName +
                            " WHERE date = DATE_ADD(CURDATE(), INTERVAL 1 DAY) " +
                            "ORDER BY time ASC LIMIT 1)"
            );

            dataDriver(elecMeterData, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return elecMeterData;
    }

    @Override
    public List<IoTModel> getFinalReadingsDailyForCurrentMonth(String account) throws SQLException {
        List<IoTModel> meterData = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        try {
            String tableName = account + "_meter";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT date, MIN(time) as time, MIN(data) as data FROM " + tableName +
                            " WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE())" +
                            " GROUP BY date"
            );

            dataDriver(meterData, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return meterData;
    }

    @Override
    public void setBudget(String account, int budget, String month) throws SQLException {
        Connection connection = Connectdb.getConnection();

        try {
            String tableName = account + "_budget_values";
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + tableName +
                            " (data, month) VALUES (?, ?) "
            );

            statement.setInt(1, budget);
            statement.setString(2, month);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public IoTModel getBudget(String account) throws SQLException {
        Connection connection = Connectdb.getConnection();
        IoTModel model = new IoTModel();

        try {
            String tableName = account + "_budget_values";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT date, data, month FROM " + tableName +
                            " ORDER BY date DESC LIMIT 1"
            );

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    model.setData(result.getInt("data"));
                    model.setMonth(result.getString("month"));
                    model.setDate(result.getDate("date"));
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
    public Map<String, Integer> getBudgetAll(String account) throws SQLException {
        Map<String, Integer> budgetMap = new HashMap<>();

        Connection connection = Connectdb.getConnection();

        try {
            String tableName = account + "_budget_values";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT month, data FROM " + tableName +
                            " ORDER BY date"
            );

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String month = result.getString("month");
                    int budget = result.getInt("data");

                    if(!budgetMap.containsKey(month))
                        budgetMap.put(month, budget);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return budgetMap;
    }


    private void dataDriver(List<IoTModel> meterData, PreparedStatement statement) throws SQLException {
        try (ResultSet result = statement.executeQuery()){
            while (result.next()) {
                IoTModel model = new IoTModel();
                model.setDate(result.getDate("date"));
                model.setTime(result.getTime("time"));
                model.setData(result.getInt("data"));
                meterData.add(model);
            }
        }
    }
}
