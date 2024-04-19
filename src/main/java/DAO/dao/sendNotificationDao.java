package DAO.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
