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

        loginDriver(req, resp, "userDashboard.jsp", "userLogin.jsp");
    }

    static void loginDriver(HttpServletRequest req, HttpServletResponse resp, String dash, String login) throws ServletException, IOException {
        String uname = req.getParameter("Uname");
        String pwd = req.getParameter("Pwd");
        HttpSession session = req.getSession();

        Cookie c = new Cookie("user_name" ,uname);

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
                        "/public/HTML/" +
                                dash
                );
                resp.addCookie(c);
                dispatcher.forward(req, resp);
            } else {
                session.setAttribute("status", "failed");
                RequestDispatcher dispatcher = req.getRequestDispatcher(
                        "/public/HTML/" +
                                login
                );
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
