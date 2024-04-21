package DAO.dao;

import DAO.impl.Connection;
import jakarta.servlet.annotation.WebServlet;
import model.ConnectionModel;
import utils.Connectdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class WaterConnectionDao implements Connection {
    @Override
    public void saveConnection(ConnectionModel connection) throws SQLException {
        java.sql.Connection conn = Connectdb.getConnection();

        String sql = "INSERT INTO water_connection_request (requester_name, account_number, nic, email, " +
                "mobile, region, current_address, new_address, nearest_account, " +
                "connection_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

        stmt.executeUpdate();
        stmt.close();
        Connectdb.closeConnection(conn);
    }

    @Override
    public List<ConnectionModel> getConnectionRegionalAdmin(String region) throws SQLException{
        List<ConnectionModel> connections = new ArrayList<>();
        java.sql.Connection connection = Connectdb.getConnection();
        String sql = "SELECT req.*\n" +
                "FROM water_connection_request req\n" +
                "JOIN water_admin admin ON req.region = admin.region\n" +
                "WHERE (admin.region = ? AND req.account_status != 'ADDED' AND req.region = admin.region)\n";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,region);

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
    public List<ConnectionModel> getConnectionRegionalAdminByNIC(String region,String searchValue) throws SQLException{
        List<ConnectionModel> connections = new ArrayList<>();
        java.sql.Connection connection = Connectdb.getConnection();
        System.out.println("nic inside getConnectionbyNIC: "+ searchValue);
        String sql = "SELECT req.*\n" +
                "FROM water_connection_request req\n" +
                "JOIN water_admin admin ON req.region = admin.region\n" +
                "WHERE admin.region = ?\n" +
                "  AND req.account_status != 'ADDED'\n" +
                "  AND req.region = admin.region\n" +
                "  AND (req.requester_name LIKE ? OR req.account_number LIKE ? OR req.nic LIKE ? OR req.email LIKE ? OR req.mobile LIKE ? OR\n" +
                "       req.region LIKE ? OR req.current_address LIKE ? OR req.new_address LIKE ? OR req.nearest_account LIKE ? OR\n" +
                "       req.connection_requirement LIKE ? OR req.connection_type LIKE ? OR req.account_status LIKE ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,region);
        stmt.setString(2, "%" + searchValue + "%");
        stmt.setString(3, "%" + searchValue + "%");
        stmt.setString(4, "%" + searchValue + "%");
        stmt.setString(5, "%" + searchValue + "%");
        stmt.setString(6, "%" + searchValue + "%");
        stmt.setString(7, "%" + searchValue + "%");
        stmt.setString(8, "%" + searchValue + "%");
        stmt.setString(9, "%" + searchValue + "%");
        stmt.setString(10, "%" + searchValue + "%");
        stmt.setString(11, "%" + searchValue + "%");
        stmt.setString(12, "%" + searchValue + "%");
        stmt.setString(13, "%" + searchValue + "%");
        System.out.println("nic inside getConnectionbyNIC: Succesful");

        ResultSet rs= stmt.executeQuery();

        while (rs.next()){
            ConnectionModel conRequest = new ConnectionModel();
            conRequest.setRequesterName(rs.getString("requester_name"));
            conRequest.setRequestId(rs.getString("request_id"));
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
        java.sql.Connection connection = Connectdb.getConnection();


        try (PreparedStatement stmt = connection.prepareStatement("UPDATE water_connection_request SET account_status = ? WHERE account_number = ?")) {

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
        java.sql.Connection connection = Connectdb.getConnection();
        ConnectionModel model = new ConnectionModel();
        System.out.println("getApprovalStatus called");
        System.out.println("complaintno: "+ accountno);

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT account_status FROM water_connection_request WHERE account_number = ?");
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
