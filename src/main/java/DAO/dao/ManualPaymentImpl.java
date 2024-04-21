package DAO.dao;

import model.ManualPaymentsModel;

import java.sql.SQLException;
import java.util.List;

public interface ManualPaymentImpl {
    List<ManualPaymentsModel> getManualPayments() throws SQLException;
}
