package DAO.impl;

import java.sql.SQLException;

public interface Device {
    void updateDeviceId(String accountNo, String deviceId, String cat) throws SQLException;
    void deleteDeviceId(String accountNo, String deviceId, String cat) throws SQLException;
}
