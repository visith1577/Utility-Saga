package DAO.impl;

import model.WaterComplaints;

import java.sql.SQLException;
import java.util.List;

public interface Complaints {
    void saveComplaint(WaterComplaints complaint) throws SQLException;
    List<WaterComplaints> getComplaints() throws SQLException;

}
