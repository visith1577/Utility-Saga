package com.backend;

import DAO.dao.SummaryReportDao;
import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import DAO.impl.SummaryReport;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccountsModel;
import model.UserModel;
import utils.ReportGenerator;

import java.io.IOException;
import java.io.InputStream;
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

        if (account != null && !account.isEmpty()) {
            try {
                if (Objects.equals(dash, "water")) {
                    UserAccountsModel account_bill = dao.getUserBillByAccount(
                            (String) session.getAttribute("NIC"), account, "WATER"
                    );

                    String elec_report = summaryReport.getSummary("water", (String) session.getAttribute("NIC"));
                    if (elec_report == null) {
                        // create a new summary report
                        elec_report = gen.dailyReportWater(account, 180, 200, 10_000);
                        summaryReport.insertSummary(elec_report, "water", (String) session.getAttribute("NIC"));
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

                    String elec_report = summaryReport.getSummary("electricity", (String) session.getAttribute("NIC"));
                    if (elec_report == null) {
                        // create a new summary report
                        elec_report = gen.dailyReportElectricity(account, 180, 200, 10_000);
                        summaryReport.insertSummary(elec_report, "electricity", (String) session.getAttribute("NIC"));
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
