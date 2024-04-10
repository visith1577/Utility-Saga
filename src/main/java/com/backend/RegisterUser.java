package com.backend;

import java.io.*;

import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;


@WebServlet("/register")
public class RegisterUser extends HttpServlet{
    private static final long serialVersionUID = 1L;
//    private static final Logger logger = LogManager.getLogger(RegisterUser.class); // logger setup

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String nic = req.getParameter("nic").strip();
        String fname = req.getParameter("Fname").strip();
        String lname = req.getParameter("Lname").strip();
        String uname = req.getParameter("Uname").strip();
        String pwd = req.getParameter("Pwd");
        String phone = req.getParameter("phone").strip();
        String rePwd = req.getParameter("Re");
        String email = req.getParameter("email").strip();
        String address = req.getParameter("address");
        String eBill = req.getParameter("eBill").strip();
        String wBill = req.getParameter("wBill").strip();
        String homePhone = req.getParameter("home-phone").strip();
        String region = req.getParameter("region").strip();
        String electricityProvider = req.getParameter("provider").toUpperCase();
        String[] services = req.getParameterValues("service");

        UserModel user = new UserModel();
        user.setNic(nic);
        user.setUsername(uname);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setRegion(region);
        user.setMobile(phone);
        user.setHome(homePhone);
        user.setEmail(email);
        user.setAddress(address);
        user.setServices(new HashSet<>(Arrays.asList(services)));

        UserModel.ProviderInfo providerInfo = UserModel.ProviderInfo.valueOf(electricityProvider);
        user.setProvider(providerInfo);

        if (Objects.equals(pwd, rePwd)) {
            String bcryptHashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
//            String sha256Pwd = CryptoUtils.encryptPassword(bcryptHashedPwd);

            user.setPassword(bcryptHashedPwd);
        }

        System.out.println(user.getNic());

        UserDetails userDao = new UserDetailsDao();

        try {
            userDao.registerUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/public/HTML/login/userSelector.jsp");
    }
}
