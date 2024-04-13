package DAO.impl;

import model.ComplaintRAdmin;
import model.UModelRegional;
import model.UserModel;
import model.UserRAdmin;

import java.sql.SQLException;
import java.util.List;

public interface UserRegional {
    List<UModelRegional> getUsers() throws SQLException;
    List<ComplaintRAdmin> getComplaints() throws SQLException;

    String getPasswordById(String id) throws SQLException;

    UserRAdmin getUserDetailsById(String id) throws SQLException;

    UserRAdmin.Role getUserRoleById(String id) throws SQLException;
}
