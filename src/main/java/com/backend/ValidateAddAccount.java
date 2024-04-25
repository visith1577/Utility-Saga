package com.backend;

import DAO.dao.ValidationDao;
import DAO.impl.Validations;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(
        name = "validateAddAccount",
        urlPatterns = {
                "/electricity/regional-admin/api/validate-add-account",
                "/water/regional-admin/api/validate-add-account"
        })
public class ValidateAddAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqId = req.getParameter("reqId");
        String accountNo = req.getParameter("accountNo");
        String iotId = req.getParameter("iotId");

        Validations dao = new ValidationDao();

        try {
            JsonObject responseData = new JsonObject();

            if (Objects.equals(req.getServletPath(), "/electricity/regional-admin/api/validate-add-account")) {

                if (reqId != null && !reqId.isEmpty()) {
                    boolean isReqIdExists = dao.isRequestIdExist(Integer.parseInt(reqId), "ELECTRICITY");
                    Gson gson1 = new Gson();
                    JsonElement jsonData1 = gson1.toJsonTree(isReqIdExists);
                    responseData.add("ReqIdExists", jsonData1);

                }
                if (accountNo != null) {

                    boolean isAccountNoExists = dao.isAccountExists(accountNo, "ELECTRICITY");
                    Gson gson2 = new Gson();
                    JsonElement jsonData2 = gson2.toJsonTree(isAccountNoExists);
                    responseData.add("AccountNoExists", jsonData2);

                }
                if (iotId != null) {

                    boolean iotIdExists = dao.isMeterExists(iotId, "ELECTRICITY");
                    boolean check = dao.isMeterExists(iotId, "WATER");
                    boolean isIotIdExists = iotIdExists || check;
                    Gson gson3 = new Gson();
                    JsonElement jsonData3 = gson3.toJsonTree(isIotIdExists);
                    responseData.add("IotIdExists", jsonData3);

                }

            } else if (Objects.equals(req.getServletPath(), "/water/regional-admin/api/validate-add-account")) {

                if (reqId != null && !reqId.isEmpty()){

                    boolean isReqIdExists = dao.isRequestIdExist(Integer.parseInt(reqId), "WATER");
                    Gson gson1 = new Gson();
                    JsonElement jsonData1 = gson1.toJsonTree(isReqIdExists);
                    responseData.add("ReqIdExists", jsonData1);

                }
                if (accountNo != null) {

                    boolean isAccountNoExists = dao.isAccountExists(accountNo, "WATER");
                    Gson gson2 = new Gson();
                    JsonElement jsonData2 = gson2.toJsonTree(isAccountNoExists);
                    responseData.add("AccountNoExists", jsonData2);

                }
                if (iotId != null) {

                    boolean iotIdExists = dao.isMeterExists(iotId, "WATER");
                    boolean check = dao.isMeterExists(iotId, "ELECTRICITY");
                    boolean isIotIdExists = iotIdExists || check;
                    Gson gson3 = new Gson();
                    JsonElement jsonData3 = gson3.toJsonTree(isIotIdExists);
                    responseData.add("IotIdExists", jsonData3);

                }
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(responseData.toString());

        } catch (SQLException e) {
            resp.getWriter().write("false false false");
        }
    }
}
