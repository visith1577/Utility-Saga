package com.backend;


import DAO.dao.UserDetailsDao;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user-profile")
public class UserDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO.impl.UserDetails dao = new UserDetailsDao();
        try {
            UserModel currentUser = dao.getUserDetailsByNic((String) session.getAttribute("NIC"));

            Gson gson = new Gson();
            String jsonData = gson.toJson(currentUser);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(jsonData);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to retrieve user details\"}");
            throw new RuntimeException(e);
        }
    }
}
