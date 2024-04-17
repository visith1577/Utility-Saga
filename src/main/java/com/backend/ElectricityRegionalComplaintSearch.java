package com.backend;


import java.io.*;
import java.util.List;

import DAO.dao.ElectricityComplaintDao;
import model.ComplaintModel;;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;


@WebServlet("/electricity/complaint/search")
public class ElectricityRegionalComplaintSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");

        ElectricityComplaintDao dao = new ElectricityComplaintDao();
        List<ComplaintModel> complaints = null;
        try {
            complaints = dao.getComplaintsByComplaintID(nic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(complaints);

        out.print(json);
        out.flush();
    }
}
