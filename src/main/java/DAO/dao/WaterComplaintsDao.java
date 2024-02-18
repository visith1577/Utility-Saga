package DAO.dao;

import DAO.impl.Complaints;
import model.WaterComplaints;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaterComplaintsDao implements Complaints {

    public void saveComplaint(WaterComplaints complaint) throws SQLException {
        Connection conn = Connectdb.getConnection();

        String sql = "INSERT INTO water_complaint (complaint_no, complaint_category, complaint_type, account_number, nic, email, mobile,complaint) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, complaint.getComplaint_no());
        stmt.setString(2, complaint.getComplaint_category());
        stmt.setString(3, complaint.getComplaint_type());
        stmt.setString(4, complaint.getAccount_number());
        stmt.setString(5, complaint.getNic());
        stmt.setString(6, complaint.getEmail());
        stmt.setString(7, complaint.getPhoneNumber());
        stmt.setString(8, complaint.getComplaint_description());

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public List<WaterComplaints> getComplaints() throws SQLException{
        List<WaterComplaints> complaint_list = new ArrayList<>();

        return complaint_list;
    }
}
