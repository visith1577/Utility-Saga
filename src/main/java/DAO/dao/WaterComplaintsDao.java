package DAO.dao;

import DAO.impl.Complaints;
import model.ComplaintModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaterComplaintsDao implements Complaints {

    public void saveComplaint(ComplaintModel complaint) throws SQLException {
        Connection conn = Connectdb.getConnection();

        String sql = "INSERT INTO water_complaint (complaint_no, complaint_category, complaint_type, account_number, nic, email, mobile, complaint) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, complaint.getComplaintNo());
        stmt.setString(2, complaint.getComplaintCategory());
        stmt.setString(3, complaint.getComplaintType());
        stmt.setString(4, complaint.getAccountNumber());
        stmt.setString(5, complaint.getNic());
        stmt.setString(6, complaint.getEmail());
        stmt.setString(7, complaint.getPhoneNumber());
        stmt.setString(8, complaint.getComplaintDescription());

        stmt.executeUpdate();
//        System.out.println("closing connection");
        stmt.close();
        conn.close();
    }

    public List<ComplaintModel> getComplaints(String id) throws SQLException{
        List<ComplaintModel> complaint_list = new ArrayList<>();

        Connection conn = Connectdb.getConnection();

        String sql = "SELECT * FROM water_complaint";
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            ComplaintModel complaint = new ComplaintModel();
            complaint.setComplaintNo(rs.getString("complaint_no"));
            complaint.setComplaintCategory(rs.getString("complaint_category"));
            complaint.setComplaintType(rs.getString("complaint_type"));
            complaint.setAccountNumber(rs.getString("account_number"));
            complaint.setNic(rs.getString("nic"));
            complaint.setEmail(rs.getString("email"));
            complaint.setPhoneNumber(rs.getString("mobile"));
            complaint.setComplaintDescription(rs.getString("account_number"));

            complaint_list.add(complaint);
        }

        rs.close();
        stmt.close();
        conn.close();

        return complaint_list;
    }

    @Override
    public List<ComplaintModel> getComplaintsByComplaintID(String region,String id) throws SQLException {
        return null;
    }

    @Override
    public void updateApprovalStatus(String bnum, String status) throws SQLException {

    }

    @Override
    public ComplaintModel getApprovalStatus(String bnum) throws SQLException {
        return null;
    }
}