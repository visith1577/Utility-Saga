package com.backend;

import com.mysql.cj.Messages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.dao.sendNotificationDao;
import model.SendNotificationModel;

import java.io.IOException;

@WebServlet("/elecNotif")
public class SuperAdminNotification extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String type = req.getParameter("type");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");

        Messages rs = null;
        SendNotificationModel notification = new SendNotificationModel(rs.getString("title"), rs.getString("subject"), rs.getString("message"));

        notification.setTitle(title);
        notification.setType(SendNotificationModel.Type.valueOf(type));
        notification.setSubject(subject);
        notification.setMessage(message);
        sendNotificationDao notificationDAO = new sendNotificationDao();

        try {
            notificationDAO.insertNotification(notification);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}
