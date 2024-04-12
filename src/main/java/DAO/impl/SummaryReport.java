package DAO.impl;

import java.sql.Date;
import java.sql.SQLException;

public interface SummaryReport {
    void insertSummary(String summary, String rep, String nic) throws SQLException;
    String getSummary(String summary, String rep) throws SQLException;
    boolean checkSummaryExists(String nic, String rep) throws SQLException;
}
