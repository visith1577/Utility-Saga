package com.backend;

import DAO.dao.WaterComplaintsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ComplaintModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/wPub-complaint")
public class WaterPublicComplaint extends HttpServlet {

    private static final long serialVersionUID = 22L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accNum = req.getParameter("AccountNum");
        String complainCat = req.getParameter("Category");
        String complainType = req.getParameter("complaint_type");
        String nic = req.getParameter("CusNIC");
        String email = req.getParameter("Email");
        String telNum = req.getParameter("Telnum");
        String txtArea = req.getParameter("txtArea");

        ComplaintModel wComplaint = new ComplaintModel(complaint_number);
        wComplaint.setComplaint_category(complainCat);
        wComplaint.setComplaint_type(complainType);
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
}
