package DAO.impl;

import model.SendNotificationModel;

import java.sql.SQLException;
import java.util.List;

public interface SendNotificationImpl {
    public void insertNotification(SendNotificationModel notification) throws SQLException;

    List<SendNotificationModel> getNotifications(SendNotificationModel.Type type) throws SQLException;

    public void rainsertNotification(SendNotificationModel notification) throws SQLException;

    List<SendNotificationModel> getAllNotifications() throws SQLException;

    List<SendNotificationModel> getNotificationsByRecipientId(String recipientId) throws SQLException;
}
