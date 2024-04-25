package DAO.dao;

import DAO.impl.ManualPaymentImpl;
import model.ManualPaymentsModel;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class WaterManualPaymentDao implements ManualPaymentImpl {

        public void insertintomanualpayment(ManualPaymentsModel manualpayments)throws SQLException{
        Connection connection= Connectdb.getConnection();
        PreparedStatement ps=connection.prepareStatement(("INSERT INTO  water_manual_payment(account_number, nic, amount, date) VALUES (?,?,?,?)"));
        ps.setString(1, manualpayments.getAccount_number());
        ps.setString(2, manualpayments.getNic());
        ps.setString(3, manualpayments.getAmount());
        ps.setString(4, String.valueOf(manualpayments.getDate()));
        ps.executeUpdate();
    }

    @Override
        public List<ManualPaymentsModel> getManualPayments()throws SQLException{
        Connection connection=Connectdb.getConnection();
        List<ManualPaymentsModel> manualPaymentsList =new ArrayList<>();

        String sql="SELECT account_number, nic, amount, date FROM water_manual_payment";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            java.sql.Date dateSql=rs.getDate("date");
            java.time.LocalDateTime date=dateSql.toLocalDate().atStartOfDay();

            ManualPaymentsModel manualPayment=new ManualPaymentsModel(
                    rs.getString("account_number"),
                    rs.getString("nic"),
                    rs.getString("amount"),
                    date
            );
            manualPaymentsList.add(manualPayment);
        }
        rs.close();
        ps.close();
        connection.close();
        return manualPaymentsList;
    }
}
