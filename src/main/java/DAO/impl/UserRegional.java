package DAO.impl;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface UserRegional {
    List<UModelRegional> getUsers() throws SQLException;
    List<ComplaintRAdmin> getComplaints() throws SQLException;

    String getPasswordById(String id) throws SQLException;

    UserRAdmin getUserDetailsById(String id) throws SQLException;

    UserRAdmin.Role getUserRoleById(String id) throws SQLException;

    String getPasswordSuperAdminById(String username) throws SQLException;

    UserRAdmin.Role getUserSuperAdminRoleById(String region) throws SQLException;

    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;
}
