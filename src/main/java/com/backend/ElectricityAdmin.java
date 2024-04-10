package com.backend;

import DAO.dao.ElectricityAdminDAO;
import DAO.dao.ElectricityComplaintDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ComplaintModel;
import model.ElectricityAdminModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        "/elecAdmin"
)
public class ElectricityAdmin extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region= req.getParameter("region");
        String contact= req.getParameter("contact");
        String email= req.getParameter("email");
        String password = req.getParameter("password");
        String utility = req.getParameter("utility");

        ElectricityAdminModel admin = new ElectricityAdminModel();

        admin.setRegion(region);
        admin.setContactNumber(contact);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setUtilityType(ElectricityAdminModel.UtilityType.valueOf(utility));

        ElectricityAdminDAO dao = new ElectricityAdminDAO();

        try {
            dao.addElectricityAdmin(admin);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}