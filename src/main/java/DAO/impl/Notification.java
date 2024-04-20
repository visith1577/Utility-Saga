package DAO.impl;

import model.NotificationModel;

import java.sql.SQLException;
import java.util.List;

public interface Notification {
    NotificationModel getUserNotificationByNic(String nic, String utility, String area) throws SQLException;
    NotificationModel setNotification(String nic, String message, String region, String category, String sent_to, String utility) throws SQLException;
    List<NotificationModel> getNotifications(String utility, String area) throws SQLException;
}
