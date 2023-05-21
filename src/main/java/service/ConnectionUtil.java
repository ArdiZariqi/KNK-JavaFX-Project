package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() {
        String databaseName = "knk_projekti";
        String databaseUser = "root";
        String databasePassword = "allahisone00";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }
}