package DAO.dao;

import DAO.impl.Notification;
import model.NotificationModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao implements Notification {

    @Override
    public NotificationModel getUserNotificationByNic(String nic, String utility, String area) throws SQLException {
        return null;
    }

    @Override
    public NotificationModel setNotification(String nic, String message, String region, String category, String sent_to, String utility) throws SQLException {
        return null;
    }

    @Override
    public List<NotificationModel> getNotifications(String utility, String area) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<NotificationModel> notificationList = new ArrayList<>();
        String tableName;

        try {
            switch (utility.toUpperCase()) {
                case "ELECTRICITY":
                    tableName = "electricity_notifications";
                    break;
                case "WATER":
                    tableName = "water_notifications";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table name: " + utility);
            }

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE sent_to = ? OR sent_to = 'ALL'");

            statement.setString(1, area);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    NotificationModel notification = new NotificationModel();
                    notification.setMessage(result.getString("message"));
                    notification.setRegion(result.getString("region"));
                    NotificationModel.Category cat = NotificationModel.Category.valueOf(result.getString("category"));
                    notification.setCategory(cat);
                    notification.setDate(result.getDate("date"));
                    notification.setSentTo(result.getString("sent_to"));
                    notificationList.add(notification);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectdb.closeConnection(connection);
        }
        return notificationList;
    }
}
