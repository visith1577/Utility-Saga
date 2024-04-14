package com.backend;

import DAO.dao.UserAccountsDao;
import DAO.dao.UserDetailsDao;
import DAO.dao.WaterComplaintsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ComplaintModel;
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/water-public-complaint")
public class WaterPublicComplaint extends HttpServlet {

    private static final long serialVersionUID = 22L;
    List<String> complaint_types = List.of("Main Leak", "Connection Leak", "No Water",
            "Low Pressure", "Leak Near Meter", "Quality Problem", "Others");


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accNum = req.getParameter("AccountNum");
        String complainCat = req.getParameter("Category");
        String complainType = req.getParameter("complaint_type");
        String nic = req.getParameter("CusNIC");
        String email = req.getParameter("Email");
        String telNum = req.getParameter("Telnum");
        String txtArea = req.getParameter("txtArea");

        ComplaintModel wComplaint = new ComplaintModel();
        switch (complainType) {
            case "MainLeak":
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.MainLeak);
                break;
            case "ConnectionLeak":
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.ConnectionLeak);
                break;
            case "NoWater":
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.NoWater);
                break;
            case "LowPressure":
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.LowPressure);
                break;
            case "LeakNearby":
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.LeakNearby);
                break;
            case "Quality":
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.Quality);
                break;

            default:
                wComplaint.setComplaintTypeWater(ComplaintModel.Complaint_Type_Water.Other);
                break;
        }
        wComplaint.setAccount_number(accNum);
        wComplaint.setEmail(email);
        wComplaint.setNic(nic);
        wComplaint.setPhoneNumber(telNum);
        wComplaint.setComplaint_description(txtArea);


        WaterComplaintsDao dao = new WaterComplaintsDao();

        try {
            try {
                dao.saveComplaint(wComplaint);
                System.out.println(req.getHeader("referer"));
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
            List<String> account_wlist = dao.getUserAccountsWithStatus(
                    (String) session.getAttribute("NIC"), "WATER", "ACTIVE"
            );
            UserModel model = user.getUserFullNameByNic((String) session.getAttribute("NIC"));


            req.setAttribute("water_account_list", account_wlist);
            req.setAttribute("fullName", model.getFullName());
            req.setAttribute("ADDRESS", model.getAddress());


            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/user/water/water-publiccomplaint.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
