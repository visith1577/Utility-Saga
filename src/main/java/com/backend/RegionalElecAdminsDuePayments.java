package com.backend;

import DAO.dao.UserBillPaymentDAO;
import DAO.impl.UserBillPaymentImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BillModel;

import java.io.IOException;
import java.util.List;


@WebServlet("/electricity/regional-admin/due-payment")
public class RegionalElecAdminsDuePayments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BillModel> electricityOverdueBills;
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        System.out.println("ID: " + id);

        UserBillPaymentImpl dao = new UserBillPaymentDAO();

        try {
            if (id == null || id.isEmpty()) {
                electricityOverdueBills = dao.getOverdueElectricityBills(session.getAttribute("REGION").toString());
            } else {
                electricityOverdueBills = dao.getOverdueElectricityBillsByAN(session.getAttribute("REGION").toString(),id);
            }

            if (electricityOverdueBills.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Electricity Overdue Payments: " + electricityOverdueBills);
                req.setAttribute("electricityOverdueBills", electricityOverdueBills);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/electricity/regionalAdmin/payments-due.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
