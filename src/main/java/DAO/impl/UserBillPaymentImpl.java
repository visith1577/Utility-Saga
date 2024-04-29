package DAO.impl;

import model.BillModel;
import model.UserAccountsModel;

import java.sql.SQLException;
import java.util.List;

public interface UserBillPaymentImpl {
    List<UserAccountsModel> getUserAccounts(String nic, String category) throws SQLException;

    List<BillModel> getOverdueElectricityBills(String region) throws SQLException;

    List<BillModel> getOverdueElectricityBillsByAN(String region, String account) throws SQLException;

    List<BillModel> getOverdueWaterBills(String region) throws SQLException;

    List<BillModel> getOverdueWaterBillsByAN(String region, String account) throws SQLException;

    int updateElectricityBill(String accountnum, String billId, Double amount) throws Exception;

    int updateWaterBill(String accountnum, String billId, Double amount) throws Exception;
}
