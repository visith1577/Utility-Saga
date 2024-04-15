package DAO.dao;

import DAO.impl.WaterRegional;
import model.*;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaterRegionalAdminDAO implements WaterRegional{

    @Override
    public List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException {
        List<ElectricityAdminModel> admins = new ArrayList<>();
        Connection con = Connectdb.getConnection();

        String sql = "SELECT region, contact_number, email, password, empid, uname, firstname, lastname, role, mobile FROM water_admin WHERE empid = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nic);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ElectricityAdminModel admin = new ElectricityAdminModel();
            admin.setRegion(rs.getString("region"));
            admin.setContactNumber(rs.getString("contact_number"));
            admin.setEmail(rs.getString("email"));
            admin.setPassword(rs.getString("password"));
            admin.setEmpId(rs.getString("empid"));
            admin.setUname(rs.getString("uname"));
            admin.setFirstname(rs.getString("firstname"));
            admin.setLastname(rs.getString("lastname"));
            admin.setRole(ElectricityAdminModel.Role.valueOf(rs.getString("role")));
            admin.setMobile(rs.getString("mobile"));

            admins.add(admin);
        }

        rs.close();
        stmt.close();
        con.close();
        return admins;
    }
}
