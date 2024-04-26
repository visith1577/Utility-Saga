package com.backend;

import DAO.dao.ElectricityManualPaymentDao;
import DAO.dao.WaterManualPaymentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ManualPaymentsModel;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/watermanualpayment")
    public class WaterRegionalManualPayment extends HttpServlet{
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String account_number = req.getParameter("account_number");
         String nic=req.getParameter("nic");
         String amount=req.getParameter("amount");
         String dateStr=req.getParameter("date");
         LocalDate date = LocalDate.parse(dateStr);

         ManualPaymentsModel manualpayment = new ManualPaymentsModel();
         manualpayment.setAccount_number(account_number);
         manualpayment.setNic(nic);
         manualpayment.setAmount(amount);
         manualpayment.setDate(date.atStartOfDay());

         WaterManualPaymentDao dao=new WaterManualPaymentDao();

         try{
             dao.insertintomanualpayment(manualpayment);
             resp.sendRedirect(req.getHeader("referer"));

         }catch (Exception e){
             req.setAttribute("errorMessage", e.getMessage());
             req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
         }

     }
}