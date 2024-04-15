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
    public String getPasswordById(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT password FROM electricity_admin WHERE region = ?"
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
    public UserRAdmin getUserDetailsById(String id) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserRAdmin user = new UserRAdmin();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT empid, mobile, email, region, utilityType, role FROM electricity_admin WHERE id = ?"
            );

            statement.setString(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    user.setId(result.getString("empid"));
                    user.setMobile(result.getString("mobile"));
                    user.setEmail(result.getString("email"));
                    user.setRegion(result.getString("region"));
                    user.setUtilityType(UserRAdmin.UtilityType.valueOf(result.getString("utilityType")));
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
    public UserRAdmin.Role getUserRoleById(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserRAdmin.Role role = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT role FROM electricity_admin WHERE region = ?"
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

    @Override
    public String getPasswordSuperAdminById(String username) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT pwd FROM super_admin WHERE uname = ?"
            );

            statement.setString(1, username);
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
}
