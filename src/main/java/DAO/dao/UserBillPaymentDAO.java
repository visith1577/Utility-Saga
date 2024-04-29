package DAO.dao;

import model.BillModel;
import model.UserAccountsModel;
import utils.Connectdb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBillPaymentDAO implements DAO.impl.UserBillPaymentImpl {
    public List<UserAccountsModel> getUserAccounts(String nic, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<UserAccountsModel> account_list = new ArrayList<>();

        try {
            String tableName = selectTable(category);
            System.out.println(tableName);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT  account_number,balance FROM " + tableName + " WHERE nic = ?"
            );

            statement.setString(1, nic);
            System.out.println(nic);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    UserAccountsModel model = new UserAccountsModel();
                    model.setAccount_number(result.getString("account_number"));
                    model.setBalance(result.getDouble("balance"));

                    if (category.equals("WATER")) {
                        model.setType(UserAccountsModel.Acc.WATER);
                    } else {
                        model.setType(UserAccountsModel.Acc.ELECTRICITY);
                    }

                    account_list.add(model);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return account_list;
    }

    public void updateBalance(String account_number, Double balance, String category) throws  SQLException{
        Connection connection = Connectdb.getConnection();
        String tableName = selectTable(category);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + tableName + " SET balance = balance - ? WHERE account_number = ?"
            );

            statement.setDouble(1, balance);
            statement.setString(2, account_number);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    private String selectTable(String category) {
        String tableName;
        System.out.println(category);
        switch (category.toUpperCase()) {
            case "WATER":
                tableName = "wAccount_list";
                break;
            case "ELECTRICITY":
                tableName = "eAccount_list";
                break;
            default:
                throw new IllegalArgumentException("Invalid table name: " + category);
        }

        return tableName;
    }

    @Override
    public List<BillModel> getOverdueElectricityBills(String region) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<BillModel> overdueBills = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT eb.*\n" +
                "FROM electricity_bill eb\n" +
                "INNER JOIN eaccount_list al ON eb.account_number = al.account_number\n" +
                "WHERE al.region = ? AND eb.status = 'OVERDUE' ORDER BY eb.billedDate ASC;");

        stmt.setString(1,region);

        try (ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                BillModel model = new BillModel();
                model.setBillId(result.getString("billId"));
                model.setAmount(Float.valueOf(result.getString("amount")));
                model.setBilledDate(Date.valueOf(result.getString("billedDate")));
                model.setDueDate(Date.valueOf(result.getString("dueDate")));
                model.setStatus(BillModel.Status.valueOf(result.getString("status")));
                model.setAccountNumber(result.getString("account_number"));


                overdueBills.add(model);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        Connectdb.closeConnection(connection);
        return overdueBills;

    }

    @Override
    public List<BillModel> getOverdueElectricityBillsByAN(String region, String account) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<BillModel> overdueBills = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT eb.*\n" +
                "FROM electricity_bill eb\n" +
                "INNER JOIN eaccount_list al ON eb.account_number = al.account_number\n" +
                "WHERE al.region = ? AND eb.status = 'OVERDUE' AND (eb.account_number LIKE ? OR eb.billId LIKE ?) ORDER BY eb.billedDate ASC;");

        stmt.setString(1,region);
        stmt.setString(2, "%"+account+ "%");
        stmt.setString(3,"%"+account+ "%");

        try (ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                BillModel model = new BillModel();
                model.setBillId(result.getString("billId"));
                model.setAmount(Float.valueOf(result.getString("amount")));
                model.setBilledDate(Date.valueOf(result.getString("billedDate")));
                model.setDueDate(Date.valueOf(result.getString("dueDate")));
                model.setStatus(BillModel.Status.valueOf(result.getString("status")));
                model.setAccountNumber(result.getString("account_number"));


                overdueBills.add(model);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        Connectdb.closeConnection(connection);
        return overdueBills;

    }

    @Override
    public List<BillModel> getOverdueWaterBills(String region) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<BillModel> overdueBills = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT eb.*\n" +
                "FROM water_bill eb\n" +
                "INNER JOIN waccount_list al ON eb.account_number = al.account_number\n" +
                "WHERE al.region = ? AND eb.status = 'OVERDUE' ORDER BY eb.billedDate ASC;");

        stmt.setString(1,region);

        try (ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                BillModel model = new BillModel();
                model.setBillId(result.getString("billId"));
                model.setAmount(Float.valueOf(result.getString("amount")));
                model.setBilledDate(Date.valueOf(result.getString("billedDate")));
                model.setDueDate(Date.valueOf(result.getString("dueDate")));
                model.setStatus(BillModel.Status.valueOf(result.getString("status")));
                model.setAccountNumber(result.getString("account_number"));


                overdueBills.add(model);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        Connectdb.closeConnection(connection);
        return overdueBills;

    }

    @Override
    public List<BillModel> getOverdueWaterBillsByAN(String region, String account) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<BillModel> overdueBills = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT eb.*\n" +
                "FROM water_bill eb\n" +
                "INNER JOIN waccount_list al ON eb.account_number = al.account_number\n" +
                "WHERE al.region = ? AND eb.status = 'OVERDUE' AND (eb.account_number LIKE ? OR eb.billId LIKE ?) ORDER BY eb.billedDate ASC;");

        stmt.setString(1,region);
        stmt.setString(2,"%"+account+ "%");
        stmt.setString(3,"%"+account+ "%");

        try (ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                BillModel model = new BillModel();
                model.setBillId(result.getString("billId"));
                model.setAmount(Float.valueOf(result.getString("amount")));
                model.setBilledDate(Date.valueOf(result.getString("billedDate")));
                model.setDueDate(Date.valueOf(result.getString("dueDate")));
                model.setStatus(BillModel.Status.valueOf(result.getString("status")));
                model.setAccountNumber(result.getString("account_number"));


                overdueBills.add(model);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        Connectdb.closeConnection(connection);
        return overdueBills;

    }

    @Override
    public int updateElectricityBill(String accountnum, String billId, Double amount) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected = 0;

        try {
            connection = Connectdb.getConnection();
            connection.setAutoCommit(false);

            String insertSql = "INSERT INTO electricity_bill (billId, amount, account_number) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, billId);
            statement.setDouble(2, amount);
            statement.setString(3, accountnum);
            rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                String updateStatusSql = "UPDATE electricity_bill SET status = 'PAID' WHERE billId = ?";
                statement = connection.prepareStatement(updateStatusSql);
                statement.setString(1, billId);
                statement.executeUpdate();

                String updateBalanceSql = "UPDATE eaccount_list SET balance = balance - ? WHERE account_number = ?";
                statement = connection.prepareStatement(updateBalanceSql);
                statement.setDouble(1, amount);
                statement.setString(2, accountnum);
                statement.executeUpdate();

                connection.commit();
            } else {
                throw new Exception("Failed to insert electricity bill.");
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        return rowsAffected;
    }

    @Override
    public int updateWaterBill(String accountnum, String billId, Double amount) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected = 0;

        try {
            connection = Connectdb.getConnection();
            connection.setAutoCommit(false);

            String insertSql = "INSERT INTO water_bill (billId, amount, account_number) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, billId);
            statement.setDouble(2, amount);
            statement.setString(3, accountnum);
            rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                String updateStatusSql = "UPDATE water_bill SET status = 'PAID' WHERE billId = ?";
                statement = connection.prepareStatement(updateStatusSql);
                statement.setString(1, billId);
                statement.executeUpdate();

                String updateBalanceSql = "UPDATE waccount_list SET balance = balance - ? WHERE account_number = ?";
                statement = connection.prepareStatement(updateBalanceSql);
                statement.setDouble(1, amount);
                statement.setString(2, accountnum);
                statement.executeUpdate();

                connection.commit();
            } else {
                throw new Exception("Failed to insert electricity bill.");
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        return rowsAffected;
    }


}