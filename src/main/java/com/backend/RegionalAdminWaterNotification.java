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

@WebServlet("/water/regional-admin/notification")
public class RegionalAdminWaterNotification extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String recipientTypeString = req.getParameter("recipientType");
        RecipientType recipientType = RecipientType.valueOf(recipientTypeString.toUpperCase());
        String recipientId = null;
        if (recipientType == RecipientType.SPECIFIC) {
            recipientId = req.getParameter("recipientId");
        }
        String subject = req.getParameter("subject");
        System.out.println(subject);
        String message = req.getParameter("message");
        System.out.println(message);

        SendNotificationModel notification = new SendNotificationModel();


        Messages rs = null;
//        SendNotificationModel notification = new SendNotificationModel(rs.getString("title"),rs.getString("subject"),rs.getString("message"));
        notification.setTitle(title);
        notification.setRecipientType(recipientType);
        notification.setRecipientId(recipientId);
        notification.setSubject(subject);
        notification.setMessage(message);


        sendNotificationDao notificationDAO = new sendNotificationDao();

        try {
            notificationDAO.rainsertWaterNotification(notification);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }

}