package DAO.impl;

import model.ConnectionModel;

import java.sql.SQLException;
import java.util.List;

public interface Connection {
    void saveConnection(ConnectionModel connection) throws SQLException;

    List<ConnectionModel> getConnectionRegionalAdmin(String region) throws SQLException;

    List<ConnectionModel> getConnectionRegionalAdminByNIC(String region,String nic) throws SQLException;

    void updateApprovalStatus(String accountno, String status) throws SQLException;

    ConnectionModel getApprovalStatus(String accountno) throws SQLException;
}
