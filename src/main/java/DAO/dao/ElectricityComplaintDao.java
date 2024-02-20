package DAO.dao;

import DAO.impl.Complaints;
import model.ComplaintModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ElectricityComplaintDao implements Complaints {

    @Override
    public void saveComplaint(ComplaintModel complaint) throws SQLException {
        Connection conn = Connectdb.getConnection();

        String sql = "INSERT INTO electricity_complaint (complaint_no, complaint_category, complaint_type, account_number, nic, email, mobile,complaint) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

    @Override
    public List<ComplaintModel> getComplaints() throws SQLException {
        return null;
    }
}
