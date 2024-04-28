package DAO.dao;

import DAO.impl.Validations;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValidationDao implements Validations {
    @Override
    public boolean isUserNameExists(String username) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE uname = ?");
            stmt.setString(1, username);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isBNumExists(String bnum) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM solar_company WHERE bnum = ?");
            stmt.setString(1, bnum);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isEmailExists(String email) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
            stmt.setString(1, email);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isNICExists(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE nic = ?");
            stmt.setString(1, nic);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isMeterExists(String meterNo, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            switch (category.toUpperCase()) {
                case "ELECTRICITY":
                    PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM eAccount_list WHERE iot_id = ?");
                    stmt.setString(1, meterNo);
                    try (ResultSet result = stmt.executeQuery()) {
                        if (result.next()) {
                            value = result.getInt(1);
                        } else {
                            value = 0;
                        }
                    }
                    break;
                case "WATER":
                    PreparedStatement stmt1 = connection.prepareStatement("SELECT COUNT(*) FROM wAccount_list WHERE iot_id = ?");
                    stmt1.setString(1, meterNo);
                    try (ResultSet result = stmt1.executeQuery()) {
                        if (result.next()) {
                            value = result.getInt(1);
                        } else {
                            value = 0;
                        }
                    }
                    break;
                default:
                    value = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isAccountExists(String accountNo, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            switch (category.toUpperCase()) {
                case "ELECTRICITY":
                    PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM eAccount_list WHERE account_number = ?");
                    stmt.setString(1, accountNo);
                    try (ResultSet result = stmt.executeQuery()) {
                        if (result.next()) {
                            value = result.getInt(1);
                        } else {
                            value = 0;
                        }
                    }
                    break;
                case "WATER":
                    PreparedStatement stmt1 = connection.prepareStatement("SELECT COUNT(*) FROM wAccount_list WHERE account_number = ?");
                    stmt1.setString(1, accountNo);
                    try (ResultSet result = stmt1.executeQuery()) {
                        if (result.next()) {
                            value = result.getInt(1);
                        } else {
                            value = 0;
                        }
                    }
                    break;
                default:
                    value = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isRequestIdExist(int reqId, String category) throws SQLException {
        Connection connection = Connectdb.getConnection();
        int value;
        try {
            switch (category.toUpperCase()) {
                case "ELECTRICITY":
                    PreparedStatement stmt = connection.prepareStatement(
                            "SELECT COUNT(*) FROM electricity_connection_request " +
                                    "WHERE id = ? AND connection_requirement = ? AND account_status = ?"
                    );
                    stmt.setInt(1, reqId);
                    stmt.setString(2, "CONNECTION");
                    stmt.setString(3, "PENDING");
                    try (ResultSet result = stmt.executeQuery()) {
                        if (result.next()) {
                            value = result.getInt(1);
                        } else {
                            value = 0;
                        }
                    }
                    break;
                case "WATER":
                    PreparedStatement stmt1 = connection.prepareStatement(
                            "SELECT COUNT(*) FROM water_connection_request " +
                                    "WHERE id = ? AND connection_requirement = ? AND account_status = ?"
                    );
                    stmt1.setInt(1, reqId);
                    stmt1.setString(2, "CONNECTION");
                    stmt1.setString(3, "PENDING");
                    try (ResultSet result = stmt1.executeQuery()) {
                        if (result.next()) {
                            value = result.getInt(1);
                        } else {
                            value = 0;
                        }
                    }
                    break;
                default:
                    value = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    //RegionalAdmin- Electricity
    @Override
    public boolean isEmpIDExistsElectricity(String empid) throws SQLException{
        Connection connection = Connectdb.getConnection();
        int value;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM electricity_admin WHERE empid = ?");
            stmt.setString(1,empid);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isEmailExistsElectricity(String email) throws SQLException{
        Connection connection = Connectdb.getConnection();
        int value;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM electricity_admin WHERE email = ?");
            stmt.setString(1,email);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isRegionExistsElectricity(String email) throws SQLException{
        Connection connection = Connectdb.getConnection();
        int value;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM electricity_admin WHERE region = ?");//check
            stmt.setString(1,email);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isEmpIDExistsWater(String empid) throws SQLException{
        Connection connection = Connectdb.getConnection();
        int value;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM water_admin WHERE empid = ?");
            stmt.setString(1,empid);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isEmailExistsWater(String email) throws SQLException{
        Connection connection = Connectdb.getConnection();
        int value;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM water_admin WHERE email = ?");
            stmt.setString(1,email);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    @Override
    public boolean isRegionExistsWater(String email) throws SQLException{
        Connection connection = Connectdb.getConnection();
        int value;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM water_admin WHERE region = ?");//check
            stmt.setString(1,email);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    value = result.getInt(1);
                } else {
                    value = 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return value != 0;
    }

    public List<String> getAccountList(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        List<String> accountList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT account_number FROM eAccount_list WHERE nic = ?");
            stmt.setString(1, nic);
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    accountList.add(result.getString("account_number"));
                }
            }
            PreparedStatement stmt1 = connection.prepareStatement("SELECT account_number FROM wAccount_list WHERE nic = ?");
            stmt1.setString(1, nic);
            try (ResultSet result = stmt1.executeQuery()) {
                while (result.next()) {
                    accountList.add(result.getString("account_number"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return accountList;
    }
}
