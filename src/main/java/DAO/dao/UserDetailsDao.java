package DAO.dao;

import model.UserModel;
import utils.Connectdb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;


public class UserDetailsDao implements DAO.impl.UserDetails {

    @Override
    public void registerUser(UserModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (nic, uname, firstname, lastname, pwd, mobile, home, email, address, provider, region, services) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, user.getNic());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getMobile());
            statement.setString(7, user.getHome());
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getAddress());
            statement.setString(10, user.getProvider().name());
            statement.setString(11, user.getRegion());

            String serviceString = String.join(",", user.getServices());
            statement.setString(12, serviceString);

            statement.executeUpdate();

        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getPasswordByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String storedHash;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT pwd FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    storedHash = result.getString("pwd");
                } else {
                    storedHash = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return storedHash;
    }

    @Override
    public String getUnameByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();

        String uname;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT uname FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    uname = result.getString("uname");
                } else {
                    uname = nic;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return uname;
    }

    @Override
    public UserModel getUserDetailsByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserModel user = new UserModel();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT firstname, lastname, mobile, home, email, address, region, provider, services FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    user.setFirstName(result.getString("firstname"));
                    user.setLastName(result.getString("lastname"));
                    user.setMobile(result.getString("mobile"));
                    user.setHome(result.getString("home"));
                    user.setEmail(result.getString("email"));
                    user.setAddress(result.getString("address"));
                    user.setRegion(result.getString("region"));
                    UserModel.ProviderInfo providerInfo = UserModel.ProviderInfo.valueOf(result.getString("provider"));
                    user.setProvider(providerInfo);
                    user.setServices(new HashSet<>(Collections.singletonList(result.getString("services"))));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return user;
    }

    @Override
    public UserModel getUserFullNameByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        UserModel user = new UserModel();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT firstname, lastname, mobile, home, email, address, region, provider, services FROM users WHERE nic = ?"
            );

            statement.setString(1, nic);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    user.setFirstName(result.getString("firstname"));
                    user.setLastName(result.getString("lastname"));
                    user.setAddress(result.getString("address"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return user;
    }

    @Override
    public void updateUserInfo(UserModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET uname = ?, mobile = ?, email = ? WHERE nic = ?"
            );

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getMobile());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNic());

            statement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public void insertImage(UserModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO images (nic, filename, content_type, data) VALUES (?, ?, ?, ?)"
            );

            statement.setString(1, user.getNic());
            statement.setString(2, user.getNic()+".jpg");
            statement.setString(3, "image/jpeg");
            statement.setBinaryStream(4, user.getImage(), user.getImage().available());

            statement.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public void updateImage(UserModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE images SET filename = ?, content_type = ?, data = ? WHERE nic = ?"
            );

            statement.setString(1, user.getNic()+".jpg");
            statement.setString(2, "image/jpeg");
            statement.setBlob(3, user.getImage());
            statement.setString(4, user.getNic());

            statement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public void updatePassword(UserModel user) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET  pwd = ? WHERE nic = ?"
            );

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getNic());

            statement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public String getImageByNic(String nic) throws SQLException {
        Connection connection = Connectdb.getConnection();
        String base64Image = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT data FROM images WHERE nic = ?"
            );

            statement.setString(1, nic);

            try (ResultSet result = statement.executeQuery()) {
                if(result.next()){
                    Blob blob = result.getBlob("data");
                    if (blob != null) {
                        InputStream inputStream  = blob.getBinaryStream();
                        byte[] imageData = inputStream.readAllBytes();

                        base64Image = Base64.getEncoder().encodeToString(imageData);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connectdb.closeConnection(connection);
        }
        return base64Image;
    }

    @Override
    public void updateServices(String nic, List<String> services) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET services = ? WHERE nic = ?"
            );

            String serviceString = String.join(",", services);
            statement.setString(1, serviceString);
            statement.setString(2, nic);

            statement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public List<UserModel> getUserDetailsRegionalAdmin(String id) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT eal.account_number, u.nic, u.firstname, u.lastname, u.mobile, u.email, u.address, eal.meter_status, eal.iot_meter, eal.iot_id \n" +
                "FROM users u\n" +
                "JOIN eaccount_list eal ON u.nic = eal.nic\n" +
                "JOIN electricity_admin ON eal.region= electricity_admin.region WHERE electricity_admin.region = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,id);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            UserModel user = new UserModel();
            user.setNic(rs.getString("nic"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setMobile(rs.getString("mobile"));
            user.setEmail(rs.getString("email"));
            user.setConnectionStatus(rs.getString("meter_status"));
            user.setAddress(rs.getString("address"));
            user.setAccountNumber(rs.getString("account_number"));
            user.setIotMeter(rs.getString("iot_meter"));
            user.setIotId(rs.getString("iot_id"));

            users.add(user);
        }

        rs.close();
        stmt.close();
        connection.close();
        return users;
    }

    @Override
    public List<UserModel> getUserDetailsByNICRegionalAdmin(String id,String searchValue) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT eal.account_number, u.nic, u.firstname, u.lastname, u.mobile, u.email, u.address, eal.meter_status \n" +
                "FROM users u\n" +
                "JOIN eaccount_list eal ON u.nic = eal.nic\n" +
                "JOIN electricity_admin ON eal.region= electricity_admin.region\n"+
                "WHERE electricity_admin.region = ? AND (eal.account_number LIKE ? OR u.nic LIKE ? OR u.firstname LIKE ? OR u.lastname LIKE ? OR u.address LIKE ?\n"+
                "OR u.mobile LIKE ? OR u.email LIKE ? OR eal.meter_status LIKE ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, "%" + searchValue + "%");
        stmt.setString(3, "%" + searchValue + "%");
        stmt.setString(4, "%" + searchValue + "%");
        stmt.setString(5, "%" + searchValue + "%");
        stmt.setString(6, "%" + searchValue + "%");
        stmt.setString(7, "%" + searchValue + "%");
        stmt.setString(8, "%" + searchValue + "%");
        stmt.setString(9, "%" + searchValue + "%");

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            UserModel user = new UserModel();
            user.setNic(rs.getString("nic"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setMobile(rs.getString("mobile"));
            user.setEmail(rs.getString("email"));
            user.setConnectionStatus(rs.getString("meter_status"));
            user.setAddress(rs.getString("address"));
            user.setAccountNumber(rs.getString("account_number"));

            users.add(user);
        }

        rs.close();
        stmt.close();
        connection.close();
        return users;
    }

    @Override
    public List<UserModel> getWaterDetailsRegionalAdmin(String id) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT eal.account_number, u.nic, u.firstname, u.lastname, u.mobile, u.email, u.address, eal.meter_status, eal.iot_meter, eal.iot_id \n" +
                "FROM users u\n" +
                "JOIN waccount_list eal ON u.nic = eal.nic\n" +
                "JOIN water_admin ON eal.region= water_admin.region WHERE water_admin.region = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,id);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            UserModel user = new UserModel();
            user.setNic(rs.getString("nic"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setMobile(rs.getString("mobile"));
            user.setEmail(rs.getString("email"));
            user.setConnectionStatus(rs.getString("meter_status"));
            user.setAddress(rs.getString("address"));
            user.setAccountNumber(rs.getString("account_number"));
            user.setIotMeter(rs.getString("iot_meter"));
            user.setIotId(rs.getString("iot_id"));

            users.add(user);
        }

        rs.close();
        stmt.close();
        connection.close();
        return users;
    }

    @Override
    public List<UserModel> getWaterDetailsByNICRegionalAdmin(String id, String searchValue) throws SQLException{
        Connection connection = Connectdb.getConnection();
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT eal.account_number, u.nic, u.firstname, u.lastname, u.mobile, u.email, u.address, eal.meter_status \n" +
                "FROM users u\n" +
                "JOIN waccount_list eal ON u.nic = eal.nic\n" +
                "JOIN water_admin ON eal.region= water_admin.region\n"+
                "WHERE water_admin.region = ? AND (eal.account_number LIKE ? OR u.nic LIKE ? OR u.firstname LIKE ? OR u.lastname LIKE ? OR u.address LIKE ?\n"+
                "OR u.mobile LIKE ? OR u.email LIKE ? OR eal.meter_status LIKE ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, "%" + searchValue + "%");
        stmt.setString(3, "%" + searchValue + "%");
        stmt.setString(4, "%" + searchValue + "%");
        stmt.setString(5, "%" + searchValue + "%");
        stmt.setString(6, "%" + searchValue + "%");
        stmt.setString(7, "%" + searchValue + "%");
        stmt.setString(8, "%" + searchValue + "%");
        stmt.setString(9, "%" + searchValue + "%");

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            UserModel user = new UserModel();
            user.setNic(rs.getString("nic"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setMobile(rs.getString("mobile"));
            user.setEmail(rs.getString("email"));
            user.setConnectionStatus(rs.getString("meter_status"));
            user.setAddress(rs.getString("address"));
            user.setAccountNumber(rs.getString("account_number"));

            users.add(user);
        }

        rs.close();
        stmt.close();
        connection.close();
        return users;
    }


    @Override
    public  void updateAccountStatus(String accountNumber, String newStatus) throws SQLException{
        Connection connection = Connectdb.getConnection();
        System.out.println("Account before exec: "+accountNumber);
        System.out.println("Status before exec: "+newStatus);

        try {
            String query = "UPDATE eaccount_list SET meter_status = ? WHERE account_number = ?";
            System.out.println("SQL Query: " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newStatus.toUpperCase());
            preparedStatement.setString(2, accountNumber);

            System.out.println("Prepared Statement Parameters:");
            System.out.println("  newStatus (uppercase): " + newStatus.toUpperCase());
            System.out.println("  accountNumber: " + accountNumber);

            preparedStatement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public  void updateWaterAccountStatus(String accountNumber, String newStatus) throws SQLException{
        Connection connection = Connectdb.getConnection();
        System.out.println("Account before exec: "+accountNumber);
        System.out.println("Status before exec: "+newStatus);

        try {
            String query = "UPDATE waccount_list SET meter_status = ? WHERE account_number = ?";
            System.out.println("SQL Query: " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newStatus.toUpperCase());
            preparedStatement.setString(2, accountNumber);

            System.out.println("Prepared Statement Parameters:");
            System.out.println("  newStatus (uppercase): " + newStatus.toUpperCase());
            System.out.println("  accountNumber: " + accountNumber);

            preparedStatement.executeUpdate();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }

    @Override
    public Integer getTotalElectricityAccountsRegion(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT account_number) AS total FROM eaccount_list WHERE region = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getTotalElectricityUsersRegion(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT nic) AS total FROM eaccount_list WHERE region = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getNewElectricityConnections(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT id) AS total FROM electricity_connection_request WHERE region = ? AND account_status != 'ADDED'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getNewElectricityComplaints(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT ec.complaint_no) AS total\n" +
                    "FROM electricity_complaint ec\n" +
                    "JOIN eaccount_list el ON ec.account_number = el.account_number\n" +
                    "WHERE el.region = ? AND ec.complaint_status != 'DONE'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getTotalWaterAccountsRegion(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT account_number) AS total FROM waccount_list WHERE region = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getTotalWaterUsersRegion(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT nic) AS total FROM waccount_list WHERE region = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getNewWaterConnections(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT id) AS total FROM water_connection_request WHERE region = ? AND account_status != 'ADDED'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }

    @Override
    public Integer getNewWaterComplaints(String region) throws SQLException {
        Connection connection = Connectdb.getConnection();
        Integer count = null;

        try {
            String query = "SELECT COUNT(DISTINCT ec.complaint_no) AS total\n" +
                    "FROM water_complaint ec\n" +
                    "JOIN waccount_list el ON ec.account_number = el.account_number\n" +
                    "WHERE el.region = ? AND ec.complaint_status != 'DONE'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } finally {
            Connectdb.closeConnection(connection);
        }
        return count;
    }


}
