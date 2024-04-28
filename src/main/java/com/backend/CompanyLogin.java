package com.backend;

import java.io.*;

import DAO.dao.CompanyDetailsDAO;
import DAO.dao.RegisterSolarDAO;
import DAO.impl.CompanyDetails;
import DAO.impl.SolarCompanyImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CompanyModel;
import model.SolarCompanyModel;
import org.mindrot.jbcrypt.BCrypt;
import utils.Constant;
import utils.PasswordHashingUtility;

import static utils.Constant.SESSION_TIMEOUT_IN_SECONDS;
import static utils.Constant.SOLAR_AUTHENTICATION_KEY_FLAG;

@WebServlet("/company-login")
public class CompanyLogin extends HttpServlet {
    private static final long serialVersionUID = 2L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        companyLogin(req, resp);
    }

    private void companyLogin(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName").trim();
        char[] password = req.getParameter("password").toCharArray();
        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("active_account", userName);
        req.removeAttribute("errorMessage");
        try {
            SolarCompanyImpl solarCompany = new RegisterSolarDAO();

            SolarCompanyModel companyModel = solarCompany.getRegisteredCompanyByUserName(userName);
            if (companyModel == null || !PasswordHashingUtility.verifyHash(new String(password),companyModel.getPwd())) {
                req.setAttribute("ID", userName);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("errorMessage", "Incorrect User Name or Password");
                RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.LOGIN_SOLARLOGIN_JSP);
                dispatcher.forward(req, resp);
            } else if(SolarCompanyModel.ApprovalStatus.APPROVED != companyModel.getApprovalStatus()){
                req.setAttribute("ID", userName);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("errorMessage", "Your request approval is Pending or Rejected.! Please contact Administration.");
                RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.LOGIN_SOLARLOGIN_JSP);
                dispatcher.forward(req, resp);
            }else {
                session = req.getSession(true);
                session.setAttribute( SOLAR_AUTHENTICATION_KEY_FLAG, true );
                session.setAttribute("supplierId", companyModel.getId());
                session.setAttribute("USERNAME", companyModel.getEmail());
                session.setAttribute("companyName", companyModel.getCompanyName());

                session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                cookie.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                resp.addCookie(cookie);

                RequestDispatcher rd = req.getRequestDispatcher( Constant.SOLAR_STORE_JSP);
                rd.forward(req, resp);
//                resp.sendRedirect(req.getContextPath() + "/item/supplierItems?supplierId="+companyModel.getId());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
