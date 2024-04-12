package DAO.dao;

import utils.Connectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class ReadingSaver implements Runnable{

    private final AtomicReference<String> latestReading;

    public ReadingSaver(AtomicReference<String> latestReading) throws SQLException {
        this.latestReading = latestReading;
    }

    @Override
    public void run() {
        String reading = latestReading.getAndSet(null);
        if (reading != null) {
            try {
                saveReadingToDatabase(reading);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            latestReading.set(null);
        }
    }

    private void saveReadingToDatabase(String reading) throws SQLException {
        Connection connection = Connectdb.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO meter_reader(value) VALUES (?)");

            stmt.setString(1, reading);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectdb.closeConnection(connection);
        }
    }
}
