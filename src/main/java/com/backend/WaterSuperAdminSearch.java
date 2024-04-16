package com.backend;


import java.io.*;
import java.util.List;

import DAO.dao.WaterRegionalAdminDAO;
import model.ElectricityAdminModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;


@WebServlet("/superadmin/water/search")
public class WaterSuperAdminSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");

        WaterRegionalAdminDAO dao = new WaterRegionalAdminDAO();
        List<ElectricityAdminModel> admins = null;
        try {
            admins = dao.getAdminsByNIC(nic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(admins);

        out.print(json);
        out.flush();
    }
}
