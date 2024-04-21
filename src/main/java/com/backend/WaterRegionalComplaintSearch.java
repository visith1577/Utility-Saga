package com.backend;


import java.io.*;
import java.util.List;

import DAO.dao.WaterComplaintsDao;
import model.ComplaintModel;;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;


@WebServlet("/water/complaint/search")
public class WaterRegionalComplaintSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");
        HttpSession session = request.getSession();

        WaterComplaintsDao dao = new WaterComplaintsDao();
        List<ComplaintModel> complaints = null;
        try {
            complaints = dao.getComplaintsByComplaintID(session.getAttribute("REGION").toString(),nic);
            System.out.println(complaints);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(complaints);

        out.print(json);
        out.flush();
    }
}
