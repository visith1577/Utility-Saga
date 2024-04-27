package com.backend;

import DAO.dao.ElectricityAdminDAO;
import DAO.dao.WaterAdminDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.dao.UserDetailsDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/water/main-admin/region-status")
public class UpdateWaterRegionStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet called");
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        System.out.println("JsonObject: "+jsonObject);
        String region = jsonObject.get("region").getAsString();
        System.out.println("Region: "+region);
        String newStatus = jsonObject.get("newStatus").getAsString();
        System.out.println("New Sttaus: "+newStatus);


        JsonElement changeStatus = jsonObject.get("changeStatus");

        WaterAdminDAO dao = new WaterAdminDAO();

        try {

            if (changeStatus.getAsBoolean()) {
                dao.updateAccountStatus(region, newStatus);
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"" + newStatus + "\"}");
            } else {
                String status = Objects.equals(newStatus, "ACTIVE") ? "INACTIVE" : "ACTIVE";
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"" + status + "\"}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
