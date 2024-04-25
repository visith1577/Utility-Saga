package DAO.dao;

import DAO.impl.Device;
import DAO.impl.ElecWaterAccountsModelImpl;
import utils.Connectdb;
import utils.PreparedStatementResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IotControl implements Device {

    @Override
    public void updateDeviceId(String accountNo, String deviceId, String cat) throws SQLException {
        Connection connection = Connectdb.getConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatementResults createMeterTableStmt = null;
        PreparedStatement createMeterBudgetTableStmt = null;
        PreparedStatement insertInitialBudgetStmt = null;

        ElecWaterAccountsModelImpl helpers = new ElecWaterAccountsDAO();

        try {
            connection.setAutoCommit(false);

            if (cat.equals("ELECTRICITY")) {
                preparedStatement = connection.prepareStatement("UPDATE eAccount_list SET iot_id = ? WHERE account_number = ?");
                preparedStatement.setString(1, deviceId);
                preparedStatement.setString(2, accountNo);
                preparedStatement.executeUpdate();

            } else if (cat.equals("WATER")) {
                preparedStatement = connection.prepareStatement("UPDATE wAccount_list SET iot_id = ? WHERE account_number = ?");
                preparedStatement.setString(1, deviceId);
                preparedStatement.setString(2, accountNo);
                preparedStatement.executeUpdate();

            } else {
                throw new SQLException("Invalid category");
            }

            createMeterTableStmt = helpers.createMeterTable(deviceId, connection);
            createMeterBudgetTableStmt = helpers.createMeterBudgetTable(deviceId, connection);
            insertInitialBudgetStmt = helpers.insertInitialBudget(deviceId, connection);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (createMeterBudgetTableStmt != null) {
                createMeterBudgetTableStmt.close();
            }
            if (insertInitialBudgetStmt != null) {
                insertInitialBudgetStmt.close();
            }
            if (createMeterTableStmt!= null) {
                createMeterTableStmt.getStmt().close();
                createMeterTableStmt.getInsertStmt().close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                Connectdb.closeConnection(connection);
            }
        }
    }

    @Override
    public void deleteDeviceId(String accountNo, String deviceId, String cat) throws SQLException {
        Connection connection = Connectdb.getConnection();
        PreparedStatement preparedStatement = null;

        PreparedStatement deleteMeterTableStmt = null;
        PreparedStatement deleteBudgetTableStmt = null;

        ElecWaterAccountsModelImpl helpers = new ElecWaterAccountsDAO();
        try {
            connection.setAutoCommit(false);

            if (cat.equals("ELECTRICITY")) {
                preparedStatement = connection.prepareStatement("UPDATE eAccount_list SET iot_id = 'NO' AND iot_meter = 'NO' WHERE account_number = ?");
                preparedStatement.setString(1, accountNo);
                preparedStatement.executeUpdate();
            } else if (cat.equals("WATER")) {
                preparedStatement = connection.prepareStatement("UPDATE wAccount_list SET iot_id = 'NO'  AND iot_meter = 'NO' WHERE account_number = ?");
                preparedStatement.setString(1, accountNo);
                preparedStatement.executeUpdate();
            } else {
                throw new SQLException("Invalid category");
            }

            deleteMeterTableStmt = helpers.deleteMeterTable(deviceId, connection);
            deleteBudgetTableStmt = helpers.deleteMeterBudgetTable(deviceId, connection);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (deleteMeterTableStmt != null) {
                deleteMeterTableStmt.close();
            }
            if (deleteBudgetTableStmt != null) {
                deleteBudgetTableStmt.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                Connectdb.closeConnection(connection);
            }
        }
    }
}
