package DAO.impl;

import model.IoTModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Analytics {
    List<IoTModel> getMeterDataMonthly(String meter) throws SQLException;
    List<IoTModel> getDataForCurrentDate(String meter) throws SQLException;
    List<IoTModel> getFinalReadingsDailyForCurrentMonth(String meter) throws SQLException;
    void setBudget(String account, int budget, String month) throws SQLException;
    IoTModel getBudget(String account) throws SQLException;
    Map<String, Integer> getBudgetAll(String account) throws SQLException;
}
