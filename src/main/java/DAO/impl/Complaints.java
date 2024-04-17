package DAO.impl;

import model.ComplaintModel;
import model.SolarCompanyModel;

import java.sql.SQLException;
import java.util.List;

public interface Complaints {
    void saveComplaint(ComplaintModel complaint) throws SQLException;
    List<ComplaintModel> getComplaints() throws SQLException;

    List<ComplaintModel> getComplaintsByComplaintID(String id) throws SQLException;

    void updateApprovalStatus(String bnum, String status) throws SQLException;

    ComplaintModel getApprovalStatus(String bnum) throws SQLException;


}
