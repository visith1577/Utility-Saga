package com.backend;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class UserLogin extends HttpServlet{
    private static final long serialVersionUID = 2L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uname = req.getParameter("Uname");
        String pwd = req.getParameter("Pwd");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utilitySaga?useSSL=false",
                    "root",
                    "root"
            );

            PreparedStatement pst = connection.prepareStatement(
                    "select * from users where email = ? and pwd = ?"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
