package DAO.dao;

import model.ConnectionModel;
import utils.Connectdb;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class ElectricityConnectionDao implements DAO.impl.Connection {
    @Override
    public void saveConnection(ConnectionModel connection) throws SQLException {
        Connection conn = Connectdb.getConnection();


        String sql = "INSERT INTO electricity_connection_request (requester_name, account_number, nic, email, " +
                "mobile, region, current_address, new_address, nearest_account, " +
                "connection_requirement, connection_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, connection.getRequester_name());
        stmt.setString(2, connection.getAccount_number());
        stmt.setString(3, connection.getNic());
        stmt.setString(4, connection.getEmail());
        stmt.setString(5, connection.getMobile());
        stmt.setString(6, connection.getRegion());
        stmt.setString(7, connection.getCurrent_address());
        stmt.setString(8, connection.getNew_address());
        stmt.setString(9, connection.getNearest_account());
        stmt.setString(10, connection.getConnection_requirements().name());
        stmt.setString(11, connection.getConnection_type());

        stmt.executeUpdate();
        stmt.close();
        Connectdb.closeConnection(conn);
    }
}
