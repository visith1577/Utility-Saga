package DAO.impl;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface WaterRegional {
    List<ElectricityAdminModel> getAdminsByNIC(String nic) throws SQLException;
}
