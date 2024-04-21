package com.backend;

import DAO.dao.ElecWaterAccountsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElecWaterAccountsModel;

import java.io.IOException;

@WebServlet("/water/regional-admin/create-account")
public class WaterRegionalAddAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNo = req.getParameter("accountno");
        String requestid = req.getParameter("requestid");
        String region = req.getParameter("region").toUpperCase();
        String subregion = req.getParameter("subregion").toUpperCase();
        String nic = req.getParameter("nicc");

        ElecWaterAccountsModel account = new ElecWaterAccountsModel();

        account.setAccountNumber(accountNo);
        account.setNic(nic);
        account.setRequestId((requestid));
        account.setRegion(region);
        account.setSubRegion(subregion);

        ElecWaterAccountsDAO dao = new ElecWaterAccountsDAO();

        try {
            dao.saveWaterAccount(account);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}