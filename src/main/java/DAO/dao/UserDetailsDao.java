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
}
