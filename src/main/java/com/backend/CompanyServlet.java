package com.backend;


import DAO.dao.AddressDetailsDAO;
import DAO.dao.CompanyDetailsDAO;
import DAO.impl.AddressDetails;
import DAO.impl.CompanyDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressModel;
import model.CompanyModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    public CompanyServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        if (requestURI.startsWith( req.getContextPath() + "/company/all")) {
            System.out.println("All users");
            // Handle request for /user/all
        } else if (requestURI.equals(req.getContextPath() + "/company/1")) {
            System.out.println("User with id 1");
            // Handle request for /user/1
        } else {
            System.out.println("Error");
            // Error
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String companyContact = req.getParameter("company_contact");

        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        int zipCode = Integer.parseInt(req.getParameter("zipCode"));
        String addressContact = req.getParameter("address_contact");

        AddressModel address = new AddressModel();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZipCode(zipCode);
        address.setContact(addressContact);

        CompanyModel company = new CompanyModel();
        company.setName(name);
        company.setEmail(email);
        company.setContact(companyContact);


        AddressDetails addressDetails = new AddressDetailsDAO();
        CompanyDetails companyDetails = new CompanyDetailsDAO();
        try {
            Integer addressId = addressDetails.addAddress(address);

            company.setAddressId(addressId);
            Integer companyId =  companyDetails.addCompany(company);
            System.out.println("Company Id: " + companyId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
