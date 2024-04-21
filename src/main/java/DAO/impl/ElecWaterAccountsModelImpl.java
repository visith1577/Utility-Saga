package DAO.impl;

import model.ElecWaterAccountsModel;
import java.sql.SQLException;
import java.util.List;

public interface ElecWaterAccountsModelImpl{
    void saveAccount(ElecWaterAccountsModel account) throws SQLException;
    void saveWaterAccount(ElecWaterAccountsModel account) throws SQLException;
    void createMeterTable(String iotId) throws SQLException;
    void createMeterBudgetTable(String iotId) throws SQLException;
    void insertInitialBudget(String iotId) throws SQLException;
}
