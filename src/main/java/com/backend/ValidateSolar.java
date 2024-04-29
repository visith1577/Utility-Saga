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
import java.util.List;

@WebServlet("/api/validate-solar")
public class ValidateSolar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bnum = req.getParameter("Bnum");


        ValidationDao validationDao = new ValidationDao();
        try {
            JsonObject responseData = new JsonObject();

            if (bnum != null) {
                boolean isBnumExists = validationDao.isBNumExists(bnum);
                Gson gson1 = new Gson();
                JsonElement jsonData1 = gson1.toJsonTree(isBnumExists);
                responseData.add("BnumExists", jsonData1);
            }



            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(responseData.toString());

        } catch (Exception e) {
            resp.getWriter().write("false false false");
        }
    }
}
