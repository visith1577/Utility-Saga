package DAO.impl;

import java.sql.SQLException;

public interface SummaryReport {
    void insertSummary(String summary, String rep, String nic, String account_number) throws SQLException;
    String getSummary(String rep, String nic, String account_number) throws SQLException;
    boolean checkSummaryExists(String rep, String nic, String account_number) throws SQLException;
}
