package DAO.impl;

import model.NotificationModel;

import java.sql.SQLException;

public interface Notification {
    NotificationModel getUserNotificationByNic(String nic) throws SQLException;
}
