package com.backend;

import DAO.dao.WaterAdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricityAdminModel;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(
        "/waterAdmin"
)
public class WaterAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("wregion");
        String contact = req.getParameter("wcontact");
        String email = req.getParameter("wemail");
        String password = req.getParameter("wpassword");
        String empid = req.getParameter("wempid");
        String uname = req.getParameter("wuname");
        String fname = req.getParameter("wfname");
        String lname = req.getParameter("wlname");
        String role = req.getParameter("wrole");
        String mobile = req.getParameter("wmobile");

        ElectricityAdminModel admin = new ElectricityAdminModel();

        admin.setRegion(region);
        admin.setContactNumber(contact);
        admin.setEmail(email);
        admin.setEmpId(empid);
        admin.setUname(uname);
        admin.setFirstname(fname);
        admin.setLastname(lname);
        admin.setRole(ElectricityAdminModel.Role.valueOf(role));
        admin.setMobile(mobile);
        String bcryptHashedPwd = BCrypt.hashpw(password, BCrypt.gensalt());
        admin.setPassword(bcryptHashedPwd);

        WaterAdminDAO dao = new WaterAdminDAO();

        try {
            dao.addWaterAdmin(admin);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/public/HTML/pages/error.jsp").forward(req, resp);
        }
    }
}
