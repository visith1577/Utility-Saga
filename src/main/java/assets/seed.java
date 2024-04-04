package assets;

import DAO.dao.ElectricityRegionalAdminDAO;
import DAO.dao.UserDetailsDao;
import DAO.impl.UserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserModel;
import model.UserRAdmin;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

@WebServlet("/initialize")
public class seed extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = "Admin";
        String fname = "CEB";
        String lname = "Admin";
        String uname = "CEB Admin";
        String pwd = "rootAdmin";
        String tel = "1987";
        String email = "dpgm@ceb.lk";
        String address = "Ceylon Electricity Board Head Quarters, 3rd Floor, No. 50, Sir Chittampalam A. Gardiner Mw, Colombo 2";
        String homePhone = "011 244 0316";
        String region = "headOffice";

//        String id = "Regional_Colombo";
//        String fname = "CEB";
//        String lname = "Regional";
//        String uname = "CEB Admin Colombo";
//        String pwd = "rootAdmin";
//        String tel = "1987";
//        String email = "dgmp@ceb.lk";
//        String address = "23 Claessen Pl ";
//        String homePhone = "0114 498 498";
//        String region = "Colombo";

        UserRAdmin user = new UserRAdmin();
        user.setId(id);
        user.setUsername(uname);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setRegion(region);
        user.setTel(tel);
        user.setHome(homePhone);
        user.setEmail(email);
        user.setAddress(address);
        user.setRegion(region);
//        user.setRole(UserRAdmin.Role.MAIN);
        user.setRole(UserRAdmin.Role.REGIONAL);

        String bcryptHashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
        user.setPassword(bcryptHashedPwd);

        ElectricityRegionalAdminDAO dao = new ElectricityRegionalAdminDAO();

        try {
            dao.registerUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
