package DAO.dao;

import DAO.impl.AddressDetails;
import model.AddressModel;
import utils.DBConnectionManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AddressDetailsDAO implements AddressDetails {
    private DBConnectionManager dbConnectionManager;
    private static final String ADD_ADDRESS = "INSERT INTO address (street, city, state, country, zip_code, contact) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public Integer addAddress(AddressModel address) throws SQLException {
        Integer id = null;
        try {
            dbConnectionManager = DBConnectionManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(ADD_ADDRESS, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getState());
            preparedStatement.setString(4, address.getCountry());
            preparedStatement.setInt(5, address.getZipCode());
            preparedStatement.setString(6, address.getContact());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        }
        return id;
    }

    @Override
    public void updateAddress(AddressModel address) {
        return;
    }

    @Override
    public void deleteAddressById(Integer id) {
        return;
    }

    @Override
    public AddressModel selectAddressById(Integer id) {
        return null;
    }


}
