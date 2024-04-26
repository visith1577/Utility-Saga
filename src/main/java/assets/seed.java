package assets;

import DAO.dao.ElectricityAdminDAO;
import DAO.dao.ElectricityRegionalAdminDAO;
import DAO.dao.WaterAdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricityAdminModel;
import model.UserRAdmin;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/test-field/initialize")
public class seed extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String id = "Admin";
//        String fname = "CEB";
//        String lname = "Admin";
//        String uname = "CEB Admin";
//        String pwd = "rootAdmin";
//        String tel = "1987";
//        String email = "dpgm@ceb.lk";
//        String address = "Ceylon Electricity Board Head Quarters, 3rd Floor, No. 50, Sir Chittampalam A. Gardiner Mw, Colombo 2";
//        String homePhone = "011 244 0316";
//        String region = "headOffice";

//        String id = "Regional_Colombo";
//        String fname = "CEB";
//        String lname = "Regional";
//        String uname = "WB Admin Colombo";
//        String pwd = "rootAdmin";
//        String tel = "1987";
//        String email = "dgmp@ceb.lk";
//        String address = "23 Claessen Pl ";
//        String homePhone = "0114 498 498";
//        String region = "Colombo";
//        String empid = "WB001";
////
//        ElectricityAdminModel user = new ElectricityAdminModel();
//        user.setId(id);
//        user.setRegion(region);
//        user.setContactNumber(tel);
//        user.setEmail(email);
//        user.setUtilityType(ElectricityAdminModel.UtilityType.valueOf("CEB"));
//        user.setEmpId(empid);
//        user.setUsername(uname);
//        user.setFirstname(fname);
//        user.setLastname(lname);
//        user.setRole(ElectricityAdminModel.Role.valueOf("REGIONAL"));
//        user.setMobile(homePhone);
//        String bcryptHashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
//        user.setPassword(bcryptHashedPwd);
//
//
//        WaterAdminDAO dao = new WaterAdminDAO();
//
//        try {
//            dao.addWaterAdmin(user);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
////        ReportGenerator report = new ReportGenerator();
////        String report_text = report.dailyReport("23456444", 200, 180, 10_000);
////
////        resp.setContentType("text/html");
////
////        // Write the report as response
////        resp.getWriter().println(report_text);
//
////        SendEmail sendEmail = new SendEmail(
////                "2021cs105@stu.ucsc.cmb.ac.lk",
////                new String[]{"visithkumarapperuma@gmail.com"},
////                "Test Email",
////                "This is a test email"
////        );
////        // Send the email
////        sendEmail.sendEmail("<h1>Test Email --> UtilitySaga</h1>");
////
////        // Send a response to the client
////        resp.getWriter().write("Email sent successfully");
    }
}
