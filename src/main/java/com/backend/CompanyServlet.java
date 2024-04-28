package com.backend;


import DAO.dao.AddressDetailsDAO;
import DAO.dao.CompanyDetailsDAO;
import DAO.impl.AddressDetails;
import DAO.impl.CompanyDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddressModel;
import model.CompanyModel;
import utils.CompanyModelDTO;
import utils.CompanyRegisterException;
import utils.PasswordHashingUtility;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    public CompanyServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        if (requestURI.startsWith(req.getContextPath() + "/company/all")) {
            System.out.println("All users");

        } else if (requestURI.equals(req.getContextPath() + "/company/check-username")) {
            String userName = req.getParameter("userName");


        } else {
            System.out.println("Error");
            // Error
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("company_id"));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CompanyModelDTO companyModelDTO = new CompanyModelDTO();
        companyModelDTO.setName(req.getParameter("name"));
        companyModelDTO.setEmail(req.getParameter("email"));
        companyModelDTO.setCompanyContact(req.getParameter("company_contact"));
        companyModelDTO.setUserName(req.getParameter("userName"));
        companyModelDTO.setPassword(req.getParameter("password"));
        companyModelDTO.setStreet(req.getParameter("street"));
        companyModelDTO.setCity(req.getParameter("city"));
        companyModelDTO.setState(req.getParameter("state"));
        companyModelDTO.setCountry(req.getParameter("country"));
        companyModelDTO.setZipCode(Integer.parseInt(req.getParameter("zipCode")));
        companyModelDTO.setAddressContact(req.getParameter("address_contact"));


        CompanyDetails companyDetails = new CompanyDetailsDAO();
        CompanyModel companyModel = companyDetails.selectCompanyByUserName(companyModelDTO.getUserName());
        if (companyModel != null) {
            System.out.println("User Name already exists");
            req.setAttribute("errorMessage", companyModelDTO.getUserName() + " :: already exists");
            req.setAttribute("contextPath", req.getContextPath());

            HttpSession session = req.getSession();
            companyModelDTO.setPassword("");
            session.setAttribute("companyModelDTO", companyModelDTO);

            RequestDispatcher dispatcher = req.getRequestDispatcher( "/public/HTML/solar/error.jsp");
            dispatcher.forward(req, resp);
//            throw new CompanyRegisterException("User Name already exists");
//            RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/public/HTML/login/companyRegister.jsp");
//            req.setAttribute("errorMessage", "Error message here");
        } else {
            AddressModel address = new AddressModel();
            address.setStreet(companyModelDTO.getStreet());
            address.setCity(companyModelDTO.getCity());
            address.setState(companyModelDTO.getState());
            address.setCountry(companyModelDTO.getCountry());
            address.setZipCode(companyModelDTO.getZipCode());
            address.setContact(companyModelDTO.getAddressContact());

            CompanyModel company = new CompanyModel();
            company.setName(company.getName());
            company.setEmail(company.getEmail());
            company.setContact(companyModelDTO.getCompanyContact());
            company.setUserName(companyModelDTO.getUserName());
            company.setPassword(PasswordHashingUtility.hash(companyModelDTO.getPassword()));

            AddressDetails addressDetails = new AddressDetailsDAO();
            try {
                Integer addressId = addressDetails.addAddress(address);

                company.setAddressId(addressId);
                Integer companyId =  companyDetails.addCompany(company);
                System.out.println("Company Id: " + companyId);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/public/HTML/login/solarLogin.jsp");
            requestDispatcher.forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("company_id"));

    }

    @Override
    public void init() throws ServletException {

    }


}
