package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;

public class DBConnectionManager {

    private final static Logger logger = LogManager.getLogger(DBConnectionManager.class);

    private static PropertyReader propertyReader;
    private static String dbName;
    private static String url;
    private static String user;
    private static String pass;
    private static String jdbcDriver;

    private static DBConnectionManager INSTANCE;
    public static Connection connection;

    private DBConnectionManager() throws IOException {

    }
    static {
        logger.info("Configuration properties are being loading..");
        propertyReader = new PropertyReader(Constant.APPLICATION_PROPERTY_FILE_NAME);
        try {
            dbName = propertyReader.get(Constant.DB_NAME).toString();
            url = propertyReader.get(Constant.JDBC_URL).toString()+ dbName;
            user = propertyReader.get(Constant.DB_USER).toString();
            pass = propertyReader.get(Constant.DB_PASSWORD).toString();
            jdbcDriver = propertyReader.get(Constant.JDBC_DRIVER).toString();
            logger.info("Database Connection is being created..");
            INSTANCE = new DBConnectionManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void connect() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(url, user, pass);
            logger.info("Connected to database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionManager getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (connection == null) {
            synchronized (DBConnectionManager.class) {
                if (connection == null) {
                    INSTANCE.connect();
                    logger.info("DB connection created..!");
                }
            }
        }
        return INSTANCE;
    }

    public static Connection getConnection() {
        if (connection == null) {
            INSTANCE.connect();
        }
        return connection;
    }

    public ResultSet executeQuery(String query) {
        if (this.connection == null) {
            INSTANCE.connect();
        }
        ResultSet resultSet = null;
        try {
            // Create the statement
            Statement statement = this.connection.createStatement();
            logger.debug(query);
            // Execute the query
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void disconnect() {
        try {
            // Check if the connection is established
            if (this.connection != null) {
                // Close the connection
                this.connection.close();
                logger.info("Disconnected from database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
