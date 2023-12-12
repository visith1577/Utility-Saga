package com.backend;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;


//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@WebServlet("/register")
public class RegisterUser extends HttpServlet{
//    private static final long serialVersionUID = 1L;
//    private static final Logger logger = LogManager.getLogger(RegisterUser.class); // logger setup

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String fname = req.getParameter("Fname");
        String lname = req.getParameter("Lname");
        String uname = req.getParameter("Uname");
        String pwd = req.getParameter("Pwd");
        String phone = req.getParameter("phone");
        String rePwd = req.getParameter("Re");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String eBill = req.getParameter("eBill");
        String wBill = req.getParameter("wBill");
        String homePhone = req.getParameter("home-phone");
        String gender = req.getParameter("gender");
        String electricityProvider = req.getParameter("provider");
        String[] services = req.getParameterValues("service");

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utilitySaga?useSSL=false",
                    "root",
                    "Eranda2001"
            );

            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO users(" +
                            "fname, lname, uname, pwd, email, mobile, home, address, " +
                            "gender, provider, ebill, wbill, services) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
            );
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, uname);
            pst.setString(4, pwd);
            pst.setString(5, email);
            pst.setString(6, phone);
            pst.setString(7, homePhone);
            pst.setString(8, address);
            pst.setString(9, gender);
            pst.setString(10, electricityProvider);
            pst.setString(11, eBill);
            pst.setString(12, wBill);
            pst.setString(13, Arrays.toString(services));

            int rowCount = pst.executeUpdate();

            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "/public/HTML/userLogin.jsp"
            );

            if (rowCount > 0) {
                req.setAttribute("status", "success");
            } else {
                req.setAttribute("status", "failed");
            }

            dispatcher.forward(req, resp);

        } catch (SQLException e) {
//                logger.log(Level.valueOf("sql error"), e);
            e.printStackTrace();
        } catch (ServletException e) {
//                logger.log(Level.valueOf("dispatcher error"), e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            logger.log(Level.valueOf("class error"), e);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
//                logger.log(Level.valueOf("dis-connection error"), e);
                e.printStackTrace();
            }
        }

          PrintWriter out = resp.getWriter();
//        out.println(fname);
//        out.println(lname);
//        out.println(uname);
//        out.println(pwd);
//        out.println(phone);

        if (pwd.equals(rePwd)) {
            out.println("user authenticated");
        } else {
            resp.sendRedirect(
                    "http://localhost:8080/UtilitySaga_war_exploded/public/HTML/registerForm.jsp?error=passwordMismatch")
            ;
        }
    }
}
