package DAO.dao;

import DAO.impl.Device;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IotControl implements Device {

    @Override
    public void updateDeviceId(String accountNo, String deviceId, String cat) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            if (cat.equals("ELECTRICITY")) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE eAccount_list SET iot_id = ? WHERE account_number = ?");
                preparedStatement.setString(1, deviceId);
                preparedStatement.setString(2, accountNo);
                preparedStatement.executeUpdate();
            } else if (cat.equals("WATER")) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE wAccount_list SET iot_id = ? WHERE account_number = ?");
                preparedStatement.setString(1, deviceId);
                preparedStatement.setString(2, accountNo);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public void deleteDeviceId(String accountNo, String deviceId, String cat) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            if (cat.equals("ELECTRICITY")) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE eAccount_list SET iot_id = 'NO' AND iot_meter = 'NO' WHERE account_number = ?");
                preparedStatement.setString(1, accountNo);
                preparedStatement.executeUpdate();
            } else if (cat.equals("WATER")) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE wAccount_list SET iot_id = 'NO'  AND iot_meter = 'NO' WHERE account_number = ?");
                preparedStatement.setString(1, accountNo);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }
}
