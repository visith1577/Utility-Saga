package com.backend;


import java.io.*;
import java.util.List;

import model.SolarCompanyModel;
import DAO.dao.RegisterSolarDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;


@WebServlet("/solar/search")
public class SolarSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");

        RegisterSolarDAO dao = new RegisterSolarDAO();
        List<SolarCompanyModel> companies = null;
        try {
            companies = dao.getRegisteredCompaniesByNIC(nic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(companies);

        out.print(json);
        out.flush();
    }
}
