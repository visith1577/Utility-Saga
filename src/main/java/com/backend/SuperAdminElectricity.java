package com.backend;


import DAO.dao.ElectricityAdminDAO;
import DAO.dao.WaterAdminDAO;
import DAO.impl.ElectricityAdminImpl;
import DAO.impl.WaterAdminImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricityAdminModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/super-admin/main-electricity-accounts")
public class SuperAdminElectricity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ElectricityAdminModel> electricityMainAdmins = null;
        List<ElectricityAdminModel> waterMainAdmins = null;

        String id = req.getParameter("id");
        String id2 = req.getParameter("id2");

        System.out.println("ID: " + id);
        System.out.println("ID2: " + id2);

        ElectricityAdminImpl dao = new ElectricityAdminDAO();
        WaterAdminImpl dao2 = new WaterAdminDAO();

        try {
            if ((id == null || id.isEmpty()) && (id2 == null || id2.isEmpty())) {
                electricityMainAdmins = dao.getElectricityAdmins(ElectricityAdminModel.Role.MAIN);
                System.out.println("Executeg get all electricity admins");
                waterMainAdmins = dao2.getWaterAdmins(ElectricityAdminModel.Role.MAIN);
                System.out.println("Executeg get all water admins");
            } else if(id2 != null && !id2.isEmpty()) {
                electricityMainAdmins = dao.getElectricityAdmins(ElectricityAdminModel.Role.MAIN);
                System.out.println("Executeg get all electricity admins");
                waterMainAdmins = dao2.getAdminsByNIC(id2);
                System.out.println("Executeg get particular water admins");
            }
            else if(id != null && !id.isEmpty()) {
                waterMainAdmins = dao2.getWaterAdmins(ElectricityAdminModel.Role.MAIN);
                System.out.println("Executeg get all water admins");
                electricityMainAdmins = dao.getAdminsByNIC(id);
                System.out.println("Executeg get particular electricity admins");
            }

            System.out.println(electricityMainAdmins);
            System.out.println(waterMainAdmins);

            if ((electricityMainAdmins.isEmpty()) && (waterMainAdmins.isEmpty())) {
                req.setAttribute("message1", "No data found");
                req.setAttribute("message2", "No data found");
            } else if(electricityMainAdmins.isEmpty() && !(waterMainAdmins.isEmpty())) {
                req.setAttribute("message1", "No data found");
                System.out.println("2Electricity Admins: " + electricityMainAdmins);
                System.out.println("2Water Admins: " + waterMainAdmins);
                req.setAttribute("waterMainAdmins", waterMainAdmins);
            }else if(waterMainAdmins.isEmpty() && !(electricityMainAdmins.isEmpty())){
                req.setAttribute("electricityMainAdmins", electricityMainAdmins);
                System.out.println("3Electricity Admins: " + electricityMainAdmins);
                System.out.println("3Water Admins: " + waterMainAdmins);
                req.setAttribute("message2", "No data found");
            }else{
                req.setAttribute("electricityMainAdmins", electricityMainAdmins);
                System.out.println("4Electricity Admins: " + electricityMainAdmins);
                System.out.println("4Water Admins: " + waterMainAdmins);
                req.setAttribute("waterMainAdmins", waterMainAdmins);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/superadmin/Superadmin-electricity-water.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
