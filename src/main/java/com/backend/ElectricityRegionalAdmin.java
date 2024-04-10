package com.backend;

import DAO.dao.ElectricityComplaintDao;
import DAO.dao.SuperAdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ComplaintModel;
import model.SuperAdmineditAdmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        "/r-admin"
)

public class ElectricityRegionalAdmin extends HttpServlet {
    private ElectricityRegionalAdmin admin;

    @Override
    public void init() throws ServletException {
        super.init();
        admin = new ElectricityRegionalAdmin();
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getServletPath();


//        try{
//            switch (action){
//                case "getComplaints":
//            }
//        }
    }
}