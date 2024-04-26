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
    public int addRegion(ElectricityAdminModel admin) throws Exception {
        Connection connection = Connectdb.getConnection();
        String query = "INSERT INTO water_region (region) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getRegion());

        int rowsAffected = statement.executeUpdate();
        connection.close();
        return rowsAffected;
    }

    @Override
    public List<String> getRegions() throws Exception {
        List<String> regions = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        ;
        String query = "SELECT region FROM water_region";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet rs = statement.executeQuery();


        while (rs.next()) {
            String region = rs.getString("region");
            regions.add(region);

            regions.add(region);
        }

        rs.close();
        statement.close();
        connection.close();
        return regions;
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
            adminw.setActiveStatus(ElectricityAdminModel.ActiveStatus.valueOf(rs.getString("activate_status")));
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

        String sql = "SELECT region, contact_number, email, password, empid, uname, firstname, lastname, role,activate_status, mobile FROM water_admin WHERE empid = ?";

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
            admin.setActiveStatus(ElectricityAdminModel.ActiveStatus.valueOf(rs.getString("activate_status")));
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

    @Override
    public ElectricityAdminModel getUserDetailsByRegion(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        ElectricityAdminModel admin = new ElectricityAdminModel();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT empid, uname, firstname, lastname, mobile FROM water_admin WHERE region = ?"
            );

            statement.setString(1, region);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    admin.setEmpId(result.getString("empid"));
                    admin.setUsername(result.getString("uname"));
                    admin.setFirstname(result.getString("firstname"));
                    admin.setLastname(result.getString("lastname"));
                    admin.setMobile(result.getString("mobile"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return admin;
    }

    @Override
    public int updateAdminDetails(ElectricityAdminModel admin) throws Exception {
        System.out.println("Inside updateAdmin details");
        Connection connection = Connectdb.getConnection();
        String query = "UPDATE water_admin\n" +
                "SET empid = ?, uname = ?, firstname = ?, lastname = ?, mobile = ?\n" +
                "WHERE region = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getEmpId());
        statement.setString(2, admin.getUsername());
        statement.setString(3, admin.getFirstname());
        statement.setString(4, admin.getLastname());
        statement.setString(5, admin.getMobile());
        statement.setString(6, admin.getRegion());

        System.out.println("Region: "+ admin.getRegion());


        int rowsAffected = statement.executeUpdate();

        System.out.println("Region: "+ admin.getRegion());
        System.out.println("Rows affected: "+ rowsAffected);
        connection.close();
        return rowsAffected;
    }

    @Override
    public int updateImportantDetails(ElectricityAdminModel admin) throws Exception{
        Connection connection = Connectdb.getConnection();
        String query= "UPDATE water_admin\n" +
                "SET contact_number = ?, email = ?\n" +
                "WHERE region = ?;\n";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, admin.getContactNumber());
        statement.setString(2, admin.getEmail());
        statement.setString(3, admin.getRegion());

        int rowsAffected = statement.executeUpdate();

        System.out.println("Region: "+ admin.getRegion());
        System.out.println("Rows affected: "+ rowsAffected);
        connection.close();
        return rowsAffected;
    }

    @Override
    public void updateAccountStatus(String region, String newStatus) throws SQLException {
        Connection connection = Connectdb.getConnection();
        System.out.println("Account before exec: " + region);
        System.out.println("Status before exec: " + newStatus);

        try {
            String query = "UPDATE water_admin SET activate_status = ? WHERE region = ?";
            System.out.println("SQL Query: " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newStatus.toUpperCase());
            preparedStatement.setString(2, region);

            System.out.println("Prepared Statement Parameters:");
            System.out.println("  newStatus (uppercase): " + newStatus.toUpperCase());
            System.out.println("  accountNumber: " + region);

            preparedStatement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getStatusByRegion(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String status = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT activate_status FROM water_admin WHERE region = ?"
            );

            statement.setString(1, region);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    status = result.getString("activate_status");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                Connectdb.closeConnection(connection);
            }
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

}
