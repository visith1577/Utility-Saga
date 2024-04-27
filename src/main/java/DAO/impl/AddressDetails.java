package DAO.impl;

import model.AddressModel;

import java.sql.SQLException;
import java.util.List;

public interface AddressDetails {
    Integer addAddress(AddressModel address) throws SQLException;
    void updateAddress(AddressModel address) throws SQLException;
    void deleteAddressById(Integer id) throws SQLException;
    AddressModel selectAddressById(Integer id) throws SQLException;


}
