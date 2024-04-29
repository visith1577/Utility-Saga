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
import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/electricity-notification")
public class UserElectricityNotifications extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        System.out.println("Go into doGet");
        String recipientId = session.getAttribute("NIC").toString();

        List<SendNotificationModel> notifications=new ArrayList<>();
        sendNotificationDao dao = new sendNotificationDao();

        try {
            notifications.addAll(dao.getNotificationsByRecipientId(recipientId));
            notifications.addAll(dao.getAllNotifications());
            System.out.println("else is right");

            // Set the notifications attribute in the request
            request.setAttribute("notifications", notifications);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/public/HTML/user/electricity/electricity-notification.jsp");
            dispatcher.forward(request, response);


        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"error\": \"Failed to update user image\"}");
            request.setAttribute("errorMessage", "{\"error\": \"Failed to update user image\"}");
            request.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(request, response);
        }
    }
}
