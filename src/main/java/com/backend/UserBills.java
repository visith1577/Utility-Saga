package com.backend;

import DAO.dao.SummaryReportDao;
import DAO.dao.UserAccountsDao;
import DAO.impl.SummaryReport;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccountsModel;
import utils.ReportGenerator;

import java.io.IOException;
import java.sql.SQLException;
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

        String eReport;
        String wReport;

        if (account != null && !account.isEmpty()) {
            try {
                if (Objects.equals(dash, "water")) {
                    UserAccountsModel account_bill = dao.getUserBillByAccount(
                            (String) session.getAttribute("NIC"), account, "WATER"
                    );

                    boolean water_report = summaryReport.checkSummaryExists("water", (String) session.getAttribute("NIC"), account);
                    if (!water_report) {
                        // create a new summary report
                        wReport = gen.dailyReportWater(account, 180, 200, 10_000);
                        summaryReport.insertSummary(wReport, "water", (String) session.getAttribute("NIC"), account);
                    } else {
                        // get the existing summary report
                        wReport = summaryReport.getSummary("water", (String) session.getAttribute("NIC"), account);
                    }


                    Gson gson = new Gson();
                    String jsonData = gson.toJson(account_bill);

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    resp.getWriter().write(jsonData);
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
                    } else {
                        // get the existing summary report
                        eReport = summaryReport.getSummary("electricity", (String) session.getAttribute("NIC"), account);

                    }


                    Gson gson = new Gson();
                    String jsonData = gson.toJson(account_bill);

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    resp.getWriter().write(jsonData);
                }

            } catch (SQLException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Failed to retrieve user details\"}");
                throw new RuntimeException(e);
            }
        }
    }
}
