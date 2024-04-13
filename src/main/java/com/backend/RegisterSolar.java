package com.backend;


import java.io.*;

import DAO.dao.RegisterSolarDAO;
import DAO.impl.SolarCompanyImpl;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.SolarCompanyModel;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/registersolar")
public class RegisterSolar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cname= req.getParameter("cname").trim();
        String bnum= req.getParameter("bnum").trim();
        String uname= req.getParameter("uname").trim();
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

        if(Objects.equals(pwd,repwd)){
            String bcryptHashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            company.setPwd(bcryptHashedPwd);
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
