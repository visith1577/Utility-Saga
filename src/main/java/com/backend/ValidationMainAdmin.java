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

@WebServlet("/electricity/main-admin/validation")
public class ValidationMainAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        String contextPath = request.getContextPath();
        String region = request.getParameter("region");
        String empid = request.getParameter("empid");
        String email = request.getParameter("email");

        ValidationDao validationDao = new ValidationDao();
        try {
            JsonObject responseData = new JsonObject();

            if (region != null) {
                boolean isRegionExists = validationDao.isRegionExistsElectricity(region);
                Gson gson1 = new Gson();
                JsonElement jsonData1 = gson1.toJsonTree(isRegionExists);
                responseData.add("RegionExists", jsonData1);
            }

            if (email != null) {
                boolean isEmailExists = validationDao.isEmailExistsElectricity(email);
                Gson gson2 = new Gson();
                JsonElement jsonData2 = gson2.toJsonTree(isEmailExists);
                responseData.add("EmailExists", jsonData2);
            }

            if (empid != null) {
                boolean isEmpIdExists = validationDao.isEmpIDExistsElectricity(empid);
                Gson gson3 = new Gson();
                JsonElement jsonData3 = gson3.toJsonTree(isEmpIdExists);
                responseData.add("EmpIdExists", jsonData3);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseData.toString());

        } catch (Exception e) {
            response.getWriter().write("false false false");
        }
    }
}
