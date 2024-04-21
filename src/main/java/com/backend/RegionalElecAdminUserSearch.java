package com.backend;


import java.io.*;
import java.util.List;

import DAO.dao.UserDetailsDao;
import model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;


@WebServlet("/electricity/user/search")
public class RegionalElecAdminUserSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");
        HttpSession session = request.getSession();

        UserDetailsDao dao = new UserDetailsDao();
        List<UserModel> users = null;
        try {
            users = dao.getUserDetailsByNICRegionalAdmin(session.getAttribute("REGION").toString(),nic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(users);

        out.print(json);
        out.flush();
    }
}
