package DAO.impl;

import model.UModelRegional;

import java.sql.SQLException;
import java.util.List;

public interface UserModelRegional {
    List<UModelRegional> getUsers() throws SQLException;
}
