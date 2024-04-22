package DAO.dao;

import DAO.impl.ElecWaterAccountsModelImpl;
import model.ElecWaterAccountsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
            if (!account.getIotId().isEmpty()) {
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

            if (account.getRequestId() != null) {
                stmt.setString(2, account.getRequestId().toString());
            } else {
                stmt.setString(2, null);
            }

            stmt.setString(3, account.getNic());
            stmt.setString(4, account.getRegion());
            stmt.setString(5, account.getSubRegion());
            stmt.setString(6, account.getIotId());
            if (!account.getIotId().isEmpty()) {
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

    @Override
    public void createMeterTable(String iotId) throws SQLException {
        Connection conn = Connectdb.getConnection();
        String sql = "CREATE TABLE " + iotId + "_meter" +
                " (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "date DATE DEFAULT (CURRENT_DATE()), " +
                "time TIME DEFAULT (CURRENT_TIME()), " +
                "data INT, " +
                "active BOOLEAN DEFAULT TRUE)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        LocalDate prevMonthLastDay = LocalDate.now().withDayOfMonth(1).minusDays(1);
        String tableName = iotId + "_meter";
        String insertQuery = "INSERT INTO " + tableName + " (date, time, data, active) VALUES (?, '00:00:00', 0, FALSE)";
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
        insertStmt.setObject(1, prevMonthLastDay);
        insertStmt.executeUpdate();

        stmt.close();
        insertStmt.close();
        Connectdb.closeConnection(conn);
    }

    @Override
    public void createMeterBudgetTable(String iotId) throws SQLException {
        Connection conn = Connectdb.getConnection();
        String sql = "CREATE TABLE " + iotId + "_budget_values" +
                " (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "month VARCHAR(100), " +
                "data INT, " +
                "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
        Connectdb.closeConnection(conn);
    }

    @Override
    public void insertInitialBudget(String iotId) throws SQLException {
        Connection conn = Connectdb.getConnection();
        String tableName = iotId + "_budget_values";
        List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");


        String sql = "INSERT INTO " + iotId + "_budget_values (month, data) VALUES ('January', 0)";
        String insertQuery = "INSERT INTO " + tableName + " (month, data) VALUES (?, 0)";
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

        for (String month : months) {
            insertStmt.setString(1, month);
            insertStmt.executeUpdate();
        }

        // Clean up resources
        insertStmt.close();
        Connectdb.closeConnection(conn);
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
