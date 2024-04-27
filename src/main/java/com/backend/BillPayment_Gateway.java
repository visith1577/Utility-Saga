package com.backend;

import DAO.dao.Payment_hashGen;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BillPayment_Gateway extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String merchantId = "1226570";
            String orderId  = req.getParameter("orderId");
            String amount = req.getParameter("amount");
            String currency = req.getParameter("currency");
            String merchantSecret = "MjUyMDg3NjQ5NzI5NTIwMzM4ODgzMjcwNDcyMTE1MTU1OTk5MzE0MA==";
            System.out.println(merchantSecret);

            if (orderId == null || amount == null || currency == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Payment_hashGen payment_hashGen = new Payment_hashGen(merchantId, orderId, Double.parseDouble(amount), currency, merchantSecret);
            String hash = payment_hashGen.createHash();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(hash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
