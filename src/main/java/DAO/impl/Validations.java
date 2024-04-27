package DAO.impl;

import java.sql.SQLException;

public interface Validations {
    boolean isUserNameExists(String username) throws SQLException;
    boolean isEmailExists(String email) throws SQLException;
    boolean isNICExists(String nic) throws SQLException;

    boolean isMeterExists(String meterNo, String category) throws SQLException;

    boolean isAccountExists(String accountNo, String category) throws SQLException;

    boolean isRequestIdExist(int reqId, String category) throws SQLException;

    //RegionalAdmin- Electricity
    boolean isEmpIDExistsElectricity(String empid) throws SQLException;

    boolean isEmailExistsElectricity(String email) throws SQLException;

    boolean isRegionExistsElectricity(String email) throws SQLException;

    boolean isEmpIDExistsWater(String empid) throws SQLException;

    boolean isEmailExistsWater(String email) throws SQLException;

    boolean isRegionExistsWater(String email) throws SQLException;
}
