package com.backend;

import com.mysql.cj.Messages;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.dao.sendNotificationDao;
import model.SendNotificationModel;
import model.SendNotificationModel.RecipientType;

import java.sql.SQLException;
import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/raelecNotif")
public class RegionalAdminElectriciyNotification extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String recipientTypeString = req.getParameter("recipientType");
        RecipientType recipientType = RecipientType.valueOf(recipientTypeString.toUpperCase());
        String recipientId = null;
        if (recipientType == RecipientType.SPECIFIC) {
            recipientId = req.getParameter("recipientId");
        }
        String dateStr = req.getParameter("date");
        LocalDate date = LocalDate.parse(dateStr);
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");

        SendNotificationModel notification = new SendNotificationModel();


        Messages rs = null;
//        SendNotificationModel notification = new SendNotificationModel(rs.getString("title"),rs.getString("subject"),rs.getString("message"));
        notification.setTitle(title);
        notification.setRecipientType(recipientType);
        notification.setRecipientId(recipientId);
        notification.setDate(date.atStartOfDay());
        notification.setSubject(subject);
        notification.setMessage(message);


        sendNotificationDao notificationDAO = new sendNotificationDao();

        try {
            notificationDAO.rainsertNotification(notification);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Go into doGet");
        String recipientId = request.getParameter("recipientId");

        List<SendNotificationModel> notifications=new ArrayList<>();
        sendNotificationDao dao = new sendNotificationDao();

        try {
            if (recipientId == null || recipientId.isEmpty()) {
                notifications= dao.getAllNotifications();
                System.out.println("if is correct");
            } else {
                notifications.addAll(dao.getNotificationsByRecipientId(recipientId));
                System.out.println("else is right");
            }


            // Set the notifications attribute in the request
            request.setAttribute("notifications", notifications);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/public/HTML/user/electricity/electricity-notification.jsp");
            dispatcher.forward(request, response);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


