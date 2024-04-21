package com.backend;


import java.io.*;
import java.util.List;

import DAO.dao.ElectricityConnectionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;
import model.ConnectionModel;


@WebServlet("/electricity/connection/search")
public class ElectricityRegionalConnectionSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");
        System.out.println("nic in /electricity/connection/search: "+ nic);
        HttpSession session = request.getSession();

        ElectricityConnectionDao dao = new ElectricityConnectionDao();
        List<ConnectionModel> connections = null;
        try {
            connections = dao.getConnectionRegionalAdminByNIC(session.getAttribute("REGION").toString(),nic);
            System.out.println("getConnectionRegionalAdminByNIC called");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(connections);

        out.print(json);
        out.flush();
    }
}
