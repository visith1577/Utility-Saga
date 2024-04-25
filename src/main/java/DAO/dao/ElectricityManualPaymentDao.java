package DAO.dao;

import DAO.impl.ManualPaymentImpl;
import model.ManualPaymentsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ElectricityManualPaymentDao implements ManualPaymentImpl{

    public void insertintomanualpayment(ManualPaymentsModel manualpayments) throws SQLException {
        Connection connection = Connectdb.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO electricity_manual_payment (account_number, nic, amount, date) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, manualpayments.getAccount_number());
        preparedStatement.setString(2, manualpayments.getNic());
        preparedStatement.setString(3, manualpayments.getAmount());
        preparedStatement.setString(4, String.valueOf(manualpayments.getDate()));
        preparedStatement.executeUpdate();
    }

    @Override
    public List<ManualPaymentsModel> getManualPayments() throws SQLException {
        Connection con = Connectdb.getConnection();
        List<ManualPaymentsModel> manualPaymentsList = new ArrayList<>();

        String sql = "SELECT account_number, nic, amount, date FROM electricity_manual_payment";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            java.sql.Date dateSql = rs.getDate("date");
            LocalDateTime date = dateSql.toLocalDate().atStartOfDay();

            ManualPaymentsModel manualPayment = new ManualPaymentsModel(
                    rs.getString("account_number"),
                    rs.getString("nic"),
                    rs.getString("amount"),
                    date
            );
            manualPaymentsList.add(manualPayment);
        }

        rs.close();
        stmt.close();
        con.close();
        return manualPaymentsList;
    }
}

