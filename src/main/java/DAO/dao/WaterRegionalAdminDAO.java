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
            admin.setUsername(rs.getString("uname"));
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

    @Override
    public String getPasswordById(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT password FROM water_admin WHERE region = ?"
            );

            statement.setString(1, region);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    storedHash = result.getString("password");
                } else {
                    storedHash = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return storedHash;
    }

    @Override
    public UserRAdmin.Role getUserRoleById(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserRAdmin.Role role = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT role FROM water_admin WHERE region = ?"
            );

            statement.setString(1, region);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    role = UserRAdmin.Role.valueOf(result.getString("role"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return role;
    }
}
