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
import org.eclipse.tags.shaded.org.apache.bcel.classfile.Constant;
import org.mindrot.jbcrypt.BCrypt;
import utils.PasswordHashingUtility;

import static utils.Constant.SESSION_TIMEOUT_IN_SECONDS;

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
                RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/login/solarLogin.jsp");
                dispatcher.forward(req, resp);
            } else if(SolarCompanyModel.ApprovalStatus.APPROVED != companyModel.getApprovalStatus()){
                req.setAttribute("ID", userName);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("errorMessage", "Your request approval is Pending or Rejected.! Please contact Administration.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/login/solarLogin.jsp");
                dispatcher.forward(req, resp);
            }else {
                session = req.getSession(true);
                session.setAttribute("isLoggedIn", true);
                session.setAttribute("supplier_id", companyModel.getId());
                session.setAttribute("USERNAME", companyModel.getEmail());

                req.setAttribute("companyName", companyModel.getCompanyName());
                session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
                cookie.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
                resp.addCookie(cookie);

                RequestDispatcher rd = req.getRequestDispatcher( "/item/supplierItems?supplierId="+companyModel.getId());
                rd.forward(req, resp);
//                resp.sendRedirect(req.getContextPath() + "/item/supplierItems?supplierId="+companyModel.getId());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    private void companyLogin_Old(HttpServletRequest req, HttpServletResponse resp) {
//        String userName = req.getParameter("userName").trim();
//        char[] password = req.getParameter("password").toCharArray();
//        HttpSession session = req.getSession();
//        if (session != null) {
//            session.invalidate();
//        }
//        Cookie cookie = new Cookie("active_account", userName);
//        req.removeAttribute("errorMessage");
//        try {
//            CompanyDetails companyDetails = new CompanyDetailsDAO();
//            System.out.println(new String(password));
//            System.out.println(BCrypt.hashpw(new String(password), BCrypt.gensalt()));
//            System.out.println("$2a$10$dXsxdsb3GL0DSkjz/fq3ueqse49uMWB6556CETpM2BfzmrpvS7hSi");
//
//            CompanyModel companyModel = companyDetails.selectCompanyByUserName(userName);
//            if (companyModel == null || !PasswordHashingUtility.verifyHash(new String(password),companyModel.getPassword())) {
//                req.setAttribute("ID", userName);
//                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                req.setAttribute("errorMessage", "Incorrect User Name or Password");
//                RequestDispatcher dispatcher = req.getRequestDispatcher("/public/HTML/login/solarLogin.jsp");
//                dispatcher.forward(req, resp);
//            } else {
//                session = req.getSession(true);
//                session.setAttribute("isLoggedIn", true);
//                session.setAttribute("ID", userName);
//                session.setAttribute("REGION", userName); // region up
//                session.setAttribute("AREAS", userName);  // list of areas
//                session.setAttribute("USERNAME", companyModel.getUserName());
//                session.setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
//                cookie.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
//                resp.addCookie(cookie);
//                RequestDispatcher rd = req.getRequestDispatcher( "/public/HTML/solar/store.jsp");
//                rd.forward(req, resp);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


}
