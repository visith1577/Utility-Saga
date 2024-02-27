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

@WebServlet(
        "/ePub-complaint"
)
public class ElectricityPublicComplaint extends HttpServlet {
    private static final long serialVersionUID = 21L;

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
        wComplaint.setComplaint_category(complainCat);
        wComplaint.setComplaint_type(complainType);
        wComplaint.setAccount_number(accNum);
        wComplaint.setEmail(email);
        wComplaint.setNic(nic);
        wComplaint.setPhoneNumber(telNum);
        wComplaint.setComplaint_description(txtArea);

        ElectricityComplaintDao dao = new ElectricityComplaintDao();

        try {
            try {
                dao.saveComplaint(wComplaint);
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
