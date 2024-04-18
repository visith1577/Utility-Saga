package DAO.dao;

import DAO.impl.Analytics;
import model.IoTModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AnalyticDao implements Analytics {
    public List<IoTModel> getElectricityMeterDataMonthly(String account) throws SQLException {
        List<IoTModel> elecMeterData = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        try {

            String tableName = account + "_emeter";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, MAX(date), time, data FROM " + tableName + " WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 5 MONTH) AND  CURDATE() GROUP BY MONTH(date), YEAR(date)"
            );

            dataDriver(elecMeterData, statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return elecMeterData;
    }

    public List<IoTModel> getDataForCurrentDate(String account) throws SQLException {
        List<IoTModel> elecMeterData = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        try {
            String tableName = account + "_emeter";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, date, time, data FROM " + tableName + " WHERE DATE(date) = CURDATE()"
            );

            dataDriver(elecMeterData, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return elecMeterData;
    }

    public List<IoTModel> getFinalReadingsForCurrentMonth(String account) throws SQLException {
        List<IoTModel> elecMeterData = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        try {
            String tableName = account + "_emeter";
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, date, MAX(time) as time, data FROM " + tableName +
                            " WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE())" +
                            " GROUP BY date"
            );

            dataDriver(elecMeterData, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }

        return elecMeterData;
    }

    private void dataDriver(List<IoTModel> elecMeterData, PreparedStatement statement) throws SQLException {
        try (ResultSet result = statement.executeQuery()){
            while (result.next()) {
                IoTModel model = new IoTModel();
                model.setId(result.getInt("id"));
                model.setDate(result.getDate("date"));
                model.setTime(result.getTime("time"));
                model.setData(result.getInt("data"));
                elecMeterData.add(model);
            }
        }
    }
}
