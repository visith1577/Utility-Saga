package com.backend;

import DAO.dao.UserBillPaymentDAO;
import DAO.dao.UserDetailsDao;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAccountsModel;
import model.UserModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/billpayment")
public class UserBillPayment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        UserBillPaymentDAO billPaymentDAO = new UserBillPaymentDAO();

        try {
            List<UserAccountsModel> accountEList = billPaymentDAO.getUserAccounts((String) session.getAttribute("NIC"), "ELECTRICITY");
            List<UserAccountsModel> accountWList = billPaymentDAO.getUserAccounts((String) session.getAttribute("NIC"), "WATER");

            for (UserAccountsModel i : accountEList) {
                System.out.println(i.getAccount_number());
            }

            req.setAttribute("electricity_account_list", accountEList);


            req.setAttribute("water_account_list", accountWList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/bill_payment.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
