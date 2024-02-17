package com.backend;

import DAO.dao.SuperAdmineditAdminDAO;
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
    private SuperAdmineditAdminDAO superAdmineditAdminDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        superAdmineditAdminDAO = new SuperAdmineditAdminDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            try {
                SuperAdmineditAdmin admin = getAdminFromRequest(request, action);

                switch (action) {
                    case "createElecAdmin":
                        superAdmineditAdminDAO.createElecAdmin(admin);
                        request.setAttribute("message", "Electricity admin created successfully!");
                        break;
                    case "createWaterAdmin":
                        superAdmineditAdminDAO.createWaterAdmin(admin);
                        request.setAttribute("message", "Water admin created successfully!");
                        break;
                    case "createSolarComp":
                        superAdmineditAdminDAO.createSolarComp(admin);
                        request.setAttribute("message", "Solar company created successfully!");
                        break;
                    case "editElecAdmin":
                        superAdmineditAdminDAO.updateElecAdmin(admin);
                        request.setAttribute("message", "Electricity admin details updated successfully!");
                        break;
                    case "editWaterAdmin":
                        superAdmineditAdminDAO.updateWaterAdmin(admin);
                        request.setAttribute("message", "Water admin details updated successfully!");
                        break;
                    case "editSolarComp":
                        superAdmineditAdminDAO.updateSolarComp(admin);
                        request.setAttribute("message", "Solar company details updated successfully!");
                        break;
                    case "deleteElecAdmin":
                        superAdmineditAdminDAO.deleteElecAdmin(admin.getElecId());
                        request.setAttribute("message", "Electricity admin deleted successfully!");
                        break;
                    case "deleteWaterAdmin":
                        superAdmineditAdminDAO.deleteWaterAdmin(admin.getWaterId());
                        request.setAttribute("message", "Water admin deleted successfully!");
                        break;
                    case "deleteSolarComp":
                        superAdmineditAdminDAO.deleteSolarComp(admin.getSolarId());
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
    }


}
