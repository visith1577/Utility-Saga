package com.backend;


import DAO.dao.WaterAdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ElectricityAdminModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/water/main-admin/update-details")
public class WaterMainAdminDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request URL: " + req.getRequestURL());
        String empid = req.getParameter("empid");
        System.out.println("Empid:"+ empid);
        String uname = req.getParameter("uname");
        System.out.println("Empid:"+ uname);
        String firstname = req.getParameter("firstname");
        System.out.println("Empid:"+ firstname);

        String lastname = req.getParameter("lastname");
        System.out.println("Empid:"+ lastname);
        String mobile = req.getParameter("mobile");
        System.out.println("Empid:"+ mobile);

        ElectricityAdminModel admin = new ElectricityAdminModel();

        admin.setEmpId(empid);
        admin.setUsername(uname);
        admin.setFirstname(firstname);
        admin.setLastname(lastname);
        admin.setMobile(mobile);
        HttpSession session = req.getSession();
        admin.setRegion(session.getAttribute("REGION").toString());
        session.setAttribute("USERNAME", admin.getUsername());
        session.setAttribute("EMPID", admin.getEmpId());
        session.setAttribute("FNAME", admin.getFirstname());
        session.setAttribute("LNAME", admin.getLastname());
        session.setAttribute("MOBILE", admin.getMobile());



        WaterAdminDAO dao = new WaterAdminDAO();

        try {
            try {
                dao.updateAdminDetails(admin);
                System.out.println("updateadmin called");
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
