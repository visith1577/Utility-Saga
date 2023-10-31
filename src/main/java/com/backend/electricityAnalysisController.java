package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import backend.models.auth.electricityAnalysis;
public class electricityAnalysisController {
    private String jdbcURL = "jdbc:mysql://localhost:3306/utilitysaga?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Eranda2001";

    private static final String INSERT_ENTRY_SQL = "INSERT INTO entry  (name, start, end, units) VALUES  (?, ?, ?, ?);";
    private static final String SELECT_ENTRY_BY_ID = "select id,name, start, end, units from entry where id =?";
    private static final String SELECT_ALL_ENTRIES = "select * from entry";
    private static final String DELETE_ENTRY_SQL = "delete from entry where id = ?;";
    private static final String UPDATE_ENTRY_SQL = "update users set name = ?, start= ?, end= ?, units= ?;";
    private Connection connection;

    public electricityAnalysisController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
        } catch (SQLException var3) {
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }
    }

    public void insertUser(electricityAnalysis entryobj) throws SQLException {
        System.out.println("INSERT INTO users  (name, start, end, units) VALUES  (?, ?, ?, ?);");

        try {
            Throwable var2 = null;
            Object var3 = null;

            try {
                Connection connection = this.getConnection();

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENTRY_SQL);

                    try {
                        preparedStatement.setString(1, entryobj.getBudgetName());
                        preparedStatement.setDate(2, entryobj.getStart().getTime());
                        preparedStatement.setDate(3, entryobj.getEnd().getTime());
                        preparedStatement.setInt(4, entryobj.getUnits());
                        System.out.println(preparedStatement);
                        preparedStatement.executeUpdate();
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }

                    }
                } catch (Throwable var19) {
                    if (var2 == null) {
                        var2 = var19;
                    } else if (var2 != var19) {
                        var2.addSuppressed(var19);
                    }

                    if (connection != null) {
                        connection.close();
                    }

                    throw var2;
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Throwable var20) {
                if (var2 == null) {
                    var2 = var20;
                } else if (var2 != var20) {
                    var2.addSuppressed(var20);
                }

                throw var2;
            }
        } catch (SQLException var21) {
        }

    }
}



