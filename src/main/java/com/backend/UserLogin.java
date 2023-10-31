package com.backend;

import java.io.*;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
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
        HttpSession session = req.getSession();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utilitySaga?useSSL=false",
                    "root",
                    "root"
            );

            PreparedStatement pst = connection.prepareStatement(
                    "select * from users where uname = ? and pwd = ?"
            );
            pst.setString(1, uname);
            pst.setString(2, pwd);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                session.setAttribute("name", rs.getString("uname"));
                RequestDispatcher dispatcher = req.getRequestDispatcher(
                        "/public/HTML/electricitydashboard.jsp"
                );
                dispatcher.forward(req, resp);
            } else {
                session.setAttribute("status", "failed");
                RequestDispatcher dispatcher = req.getRequestDispatcher(
                        "/public/HTML/userLogin.jsp"
                );
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
