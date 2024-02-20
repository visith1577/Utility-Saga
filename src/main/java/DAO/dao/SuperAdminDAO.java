package DAO.dao;

import model.SuperAdmineditAdmin;
import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuperAdminDAO implements DAO.impl.SuperAdmineditAdminImpl {

    @Override
    public void createElecAdmin(SuperAdmineditAdmin admin) throws SQLException {
        Connection con= Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO electricity_admin (firstname,lastname, title, id, pwd) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, admin.getElecfirstName());
        stmt.setString(2, admin.getEleclastName());
        stmt.setString(3, admin.getElecTitle());
        stmt.setString(4, admin.getElecId());
        stmt.setString(5, admin.getElecPass());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void deleteElecAdmin(String elecId) throws SQLException {
        Connection con = Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM electricity_admin WHERE id = ?");
        stmt.setString(1, elecId);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void updateElecAdmin(SuperAdmineditAdmin admin) throws SQLException {
        Connection conn = Connectdb.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE electricity_admin SET firstname = ?, lastname = ?, title = ?, pwd = ? WHERE id = ?");
        stmt.setString(1, admin.getElecfirstName());
        stmt.setString(2, admin.getEleclastName());
        stmt.setString(3, admin.getElecTitle());
        stmt.setString(4, admin.getElecId());
        stmt.setString(5, admin.getElecPass());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public void createWaterAdmin(SuperAdmineditAdmin admin) throws SQLException {
        Connection con= Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO water_admin (firstname,lastname, title, id, pwd) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, admin.getWaterfirstName());
        stmt.setString(2, admin.getWaterlastName());
        stmt.setString(3, admin.getWaterTitle());
        stmt.setString(4, admin.getWaterId());
        stmt.setString(5, admin.getWaterPass());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void deleteWaterAdmin(String waterId) throws SQLException {
        Connection con = Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM water_admin WHERE id = ?");
        stmt.setString(1, waterId);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void updateWaterAdmin(SuperAdmineditAdmin admin) throws SQLException {
        Connection conn = Connectdb.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE water_admin SET firstname = ?, lastname = ?, title = ?, pwd = ? WHERE id = ?");
        stmt.setString(1, admin.getWaterfirstName());
        stmt.setString(2, admin.getWaterlastName());
        stmt.setString(3, admin.getWaterTitle());
        stmt.setString(4, admin.getWaterId());
        stmt.setString(5, admin.getWaterPass());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public void createSolarComp(SuperAdmineditAdmin admin) throws SQLException {
        Connection con= Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO solar_company (compId,name, landPhone, uname, pwd) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, admin.getSolarId());
        stmt.setString(2, admin.getSolarName());
        stmt.setString(3, admin.getSolarlandPhone());
        stmt.setString(4, admin.getSolaruName());
        stmt.setString(5, admin.getSolarPass());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void deleteSolarComp(String solarId) throws SQLException {
        Connection con = Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM water_admin WHERE id = ?");
        stmt.setString(1, solarId);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void updateSolarComp(SuperAdmineditAdmin admin) throws SQLException {
        Connection con= Connectdb.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE solar_company SET compId = ?, name = ?, landPhone = ?, uname = ?, pwd = ? WHERE compId = ?");
        stmt.setString(1, admin.getSolarId());
        stmt.setString(2, admin.getSolarName());
        stmt.setString(3, admin.getSolarlandPhone());
        stmt.setString(4, admin.getSolaruName());
        stmt.setString(5, admin.getSolarPass());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
}