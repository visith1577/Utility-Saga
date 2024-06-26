package com.backend;

import DAO.dao.ElectricityAdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricityAdminModel;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(
        "/elecAdmin"
)
public class ElectricityAdmin extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region= req.getParameter("region").toUpperCase();
        String contact= req.getParameter("contact");
        String email= req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("re-password");
        String utility = req.getParameter("utility");
        String empid = req.getParameter("empid");
        String uname = req.getParameter("uname");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String role = req.getParameter("role");
        String mobile = req.getParameter("mobile");

        if(!password.equals(repassword)){
            req.setAttribute("errorMessage", "Password not matching");
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
            return;
        }


        ElectricityAdminModel admin = new ElectricityAdminModel();

        admin.setRegion(region);
        admin.setContactNumber(contact);
        admin.setEmail(email);
        admin.setUtilityType(ElectricityAdminModel.UtilityType.valueOf(utility));
        admin.setEmpId(empid);
        admin.setUsername(uname);
        admin.setFirstname(fname);
        admin.setLastname(lname);
        admin.setRole(ElectricityAdminModel.Role.valueOf(role));
        admin.setMobile(mobile);
        String bcryptHashedPwd = BCrypt.hashpw(password, BCrypt.gensalt());
        admin.setPassword(bcryptHashedPwd);

        ElectricityAdminDAO dao = new ElectricityAdminDAO();

        try {
            dao.addElectricityAdmin(admin);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}
