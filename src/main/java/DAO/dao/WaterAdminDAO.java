package DAO.dao;

import model.ElectricityAdminModel;
import DAO.impl.WaterAdminImpl;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        statement.setString(6, admin.getUsername());
        statement.setString(7, admin.getFirstname());
        statement.setString(8, admin.getLastname());
        statement.setString(9, admin.getRole().toString().toUpperCase());
        statement.setString(10, admin.getMobile());


        int rowsAffected = statement.executeUpdate();
        connection.close();
        return rowsAffected;
    }

    @Override
    public List<ElectricityAdminModel> getWaterAdmins(ElectricityAdminModel.Role role) throws Exception {
        List <ElectricityAdminModel> admins = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String query = "SELECT * FROM water_admin WHERE role=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, role.toString().toUpperCase());

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            ElectricityAdminModel adminw = new ElectricityAdminModel();
            adminw.setId(rs.getString("id"));
            adminw.setRegion(rs.getString("region"));
            adminw.setContactNumber(rs.getString("contact_number"));
            adminw.setEmail(rs.getString("email"));
            adminw.setPassword(rs.getString("password"));
            adminw.setEmpId(rs.getString("empid"));
            adminw.setUsername(rs.getString("uname"));
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
    public String getPasswordByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT password FROM water_admin WHERE region = ?"
            );

            statement.setString(1, nic);
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
    public void updatePassword(ElectricityAdminModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE water_admin SET  password = ? WHERE region = ?"
            );

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getRegion());

            statement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }
}
