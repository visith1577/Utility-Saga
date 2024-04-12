package com.backend;

import DAO.dao.ValidationDao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/validate-one")
public class ValidateOne extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String nic = req.getParameter("nic");
        String email = req.getParameter("email");

        ValidationDao validationDao = new ValidationDao();
        try {
            JsonObject responseData = new JsonObject();

            if (username != null) {
                boolean isUserNameExists = validationDao.isUserNameExists(username);
                Gson gson1 = new Gson();
                JsonElement jsonData1 = gson1.toJsonTree(isUserNameExists);
                responseData.add("UsernameExists", jsonData1);
            }

            if (nic != null) {
                boolean isNICExists = validationDao.isNICExists(nic);
                Gson gson2 = new Gson();
                JsonElement jsonData2 = gson2.toJsonTree(isNICExists);
                responseData.add("NICExists", jsonData2);
            }

            if (email != null) {
                boolean isEmailExists = validationDao.isEmailExists(email);
                Gson gson3 = new Gson();
                JsonElement jsonData3 = gson3.toJsonTree(isEmailExists);
                responseData.add("EmailExists", jsonData3);
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(responseData.toString());

        } catch (Exception e) {
            resp.getWriter().write("false false false");
        }
    }
}
