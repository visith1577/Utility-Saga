package DAO.impl;

import model.SendNotificationModel;

import java.sql.SQLException;
import java.util.List;

public interface SendNotificationImpl {
    public void insertNotification(SendNotificationModel notification) throws SQLException;

        List<SendNotificationModel>getNotifications(SendNotificationModel.Type type)throws SQLException;
}
