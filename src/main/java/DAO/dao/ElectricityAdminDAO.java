package DAO.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.impl.ElectricityAdminImpl;
import utils.Connectdb;
import model.ElectricityAdminModel;

public class ElectricityAdminDAO implements ElectricityAdminImpl {
    @Override
    public int addElectricityAdmin(ElectricityAdminModel admin) throws Exception {
        Connection connection = Connectdb.getConnection();
        String query = "INSERT INTO electricity_admin (region, contact_number, email, password, utilityType,empid,uname, firstname, lastname, role, mobile) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getRegion());
        statement.setString(2, admin.getContactNumber());
        statement.setString(3, admin.getEmail());
        statement.setString(4, admin.getPassword());
        statement.setString(5, admin.getUtilityType().toString().toUpperCase());
        statement.setString(6, admin.getEmpId());
        statement.setString(7, admin.getUname());
        statement.setString(8, admin.getFirstname());
        statement.setString(9, admin.getLastname());
        statement.setString(10, admin.getRole().toString().toUpperCase());
        statement.setString(11, admin.getMobile());


        int rowsAffected = statement.executeUpdate();
        connection.close();
        return rowsAffected;
    }

    @Override
    public List<ElectricityAdminModel> getElectricityAdmins(ElectricityAdminModel.Role role) throws Exception {
        List<ElectricityAdminModel> admins = new ArrayList<>();
        Connection connection = Connectdb.getConnection();;
        String query = "SELECT * FROM electricity_admin WHERE role = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, role.toString().toUpperCase());

        ResultSet rs = statement.executeQuery();


        while (rs.next()) {
            ElectricityAdminModel admin = new ElectricityAdminModel();
            admin.setId(rs.getString("id"));
            admin.setRegion(rs.getString("region"));
            admin.setContactNumber(rs.getString("contact_number"));
            admin.setEmail(rs.getString("email"));
            admin.setPassword(rs.getString("password"));
            admin.setUtilityType(ElectricityAdminModel.UtilityType.valueOf(rs.getString("utilityType")));
            admin.setEmpId(rs.getString("empid"));
            admin.setUname(rs.getString("uname"));
            admin.setFirstname(rs.getString("firstname"));
            admin.setLastname(rs.getString("lastname"));
            admin.setRole(ElectricityAdminModel.Role.valueOf(rs.getString("role")));
            admin.setMobile(rs.getString("mobile"));

            admins.add(admin);
        }

        rs.close();
        statement.close();
        connection.close();
        return admins;
    }

}
