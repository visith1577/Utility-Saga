package DAO.dao;

import DAO.impl.Device;
import DAO.impl.ElecWaterAccountsModelImpl;
import utils.Connectdb;
import utils.PreparedStatementResults;

import java.sql.*;
import java.util.Objects;

public class IotControl implements Device {

    @Override
    public void updateDeviceId(String accountNo, String deviceId, String prevDeviceId, String cat) throws SQLException {
        Connection connection = Connectdb.getConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatementResults createMeterTableStmt = null;
        PreparedStatement backupMeterTableStmt = null;
        PreparedStatement createMeterBudgetTableStmt = null;
        PreparedStatement insertInitialBudgetStmt = null;
        PreparedStatement deleteMeterTableStmt = null;
        PreparedStatement deleteBudgetTableStmt = null;

        ElecWaterAccountsModelImpl helpers = new ElecWaterAccountsDAO();

        try {
            connection.setAutoCommit(false);
            if (!Objects.equals(prevDeviceId, "NO")){
                backupMeterTableStmt = createBackupTable(prevDeviceId, connection);
                deleteMeterTableStmt = helpers.deleteMeterTable(prevDeviceId, connection);
                deleteBudgetTableStmt = helpers.deleteMeterBudgetTable(prevDeviceId, connection);
            }

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
            if (deleteMeterTableStmt != null) {
                deleteMeterTableStmt.close();
            }
            if (deleteBudgetTableStmt != null) {
                deleteBudgetTableStmt.close();
            }
            if (backupMeterTableStmt != null) {
                backupMeterTableStmt.close();
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
        PreparedStatement backupMeterTableStmt = null;
        PreparedStatement deleteMeterTableStmt = null;
        PreparedStatement deleteBudgetTableStmt = null;

        ElecWaterAccountsModelImpl helpers = new ElecWaterAccountsDAO();
        try {
            connection.setAutoCommit(false);

            if (cat.equals("ELECTRICITY")) {
                preparedStatement = connection.prepareStatement("UPDATE eAccount_list SET iot_id = 'NO', iot_meter = 'NO' WHERE account_number = ?");
                preparedStatement.setString(1, accountNo);
                preparedStatement.executeUpdate();
            } else if (cat.equals("WATER")) {
                preparedStatement = connection.prepareStatement("UPDATE wAccount_list SET iot_id = 'NO', iot_meter = 'NO' WHERE account_number = ?");
                preparedStatement.setString(1, accountNo);
                preparedStatement.executeUpdate();
            } else {
                throw new SQLException("Invalid category");
            }

            backupMeterTableStmt = createBackupTable(deviceId, connection);

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
            if (backupMeterTableStmt != null) {
                backupMeterTableStmt.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                Connectdb.closeConnection(connection);
            }
        }
    }

    private PreparedStatement createBackupTable(String deviceId, Connection connection) throws SQLException {
        String meterTable = deviceId + "_meter";
        String backupMeterTable = deviceId + "_meter_backup";
        int backupTableCounter = 1;

        while (tableExists(backupMeterTable, connection)) {
            backupMeterTable = deviceId + "_meter_backup" + backupTableCounter;
            backupTableCounter++;
        }

        PreparedStatement createBackupMeterTableStmt = connection.prepareStatement("CREATE TABLE " + backupMeterTable + " AS SELECT * FROM " + meterTable);
        createBackupMeterTableStmt.executeUpdate();

        return createBackupMeterTableStmt;
    }

    private boolean tableExists(String tableName, Connection connection) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet result = dbm.getTables(null, null, tableName, null);
        return result.next();
    }

//    private String exportTableToCSV(String tableName, Connection connection) throws SQLException {
//
//        StringBuilder csvData = new StringBuilder();
//        String sql = "SELECT * FROM " + tableName;
//
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery();
//        ResultSetMetaData metaData = rs.getMetaData();
//        int columnCount = metaData.getColumnCount();
//
//        for (int i = 1; i <= columnCount; i++) {
//            csvData.append(metaData.getColumnName(i));
//            if (i != columnCount) {
//                csvData.append(",");
//            }
//        }
//        csvData.append("\n");
//
//        while (rs.next()) {
//            for (int i = 1; i <= columnCount; i++) {
//                csvData.append(rs.getString(i));
//                if (i != columnCount) {
//                    csvData.append(",");
//                }
//            }
//            csvData.append("\n");
//        }
//
//        return csvData.toString();
//    }
}
