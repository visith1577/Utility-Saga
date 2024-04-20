package com.backend;

import DAO.dao.AnalyticDao;
import DAO.dao.SummaryReportDao;
import DAO.dao.UserAccountsDao;
import DAO.impl.Analytics;
import DAO.impl.SummaryReport;
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
import model.UserAccountsModel;
import utils.ReportGenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/user/my-bills")
public class UserBills extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String account = req.getParameter("account");
        String dash = req.getParameter("currDash");
        DAO.impl.UserAccounts dao = new UserAccountsDao();
        SummaryReport summaryReport = new SummaryReportDao();
        ReportGenerator gen = new ReportGenerator();
        Analytics analytics = new AnalyticDao();

        String eReport;
        String wReport;

        if (account != null && !account.isEmpty()) {
            try {
                JsonObject responseData = new JsonObject();

                if (Objects.equals(dash, "water")) {
                    UserAccountsModel account_bill = dao.getUserBillByAccount(
                            (String) session.getAttribute("NIC"), account, "WATER"
                    );

                    boolean water_report = summaryReport.checkSummaryExists("water", (String) session.getAttribute("NIC"), account);
                    if (!water_report) {
                        // create a new summary report
                        wReport = gen.dailyReportWater(account, 180, 200, 10_000);
                        summaryReport.insertSummary(wReport, "water", (String) session.getAttribute("NIC"), account);
                    }

                    wReport = summaryReport.getSummary("water", (String) session.getAttribute("NIC"), account);
                    Gson gson1 = new Gson();
                    JsonElement jsonData1 = gson1.toJsonTree(wReport);
                    responseData.add("report", jsonData1);


                    Gson gson2 = new Gson();
                    JsonElement jsonData2 = gson2.toJsonTree(account_bill);
                    responseData.add("bill", jsonData2);


                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    resp.getWriter().write(responseData.toString());
                }

                if (Objects.equals(dash, "electricity")) {
                    UserAccountsModel account_bill = dao.getUserBillByAccount(
                            (String) session.getAttribute("NIC"), account, "ELECTRICITY"
                    );

                    boolean elec_report = summaryReport.checkSummaryExists("electricity", (String) session.getAttribute("NIC"), account);
                    if (!elec_report) {
                        // create a new summary report
                        eReport= gen.dailyReportElectricity(account, 180, 200, 10_000);
                        summaryReport.insertSummary(eReport, "electricity", (String) session.getAttribute("NIC"), account);
                    }

                    eReport = summaryReport.getSummary("electricity", (String) session.getAttribute("NIC"), account);
                    Gson gson1 = new Gson();
                    JsonObject jsonObject = gson1.fromJson(eReport, JsonObject.class);
                    JsonElement jsonData1 = gson1.toJsonTree(jsonObject);
                    responseData.add("report", jsonData1);


                    Gson gson2 = new Gson();
                    JsonElement jsonData2 = gson2.toJsonTree(account_bill);
                    responseData.add("bill", jsonData2);


                    Gson gson3 = new Gson();
                    List<IoTModel> data_list_daily = analytics.getDataForCurrentDate(account);
                    JsonElement jsonData = gson3.toJsonTree(data_list_daily);
                    responseData.add("data_list_daily", jsonData);


                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    resp.getWriter().write(responseData.toString());
                }

            } catch (SQLException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Failed to retrieve user details\"}");
                throw new RuntimeException(e);
            }
        }
    }
}
