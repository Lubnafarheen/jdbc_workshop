package org.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "12345678";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/world";

    public static Connection getConnection() throws DBConnectionException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new DBConnectionException("Database connection failed");
        }

    }
}
