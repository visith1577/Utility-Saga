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


@WebServlet("/water/regional-admin/due-payment")
public class RegionalWaterAdminsDuePayments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BillModel> waterOverdueBills;
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        System.out.println("ID: " + id);

        UserBillPaymentImpl dao = new UserBillPaymentDAO();

        try {
            if (id == null || id.isEmpty()) {
                waterOverdueBills = dao.getOverdueElectricityBills(session.getAttribute("REGION").toString());
            } else {
                waterOverdueBills = dao.getOverdueElectricityBillsByAN(session.getAttribute("REGION").toString(),id);
            }

            if (waterOverdueBills.isEmpty()) {
                req.setAttribute("message", "No data found");
            } else {
                System.out.println("Water Overdue Payments: " + waterOverdueBills);
                req.setAttribute("waterOverdueBills", waterOverdueBills);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/water/regionalAdmin/payments-due.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
