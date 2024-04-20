package com.backend;

import DAO.dao.AnalyticDao;
import DAO.dao.UserAccountsDao;
import DAO.impl.Analytics;
import DAO.impl.UserAccounts;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.IoTModel;
import utils.AnalysisHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet(name = "Analytics", value = "/user/electricity-analytics")
public class ElectricityAnalytics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String account = req.getParameter("account");


        UserAccounts dao = new UserAccountsDao();
        Analytics analytics = new AnalyticDao();
        JsonObject responseData = new JsonObject();

        try {
            Map<String, String> account_list = dao.getUserAccountsWithIotStatus(
                    (String) session.getAttribute("NIC"),
                    "ELECTRICITY"
            );

            req.setAttribute("electricity_account_list", account_list);

            if (account != null) {
                Gson gson = new Gson();
                List<IoTModel> data_list_daily = analytics.getFinalReadingsDailyForCurrentMonth(account);

                AnalysisHelper analysisHelper = new AnalysisHelper();
                List<IoTModel> data_list_monthly = analysisHelper.getDifferenceBetweenReadings(account);
                List<IoTModel> final_models = calculateMonthlyBills(data_list_monthly);
                Map<String, Integer> budgetMap = analytics.getBudgetAll(account);
                IoTModel budget = analytics.getBudget(account);


                JsonElement jsonDataMonthly = gson.toJsonTree(final_models);
                responseData.add("data_list_monthly", jsonDataMonthly);

                JsonElement jsonData = gson.toJsonTree(data_list_daily);
                responseData.add("data_list_daily", jsonData);

                JsonElement budgetData = gson.toJsonTree(budgetMap);
                responseData.add("budget", budgetData);

                JsonElement budgetData1 = gson.toJsonTree(budget);
                responseData.add("budgetLatest", budgetData1);

                session.setAttribute("page_account", account);


                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write(responseData.toString());
            } else {

                RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/electricity/electricityAnalysis.jsp");
                dispatcher.forward(req, resp);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String month = req.getParameter("month");
        String expectedUnits = req.getParameter("expectedUnits");
        String account = session.getAttribute("page_account").toString();

        Analytics dao = new AnalyticDao();


        try {
            try {
                dao.setBudget(account, Integer.parseInt(expectedUnits), month);
                resp.sendRedirect(req.getHeader("referer"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }

    public static List<IoTModel> calculateMonthlyBills(List<IoTModel> iotModels) {
    List<IoTModel> updatedModels = new ArrayList<>();
    AnalysisHelper analysisHelper = new AnalysisHelper();

    for (IoTModel model : iotModels) {
        double monthlyBill = analysisHelper.calculateDomesticBill(model.getData(), 0, 0);
        model.setMonthlyBill(monthlyBill);
        updatedModels.add(model);
    }

    return updatedModels;
}
}
