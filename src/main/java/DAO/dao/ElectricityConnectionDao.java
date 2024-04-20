package DAO.dao;

import model.ConnectionModel;
import utils.Connectdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ElectricityConnectionDao implements DAO.impl.Connection {
    @Override
    public void saveConnection(ConnectionModel connection) throws SQLException {
        Connection conn = Connectdb.getConnection();



        String sql = "INSERT INTO electricity_connection_request (requester_name, account_number, nic, email, " +
                "mobile, region, current_address, new_address, nearest_account, " +
                "connection_requirement, connection_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, connection.getRequesterName());
        stmt.setString(2, connection.getAccountNumber());
        stmt.setString(3, connection.getNic());
        stmt.setString(4, connection.getEmail());
        stmt.setString(5, connection.getMobile());
        stmt.setString(6, connection.getRegion());
        stmt.setString(7, connection.getCurrentAddress());
        stmt.setString(8, connection.getNewAddress());
        stmt.setString(9, connection.getNearestAccount());
        stmt.setString(10, connection.getConnectionRequirements().name());
        stmt.setString(11, connection.getConnectionType());

        stmt.executeUpdate();
        stmt.close();
        Connectdb.closeConnection(conn);
    }

    @Override
    public List<ConnectionModel> getConnectionRegionalAdmin() throws SQLException{
        List<ConnectionModel> connections = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        String sql = "SELECT req.*\n" +
                "FROM electricity_connection_request req\n" +
                "JOIN electricity_admin admin ON req.region = admin.region\n" +
                "WHERE req.account_status != 'ADDED' AND req.region = admin.region;";

        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs= stmt.executeQuery();

        while (rs.next()){
            ConnectionModel conRequest = new ConnectionModel();
            conRequest.setRequesterName(rs.getString("requester_name"));
            conRequest.setAccountNumber(rs.getString("account_number"));
            conRequest.setNic(rs.getString("nic"));
            conRequest.setEmail(rs.getString("email"));
            conRequest.setMobile(rs.getString("mobile"));
            conRequest.setRegion(rs.getString("region"));
            conRequest.setCurrentAddress(rs.getString("current_address"));
            conRequest.setNewAddress(rs.getString("new_address"));
            conRequest.setNearestAccount(rs.getString("nearest_account"));
            conRequest.setConnectionRequirements(ConnectionModel.ConnectionRequirement.valueOf(rs.getString("connection_requirement")));
            conRequest.setConnectionType(rs.getString("connection_type"));
            conRequest.setAccountStatus(ConnectionModel.AccountStatus.valueOf(rs.getString("account_status")));

            connections.add(conRequest);


        }
        rs.close();
        stmt.close();
        connection.close();
        return connections;
    }

    @Override
    public List<ConnectionModel> getConnectionRegionalAdminByNIC(String nic) throws SQLException{
        List<ConnectionModel> connections = new ArrayList<>();
        Connection connection = Connectdb.getConnection();
        System.out.println("nic inside getConnectionbyNIC: "+ nic);
        String sql = "SELECT req.*\n" +
                "FROM electricity_connection_request req\n" +
                "JOIN electricity_admin admin ON req.region = admin.region\n" +
                "WHERE req.account_status != 'ADDED' \n" +
                "AND req.account_number = ?\n" +
                "AND req.region = admin.region;";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, nic);
        System.out.println("nic inside getConnectionbyNIC: Succesful");

        ResultSet rs= stmt.executeQuery();

        while (rs.next()){
            ConnectionModel conRequest = new ConnectionModel();
            conRequest.setRequesterName(rs.getString("requester_name"));
            conRequest.setAccountNumber(rs.getString("account_number"));
            conRequest.setNic(rs.getString("nic"));
            conRequest.setEmail(rs.getString("email"));
            conRequest.setMobile(rs.getString("mobile"));
            conRequest.setRegion(rs.getString("region"));
            conRequest.setCurrentAddress(rs.getString("current_address"));
            conRequest.setNewAddress(rs.getString("new_address"));
            conRequest.setNearestAccount(rs.getString("nearest_account"));
            conRequest.setConnectionRequirements(ConnectionModel.ConnectionRequirement.valueOf(rs.getString("connection_requirement")));
            conRequest.setConnectionType(rs.getString("connection_type"));
            conRequest.setAccountStatus(ConnectionModel.AccountStatus.valueOf(rs.getString("account_status")));

            connections.add(conRequest);


        }

        System.out.println("Added connections: "+ connections.size());
        rs.close();
        stmt.close();
        connection.close();
        return connections;
    }

    @Override
    public void updateApprovalStatus(String accountno, String status) throws SQLException {
        Connection connection = Connectdb.getConnection();


        try (PreparedStatement stmt = connection.prepareStatement("UPDATE electricity_connection_request SET account_status = ? WHERE account_number = ?")) {

            stmt.setString(1, status);
            stmt.setString(2, accountno);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update approval status: " + e.getMessage());
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public ConnectionModel getApprovalStatus(String accountno) throws SQLException {
        Connection connection = Connectdb.getConnection();
        ConnectionModel model = new ConnectionModel();
        System.out.println("getApprovalStatus called");
        System.out.println("complaintno: "+ accountno);

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT account_status FROM electricity_connection_request WHERE account_number = ?");
            stmt.setString(1, accountno);

            try (ResultSet result = stmt.executeQuery()){
                while(result.next()) {
                    ConnectionModel.AccountStatus status = ConnectionModel.AccountStatus.valueOf(result.getString("account_status"));
                    System.out.println("Final status: "+status);
                    model.setAccountStatus(status);
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
