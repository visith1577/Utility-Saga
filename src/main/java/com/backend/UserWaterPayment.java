package com.backend;

import DAO.dao.UserBillPaymentDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/water/payment")
public class UserWaterPayment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        String accountNumber = jsonObject.get("accountNumber").getAsString();
        String billId = jsonObject.get("billId").getAsString();
        Double amount = jsonObject.get("amount").getAsDouble();

        UserBillPaymentDAO dao = new UserBillPaymentDAO();

        try{
            dao.updateWaterBill(accountNumber, billId, amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
