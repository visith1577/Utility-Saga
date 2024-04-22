package DAO.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import DAO.impl.SendNotificationImpl;
import model.SendNotificationModel;
import utils.Connectdb;
public class sendNotificationDao implements SendNotificationImpl {
    public void insertNotification(SendNotificationModel notification) throws SQLException{
            Connection connection=Connectdb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO superadmin_notification (title, type, subject, message) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, notification.getTitle());
            preparedStatement.setString(2, String.valueOf(notification.getType()));
            preparedStatement.setString(3, notification.getSubject());
            preparedStatement.setString(4, notification.getMessage());
            preparedStatement.executeUpdate();
    }
    @Override

    public List<SendNotificationModel>getNotifications(SendNotificationModel.Type type)throws SQLException{
        List<SendNotificationModel> NotificationList=new ArrayList<>();
        Connection con =Connectdb.getConnection();

        String sql="SELECT title, subject,message FROM superadmin_notification WHERE type=? ";
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setString(1,type.toString().toUpperCase());

        ResultSet rs=stmt.executeQuery();

        while (rs.next()){
            SendNotificationModel notificationModel=new SendNotificationModel(
                    rs.getString("title"),
                    SendNotificationModel.Type.valueOf(type.toString().toUpperCase()),
                    rs.getString("subject"),
                    rs.getString("message")
            );
            NotificationList.add(notificationModel);
        }
        rs.close();
        stmt.close();
        con.close();
        return NotificationList;
    }

    @Override

    public void rainsertNotification(SendNotificationModel notification)throws SQLException{
            Connection connection=Connectdb.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO electricity_regionaladmin_notification( title, recipientType, recipientId, date, subject, message) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,notification.getTitle());
            preparedStatement.setString(2, String.valueOf(notification.getRecipientType()).toUpperCase());
            preparedStatement.setString(3,notification.getRecipientId());
            preparedStatement.setString(4, String.valueOf(notification.getDate()));
            preparedStatement.setString(5,notification.getSubject());
            preparedStatement.setString(6,notification.getMessage());
            preparedStatement.executeUpdate();
        }

        @Override
    public List<SendNotificationModel> getAllNotifications() throws SQLException {
        System.out.println("Calling the get Not function for all");
        List<SendNotificationModel> notifications = new ArrayList<>();
        Connection connection = Connectdb.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM electricity_regionaladmin_notification WHERE recipientType = 'ALL'");
        ResultSet rs = stmt.executeQuery();
        System.out.println("Executed the query");
        {
            while (rs.next()) {
                SendNotificationModel notification = new SendNotificationModel();
                notification.setTitle(rs.getString("title"));
                notification.setRecipientType(SendNotificationModel.RecipientType.valueOf(rs.getString("recipientType")));
                notification.setDate(rs.getTimestamp("date").toLocalDateTime());
                notification.setSubject(rs.getString("subject"));
                notification.setMessage(rs.getString("message"));
                notifications.add(notification);
            }
        }
        return notifications;
    }

    @Override
    public List<SendNotificationModel> getNotificationsByRecipientId(String recipientId) throws SQLException {
        System.out.println("Calling the get Not function for several user");
        List<SendNotificationModel> notifications = new ArrayList<>();

        Connection connection = Connectdb.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT n.* FROM electricity_regionaladmin_notification n " +
                "JOIN users u ON n.recipientId = u.nic " +  "WHERE n.recipientId = ? AND n.recipientType = 'SPECIFIC'"); {
                    System.out.println(recipientId);

            stmt.setString(1, recipientId);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next()) {
                    SendNotificationModel notification = new SendNotificationModel();
                    notification.setTitle(rs.getString("title"));
                    notification.setRecipientType(SendNotificationModel.RecipientType.valueOf(rs.getString("recipientType")));
                    notification.setDate(rs.getTimestamp("date").toLocalDateTime());
                    notification.setSubject(rs.getString("subject"));
                    notification.setMessage(rs.getString("message"));
                    notifications.add(notification);
                }
                System.out.println("try execute fun 2");

            }
        }

        return notifications;
    }
}
