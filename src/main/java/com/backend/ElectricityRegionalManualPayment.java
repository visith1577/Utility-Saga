package com.backend;

import DAO.dao.ElectricityManualPaymentDao;
import com.mysql.cj.Messages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ManualPaymentsModel;
import model.SendNotificationModel;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/elemanualpayment")
public class ElectricityRegionalManualPayment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account_number = request.getParameter("account_number");
        String nic = request.getParameter("nic");
        String amount = request.getParameter("amount");
        String dateStr = request.getParameter("date");
        LocalDate date = LocalDate.parse(dateStr);

        ManualPaymentsModel manualpayment = new ManualPaymentsModel();
        manualpayment.setAccount_number(account_number);
        manualpayment.setNic(nic);
        manualpayment.setAmount(amount);
        manualpayment.setDate(date.atStartOfDay());

        ElectricityManualPaymentDao manualPaymentDao = new ElectricityManualPaymentDao();

        try {
            manualPaymentDao.insertintomanualpayment(manualpayment);
            response.sendRedirect(request.getHeader("referer"));
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(request, response);
        }
    }
}
