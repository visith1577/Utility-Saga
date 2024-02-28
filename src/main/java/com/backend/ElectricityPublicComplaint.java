package com.backend;

import DAO.dao.ElectricityComplaintDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ComplaintModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        "/ePub-complaint"
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
        eComplaint.setComplaint_category(complainCat);
        if (complaint_types.contains(complainType)) {
            eComplaint.setComplaint_type(complainType);
        } else {
            eComplaint.setComplaint_type("Others");
        }
        eComplaint.setAccount_number(accNum);
        eComplaint.setEmail(email);
        eComplaint.setNic(nic);
        eComplaint.setPhoneNumber(telNum);
        eComplaint.setComplaint_description(txtArea);

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
}
