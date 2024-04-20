package DAO.dao;

import DAO.impl.ElecWaterAccountsModelImpl;
import model.ElecWaterAccountsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElecWaterAccountsDAO implements ElecWaterAccountsModelImpl {
//    @Override
//    public void saveAccount(ElecWaterAccountsModel account) throws SQLException {
//        Connection conn = Connectdb.getConnection();
//        PreparedStatement stmt = null;
//        String updatesql = null;
//        PreparedStatement stmt2 = null;
//        try {
//            conn.setAutoCommit(false);
//
//
//            String sql = "INSERT INTO eaccount_list (account_number, nic, region, sub_region) " +
//                    " VALUES (?, ?, ?, ?)";
//            stmt = conn.prepareStatement(sql);
//
//            stmt.setString(1, account.getAccountNumber());
//            stmt.setString(2, account.getNic());
//            stmt.setString(3, account.getRegion());
//            stmt.setString(4, account.getSubRegion());
//            stmt.executeUpdate();
//
//            updatesql = "UPDATE electricity_connection_request AS r INNER JOIN eaccount_list AS e ON r.id = e.request_id SET r.account_status = 'ADDED' WHERE e.account_number = ? ";
//            stmt2 = conn.prepareStatement(updatesql);
//            stmt2.setString(1, account.getAccountNumber());
//            stmt2.executeUpdate();
//            conn.commit();
//        } catch (SQLException e) {
//            if (conn != null) {
//                conn.rollback();
//            }
//            throw e;
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (stmt2 != null) {
//                stmt2.close();
//            }
//            if (conn != null) {
//                conn.setAutoCommit(true);
//                Connectdb.closeConnection(conn);
//            }
//        }
//    }


    @Override
    public void saveAccount(ElecWaterAccountsModel account) throws SQLException {
        Connection conn = Connectdb.getConnection();

        String sql= "INSERT INTO eaccount_list (account_number, nic, region, sub_region) " +
                    " VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, account.getAccountNumber());
        stmt.setString(2, account.getNic());
        stmt.setString(3, account.getRegion());
        stmt.setString(4, account.getSubRegion());


        stmt.executeUpdate();
        stmt.close();
        Connectdb.closeConnection(conn);

    }
}
