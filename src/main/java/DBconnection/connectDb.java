package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
public class connectDb {
    public Connection getConnection() {
        String databaseName = "knk_projekti";
        String databaseUser = "root";
        String databasePassword = "Abz130203?!$";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, databaseUser, databasePassword);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return null;
        }

    }
}
