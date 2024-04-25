package DAO.impl;

import model.ElecWaterAccountsModel;
import utils.PreparedStatementResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ElecWaterAccountsModelImpl{
    void saveAccount(ElecWaterAccountsModel account, String deviceId) throws SQLException;
    void saveWaterAccount(ElecWaterAccountsModel account, String deviceId) throws SQLException;
    PreparedStatementResults createMeterTable(String iotId, Connection conn) throws SQLException;
    PreparedStatement createMeterBudgetTable(String iotId, Connection conn) throws SQLException;
    PreparedStatement insertInitialBudget(String iotId, Connection conn) throws SQLException;
    PreparedStatement deleteMeterTable(String iotId, Connection conn) throws SQLException;
    PreparedStatement deleteMeterBudgetTable(String iotId, Connection conn) throws SQLException;
}
