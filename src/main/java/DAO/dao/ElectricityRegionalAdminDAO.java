package DAO.dao;

import model.ComplaintModel;
import model.ComplaintRAdmin;
import model.UModelRegional;
import model.UsersRAdmin;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElectricityRegionalAdminDAO {
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

    public List<ComplaintRAdmin> getComplaints() throws SQLException {
        List<ComplaintRAdmin> complaint_list = new ArrayList<>();

        Connection con = Connectdb.getConnection();


        String sql = "SELECT c.complaint_no, c.complaint_category, c.complaint_type, c.account_number, c.complaint_status, u.firstName, u.lastName, u.mobile "
                + "FROM electricity_complaint c "
                + "JOIN users u ON c.nic = u.nic AND u.district = ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, adminDistrict);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ComplaintRAdmin complaint = new ComplaintRAdmin(
                    rs.getString("complaint_no"),
                    rs.getString("complaint_category"),
                    rs.getString("complaint_type"),
                    rs.getString("account_number"),
                    rs.getString("complaint_status")
            );

            UsersRAdmin user = new UsersRAdmin(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("mobile")
            );

            complaint.setUsernic(user);

            complaint_list.add(complaint);
        }

        rs.close();
        stmt.close();
        con.close();

        return complaint_list;
    }
}
