package DAO.impl;

import model.IoTModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Analytics {
    List<IoTModel> getElectricityMeterDataMonthly(String account) throws SQLException;
    List<IoTModel> getDataForCurrentDate(String account) throws SQLException;
    List<IoTModel> getFinalReadingsDailyForCurrentMonth(String account) throws SQLException;
    void setBudget(String account, int budget, String month) throws SQLException;
    IoTModel getBudget(String account) throws SQLException;
    Map<String, Integer> getBudgetAll(String account) throws SQLException;
}
