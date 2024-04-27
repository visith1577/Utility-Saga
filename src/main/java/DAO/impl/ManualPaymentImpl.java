package DAO.impl;

import model.ManualPaymentsModel;

import java.sql.SQLException;
import java.util.List;

    public interface ManualPaymentImpl {
        public void insertintomanualpayment(ManualPaymentsModel manualpayments) throws SQLException;

//        public void insertwatermanualpayment(ManualPaymentsModel manualpayments) throws SQLException;

        List<ManualPaymentsModel> getManualPayments() throws SQLException;

//        List<ManualPaymentsModel> getWaterManualPayments() throws SQLException;

    }
