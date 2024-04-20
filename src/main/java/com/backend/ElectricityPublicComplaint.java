package com.backend;

import DAO.dao.ElectricityComplaintDao;
import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ComplaintModel;
import model.UserAccountsModel;
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        "/user/electricity-public-complaint"
)
public class ElectricityPublicComplaint extends HttpServlet {
    private static final long serialVersionUID = 21L;
    List<String> complaint_types = List.of("Billing issues", "Connection & Disconnection issues", "Power Outages",
            "Voltage & frequency problems", "Smart meter problems", "Quality Problem", "Others");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accNum = req.getParameter("AccountNum");
        String complainCat = req.getParameter("Category");
        String complainType = req.getParameter("complaint_type");
        String nic = req.getParameter("CusNIC");
        String email = req.getParameter("Email");
        String telNum = req.getParameter("Telnum");
        String txtArea = req.getParameter("txtArea");

        ComplaintModel eComplaint = new ComplaintModel();
        eComplaint.setComplaintCategory(complainCat);
        if (complaint_types.contains(complainType)) {
            eComplaint.setComplaintType(complainType);
        } else {
            eComplaint.setComplaintType("Others");
        }
        eComplaint.setAccountNumber(accNum);
        eComplaint.setEmail(email);
        eComplaint.setNic(nic);
        eComplaint.setPhoneNumber(telNum);
        eComplaint.setComplaintDescription(txtArea);

        ElectricityComplaintDao dao = new ElectricityComplaintDao();

        try {
            try {
                dao.saveComplaint(eComplaint);
                resp.sendRedirect(req.getHeader("referer"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO.impl.UserAccounts dao = new UserAccountsDao();
        UserDetails user = new UserDetailsDao();
        try {
            List<UserAccountsModel> account_elist = dao.getUserAccounts(
                    (String) session.getAttribute("NIC"), "ELECTRICITY"
            );
            UserModel model = user.getUserFullNameByNic((String) session.getAttribute("NIC"));


            req.setAttribute("electricity_account_list", account_elist);
            req.setAttribute("fullName", model.getFullName());
            req.setAttribute("ADDRESS", model.getAddress());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/electricity/electricity-publiccomplaint.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}