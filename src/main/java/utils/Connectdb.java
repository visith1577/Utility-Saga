package utils;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class Connectdb {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/utilitySaga?useSSL=false";
    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            return DriverManager.getConnection(JDBC_URL, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}
