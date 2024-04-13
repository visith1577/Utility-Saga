package DAO.dao;

import DAO.impl.UserRegional;
import model.ComplaintRAdmin;
import model.UModelRegional;
import model.UserModel;
import model.UserRAdmin;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ElectricityRegionalAdminDAO implements UserRegional {
    String adminDistrict= "SELECT district FROM electricity_admin";
    public List<UModelRegional> getUsers() throws SQLException {
        List<UModelRegional> user_list = new ArrayList<>();

        Connection con = Connectdb.getConnection();

        String sql = "SELECT nic, firstname, lastname, address, mobile FROM users WHERE region =? ";//Check
        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            UModelRegional user = new UModelRegional(
                rs.getString("nic"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("address"),
                rs.getString("mobile"),
                rs.getString("connectionType"),
                rs.getString("connectionStatus")
            );
            user_list.add(user);
        }

        rs.close();
        stmt.close();
        con.close();
        return user_list;
    }

    @Override
    public void registerUser(UserRAdmin user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO electricity_admin (id, uname, firstname, lastname, password, tel, email, address, region, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            System.out.println(user.getId());
            statement.setString(1, user.getId().trim());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getTel());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getAddress());
            statement.setString(9, user.getRegion());
            statement.setString(10, user.getRole().name());

            statement.executeUpdate();

        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getPasswordById(String id) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT password FROM electricity_admin WHERE id = ?"
            );

            statement.setString(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    storedHash = result.getString("pwd");
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
    public UserRAdmin getUserDetailsById(String id) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserRAdmin user = new UserRAdmin();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT tel, email, address, region, role FROM electricity_admin WHERE id = ?"
            );

            statement.setString(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    user.setTel(result.getString("tel"));
                    user.setEmail(result.getString("email"));
                    user.setAddress(result.getString("address"));
                    user.setRegion(result.getString("region"));
                    UserRAdmin.Role role = UserRAdmin.Role.valueOf(result.getString("role"));
                    user.setRole(role);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return user;
    }

    @Override
    public UserRAdmin.Role getUserRoleById(String id) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserRAdmin.Role role = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT role FROM electricity_admin WHERE id = ?"
            );

            statement.setString(1, id);

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

    public List<ComplaintRAdmin> getComplaints() throws SQLException {
        List<ComplaintRAdmin> complaint_list = new ArrayList<>();

        Connection con = Connectdb.getConnection();


        String sql = "SELECT c.complaint_no, c.complaint_category, c.complaint_type, c.account_number, c.complaint_status, u.firstName, u.lastName, u.mobile "
                + "FROM electricity_complaint c "
                + "JOIN users u ON c.nic = u.nic AND u.region = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, adminDistrict);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ComplaintRAdmin.ComplaintStatus status =ComplaintRAdmin.ComplaintStatus.valueOf(rs.getString("complaint_status"));
            ComplaintRAdmin complaint = new ComplaintRAdmin(
                    rs.getString("complaint_no"),
                    rs.getString("complaint_category"),
                    rs.getString("complaint_type"),
                    rs.getString("account_number"),
                    status
            );

            UserModel user = new UserModel();

            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setMobile(rs.getString("mobile"));

            complaint.setUserNic(user);

            complaint_list.add(complaint);
        }

        rs.close();
        stmt.close();
        con.close();

        return complaint_list;
    }
}
