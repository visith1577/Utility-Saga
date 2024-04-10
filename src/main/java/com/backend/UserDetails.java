package com.backend;


import DAO.dao.UserDetailsDao;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;


@MultipartConfig
@WebServlet("/user/api/user-profile")
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nic = (String) session.getAttribute("NIC");
        String uname = req.getParameter("user_name").strip();
        String phone = req.getParameter("telephone").strip();
        String email = req.getParameter("email").strip();
        InputStream item = req.getPart("image").getInputStream();

        UserModel user = new UserModel();
        user.setNic(nic);
        user.setUsername(uname);
        user.setEmail(email);
        user.setMobile(phone);
        user.setImage(item);

        DAO.impl.UserDetails userDao = new UserDetailsDao();

        try {
            userDao.updateUserInfo(user);
            session.setAttribute("UNAME", uname);
            session.setAttribute("TELEPHONE", user.getMobile());
            session.setAttribute("EMAIL", user.getEmail());
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.setAttribute("errorMessage", "{\"error\": \"Failed to update user image\"}");
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }

        try {
            String image = userDao.getImageByNic(nic);
            if (image == null) {
                userDao.insertImage(user);
            } else {
                userDao.updateImage(user);
            }
            resp.sendRedirect(req.getHeader("referer"));
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"error\": \"Failed to update user image\"}");
            req.setAttribute("errorMessage", "{\"error\": \"Failed to update user image\"}");
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}
