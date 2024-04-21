package DAO.dao;

import DAO.impl.ElecWaterAccountsModelImpl;
import model.ElecWaterAccountsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElecWaterAccountsDAO implements ElecWaterAccountsModelImpl {
    @Override
    public void saveAccount(ElecWaterAccountsModel account) throws SQLException {
        Connection conn = Connectdb.getConnection();
        PreparedStatement stmt = null;
        String updatesql = null;
        PreparedStatement stmt2 = null;
        try {
            conn.setAutoCommit(false);


            String sql = "INSERT INTO eAccount_list (account_number,request_id, nic, region, sub_region, iot_id, iot_meter) " +
                    " VALUES (?,?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, account.getAccountNumber());

            if (account.getRequestId() != null) {
                stmt.setString(2, account.getRequestId().toString());
            } else {
                stmt.setString(2, null);
            }

            stmt.setString(3, account.getNic());
            stmt.setString(4, account.getRegion());
            stmt.setString(5, account.getSubRegion());
            stmt.setString(6, account.getIotId());
            if (account.getIotId() != null) {
                stmt.setString(7, "YES");
            } else {
                stmt.setString(7, "NO");
            }
            stmt.executeUpdate();

            if (account.getRequestId() != null) {
                updatesql = "UPDATE electricity_connection_request AS r INNER JOIN eAccount_list AS e ON r.id = e.request_id SET r.account_status = 'ADDED' WHERE e.request_id = ? ";
                stmt2 = conn.prepareStatement(updatesql);
                stmt2.setString(1, account.getRequestId().toString());
                stmt2.executeUpdate();
            }
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                Connectdb.closeConnection(conn);
            }
        }
    }

    @Override
    public void saveWaterAccount(ElecWaterAccountsModel account) throws SQLException {
        Connection conn = Connectdb.getConnection();
        PreparedStatement stmt = null;
        String updatesql = null;
        PreparedStatement stmt2 = null;
        try {
            conn.setAutoCommit(false);


            String sql = "INSERT INTO wAccount_list (account_number,request_id, nic, region, sub_region, iot_id, iot_meter) " +
                    " VALUES (?,?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getRequestId().toString());
            stmt.setString(3, account.getNic());
            stmt.setString(4, account.getRegion());
            stmt.setString(5, account.getSubRegion());
            stmt.setString(6, account.getIotId());
            if (account.getIotId() != null) {
                stmt.setString(7, "YES");
            } else {
                stmt.setString(7, "NO");
            }
            stmt.executeUpdate();

            if (account.getRequestId() != null) {
                updatesql = "UPDATE water_connection_request AS r INNER JOIN wAccount_list AS e ON r.id = e.request_id SET r.account_status = 'ADDED' WHERE e.request_id = ? ";
                stmt2 = conn.prepareStatement(updatesql);
                stmt2.setString(1, account.getRequestId().toString());
                stmt2.executeUpdate();
            }
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                Connectdb.closeConnection(conn);
            }
        }
    }


//    @Override
//    public void saveAccount(ElecWaterAccountsModel account) throws SQLException {
//        Connection conn = Connectdb.getConnection();
//
//        String sql= "INSERT INTO eaccount_list (account_number, nic, region, sub_region) " +
//                    " VALUES (?, ?, ?, ?)";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//
//        stmt.setString(1, account.getAccountNumber());
//        stmt.setString(2, account.getNic());
//        stmt.setString(3, account.getRegion());
//        stmt.setString(4, account.getSubRegion());
//
//
//        stmt.executeUpdate();
//        stmt.close();
//        Connectdb.closeConnection(conn);
//
//    }
}
