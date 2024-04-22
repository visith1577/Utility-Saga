package DAO.impl;

import model.ManualPaymentsModel;

import java.sql.SQLException;
import java.util.List;

    public interface ManualPaymentImpl {
        public void insertintomanualpayment(ManualPaymentsModel manualpayments) throws SQLException;

        List<ManualPaymentsModel> getManualPayments(ManualPaymentsModel manualPayments) throws SQLException;

        List<ManualPaymentsModel> getManualPayments() throws SQLException;
    }
