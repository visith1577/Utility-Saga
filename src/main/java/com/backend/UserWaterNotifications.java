package com.backend;


import com.mysql.cj.Messages;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.dao.sendNotificationDao;
import jakarta.servlet.http.HttpSession;
import model.SendNotificationModel;
import model.SendNotificationModel.RecipientType;

import java.sql.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/water-notification")
public class UserWaterNotifications extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        System.out.println("Go into doGet");
        String recipientId = session.getAttribute("NIC").toString();

        List<SendNotificationModel> notifications=new ArrayList<>();
        sendNotificationDao dao = new sendNotificationDao();

        try {
            notifications.addAll(dao.getWaterNotificationsByRecipientId(recipientId));
            notifications.addAll(dao.getAllWaterNotifications());

            // Set the notifications attribute in the request
            request.setAttribute("waternotifications", notifications);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/public/HTML/user/water/water-notification.jsp");
            dispatcher.forward(request, response);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
