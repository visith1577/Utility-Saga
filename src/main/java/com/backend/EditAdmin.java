package com.backend;

import DAO.dao.SuperAdminDAO;
import model.SuperAdmineditAdmin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/editAdmin")
public class EditAdmin extends HttpServlet {
    private SuperAdminDAO superAdminDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        superAdminDAO = new SuperAdminDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            try {
                SuperAdmineditAdmin admin = getAdminFromRequest(request, action);

                switch (action) {
                    case "createElecAdmin":
                        superAdminDAO.createElecAdmin(admin);
                        request.setAttribute("message", "Electricity admin created successfully!");
                        break;
                    case "createWaterAdmin":
                        superAdminDAO.createWaterAdmin(admin);
                        request.setAttribute("message", "Water admin created successfully!");
                        break;
                    case "createSolarComp":
                        superAdminDAO.createSolarComp(admin);
                        request.setAttribute("message", "Solar company created successfully!");
                        break;
                    case "editElecAdmin":
                        superAdminDAO.updateElecAdmin(admin);
                        request.setAttribute("message", "Electricity admin details updated successfully!");
                        break;
                    case "editWaterAdmin":
                        superAdminDAO.updateWaterAdmin(admin);
                        request.setAttribute("message", "Water admin details updated successfully!");
                        break;
                    case "editSolarComp":
                        superAdminDAO.updateSolarComp(admin);
                        request.setAttribute("message", "Solar company details updated successfully!");
                        break;
                    case "deleteElecAdmin":
                        superAdminDAO.deleteElecAdmin(admin.getElecId());
                        request.setAttribute("message", "Electricity admin deleted successfully!");
                        break;
                    case "deleteWaterAdmin":
                        superAdminDAO.deleteWaterAdmin(admin.getWaterId());
                        request.setAttribute("message", "Water admin deleted successfully!");
                        break;
                    case "deleteSolarComp":
                        superAdminDAO.deleteSolarComp(admin.getSolarId());
                        request.setAttribute("message", "Solar company deleted successfully!");
                        break;
                    default:
                        // Handle invalid action
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error performing admin operation!");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Superadmin-editadmin.html");
        dispatcher.forward(request, response);
    }

    private SuperAdmineditAdmin getAdminFromRequest(HttpServletRequest request, String action) {
        // Implement data extraction and object creation based on your form fields and action
        // Consider input validation and sanitization here
        return null;
    }


}
