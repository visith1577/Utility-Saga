package com.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.dao.UserDetailsDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user-status")
public class UpdateAccountStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        System.out.println("doPost method called");
        String accountNumber = request.getParameter("accountNumber");
        String newStatus = request.getParameter("newStatus");

        UserDetailsDao dao = new UserDetailsDao();

        try {
            dao.updateAccountStatus(accountNumber, newStatus);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"" + newStatus + "\"}");
    }

}
