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
        String query = "INSERT INTO electricity_admin (region, contact_number, email, password, utilityType) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getRegion());
        statement.setString(2, admin.getContactNumber());
        statement.setString(3, admin.getEmail());
        statement.setString(4, admin.getPassword());
        statement.setString(5, admin.getUtilityType().toString().toUpperCase());

        int rowsAffected = statement.executeUpdate();
        connection.close();
        return rowsAffected;
    }

    @Override
    public List<ElectricityAdminModel> getElectricityAdmins() throws Exception {
        List<ElectricityAdminModel> admins = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String query = "SELECT * FROM electricity_admin";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet rs = statement.executeQuery();


        while (rs.next()) {
            ElectricityAdminModel admin = new ElectricityAdminModel();
            admin.setId(rs.getString("id"));
            admin.setRegion(rs.getString("region"));
            admin.setContactNumber(rs.getString("contact_number"));
            admin.setEmail(rs.getString("email"));
            admin.setPassword(rs.getString("password"));
            admin.setUtilityType(ElectricityAdminModel.UtilityType.valueOf(rs.getString("utilityType")));
            admins.add(admin);
        }

        rs.close();
        statement.close();
        connection.close();
        return admins;
    }

}
