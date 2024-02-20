package DAO.impl;

import model.ComplaintModel;

import java.sql.SQLException;
import java.util.List;

public interface Complaints {
    void saveComplaint(ComplaintModel complaint) throws SQLException;
    List<ComplaintModel> getComplaints() throws SQLException;

}
