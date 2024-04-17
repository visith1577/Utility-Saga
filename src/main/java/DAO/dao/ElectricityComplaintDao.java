package DAO.dao;

import DAO.impl.Complaints;
import model.ComplaintModel;
import model.SolarCompanyModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElectricityComplaintDao implements Complaints {

    @Override
    public void saveComplaint(ComplaintModel complaint) throws SQLException {
        Connection conn = Connectdb.getConnection();

        String sql = "INSERT INTO electricity_complaint (complaint_no, complaint_category, complaint_type, account_number, nic, email, mobile, complaint) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

        stmt.close();
        Connectdb.closeConnection(conn);
    }

    @Override
    public List<ComplaintModel> getComplaints() throws SQLException {
        List<ComplaintModel> complaint_list = new ArrayList<>();

        Connection conn = Connectdb.getConnection();

        String sql = "SELECT *\n" +
                "FROM electricity_complaint ec\n" +
                "JOIN eAccount_list ea ON ec.account_number = ea.account_number\n" +
                "JOIN electricity_admin ead ON ea.region = ead.region WHERE ea.region = ead.region\n" +
                "AND ec.account_number = ea.account_number";

//        String sql= "SELECT * FROM electricity_complaint";
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            ComplaintModel complaint = new ComplaintModel();
            complaint.setComplaintNo(rs.getString("complaint_no"));
            complaint.setComplaintCategory(rs.getString("complaint_category"));
            complaint.setComplaintType(rs.getString("complaint_type"));
            complaint.setNic(rs.getString("nic"));
            complaint.setAccountNumber(rs.getString("account_number"));
            complaint.setMobile(rs.getString("mobile"));
            complaint.setComplaintStatus(ComplaintModel.ComplaintStatus.valueOf(rs.getString("complaint_status")));
            complaint.setComplaintDescription(rs.getString("complaint"));

            complaint_list.add(complaint);
        }

        rs.close();
        stmt.close();
        conn.close();

        return complaint_list;
    }

    @Override
    public List<ComplaintModel> getComplaintsByComplaintID(String id) throws SQLException {
        List<ComplaintModel> complaint_list = new ArrayList<>();

        Connection conn = Connectdb.getConnection();

        String sql = "SELECT *\n" +
                "FROM electricity_complaint ec\n" +
                "JOIN eAccount_list ea ON ec.account_number = ea.account_number\n" +
                "JOIN electricity_admin ead ON ea.region = ead.region WHERE ea.region = ead.region\n" +
                "AND ec.account_number = ea.account_number AND ec.complaint_no = ?";

//        String sql= "SELECT * FROM electricity_complaint";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, id);

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
            complaint.setComplaintDescription(rs.getString("complaint"));

            complaint_list.add(complaint);
        }

        rs.close();
        stmt.close();
        conn.close();

        return complaint_list;
    }

    @Override
    public void updateApprovalStatus(String complaintno, String status) throws SQLException {
        Connection connection = Connectdb.getConnection();


        try (PreparedStatement stmt = connection.prepareStatement("UPDATE electricity_complaint SET complaint_status = ? WHERE complaint_no = ?")) {

            stmt.setString(1, status);
            stmt.setString(2, complaintno);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update approval status: " + e.getMessage());
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public ComplaintModel getApprovalStatus(String complaintno) throws SQLException {
        Connection connection = Connectdb.getConnection();
        ComplaintModel model = new ComplaintModel();
        System.out.println("getApprovalStatus called");
        System.out.println("complaintno: "+ complaintno);

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT complaint_status FROM electricity_complaint WHERE complaint_no = ?");
            stmt.setString(1, complaintno);

            try (ResultSet result = stmt.executeQuery()){
                while(result.next()) {
                    ComplaintModel.ComplaintStatus status = ComplaintModel.ComplaintStatus.valueOf(result.getString("complaint_status"));
                    System.out.println("Final status: "+status);
                    model.setComplaintStatus(status);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Failed to update approval status: " + e.getMessage());
        } finally {
            Connectdb.closeConnection(connection);
        }
        return model;
    }
}