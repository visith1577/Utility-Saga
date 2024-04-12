package DAO.dao;

import model.ElectricityAdminModel;
import DAO.impl.WaterAdminImpl;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WaterAdminDAO implements WaterAdminImpl {
    @Override
    public int addWaterAdmin(ElectricityAdminModel admin) throws Exception {
        Connection connection = Connectdb.getConnection();
        String sql = "INSERT INTO water_admin (region, contact_number, email, password, empid,uname, firstname, lastname, role, mobile) VALUES ( ?, ?, ?, ?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, admin.getRegion());
        statement.setString(2, admin.getContactNumber());
        statement.setString(3, admin.getEmail());
        statement.setString(4, admin.getPassword());
        statement.setString(5, admin.getEmpId());
        statement.setString(6, admin.getUname());
        statement.setString(7, admin.getFirstname());
        statement.setString(8, admin.getLastname());
        statement.setString(9, admin.getRole().toString().toUpperCase());
        statement.setString(10, admin.getMobile());


        int rowsAffected = statement.executeUpdate();
        connection.close();
        return rowsAffected;
    }

    @Override
    public List<ElectricityAdminModel> getWaterAdmins() throws Exception {
        List <ElectricityAdminModel> admins = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String query = "SELECT * FROM water_admin";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            ElectricityAdminModel adminw = new ElectricityAdminModel();
            adminw.setId(rs.getString("id"));
            adminw.setRegion(rs.getString("region"));
            adminw.setContactNumber(rs.getString("contact_number"));
            adminw.setEmail(rs.getString("email"));
            adminw.setPassword(rs.getString("password"));
            adminw.setEmpId(rs.getString("empid"));
            adminw.setUname(rs.getString("uname"));
            adminw.setFirstname(rs.getString("firstname"));
            adminw.setLastname(rs.getString("lastname"));
            adminw.setRole(ElectricityAdminModel.Role.valueOf(rs.getString("role")));
            adminw.setMobile(rs.getString("mobile"));

            admins.add(adminw);
        }

        rs.close();
        statement.close();
        connection.close();
        return admins;
    }
}
