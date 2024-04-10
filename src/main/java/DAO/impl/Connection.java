package DAO.impl;

import model.ConnectionModel;

import java.sql.SQLException;

public interface Connection {
    void saveConnection(ConnectionModel connection) throws SQLException;
}
