package com.backend;


import java.io.*;

import DAO.dao.RegisterSolarDAO;
import DAO.impl.SolarCompanyImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SolarCompanyModel;
import org.mindrot.jbcrypt.BCrypt;
import utils.PasswordHashingUtility;


import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/registersolar")
public class RegisterSolar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cname= req.getParameter("Cname").trim();
        String bnum= req.getParameter("Bnum").trim();
        String uname= req.getParameter("Uname").trim();
        String nic= req.getParameter("nic").trim();
        String pwd= req.getParameter("pwd").trim();
        String repwd= req.getParameter("Re").trim();
        String phone= req.getParameter("phone").trim();
        String cphone= req.getParameter("company-phone").trim();
        String email= req.getParameter("email").trim();
        String district= req.getParameter("district").trim();
        String address= req.getParameter("address").trim();
        String comment= req.getParameter("comment").trim();

        SolarCompanyModel company= new SolarCompanyModel();
        company.setCompanyName(cname);
        company.setBNum(bnum);
        company.setUsername(uname);
        company.setOwnerNIC(nic);
        company.setMobile(phone);
        company.setLandNo(cphone);
        company.setEmail(email);
        company.setDistrict(district);
        company.setAddress(address);
        company.setRemarks(comment);

        SolarCompanyImpl solarCompany = new RegisterSolarDAO();

        SolarCompanyModel dbCompanyModel = solarCompany.getRegisteredCompanyByUserName(company.getEmail());
        if(dbCompanyModel != null){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            req.setAttribute("errorMessage", "Company email already exist. Please enter a valid email.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/login/solarLogin.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        if(Objects.equals(pwd,repwd)){
            company.setPwd(PasswordHashingUtility.hash(pwd));
        }

        SolarCompanyImpl dao = new RegisterSolarDAO();

        try{
            dao.registerCompany(company);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/public/HTML/login/solarLogin.jsp");

    }
}
