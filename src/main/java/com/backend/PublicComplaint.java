package com.backend;

import java.io.*;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/complaint-form")
public class PublicComplaint extends HttpServlet{
    private static final long serialVersionUID = 7L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String accNo = req.getParameter("acc");
        String phone = req.getParameter("phone");
        String date = req.getParameter("day");
        HttpSession session = req.getSession();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utilitySaga?useSSL=false",
                    "root",
                    "root"
            );

            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO complains(accNo, phone, date) values(?, ?, ?)"
            );
            pst.setString(1, accNo);
            pst.setString(2, phone);
            pst.setString(3, date);

            // success and redirect should be implemented
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
