package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
public class connectDb {
    public Connection getConnection() {
        String databaseName = "knk_projekti";
        String databaseUser = "root";
        String databasePassword = "Abz130203?!$";
        String url = "jdbc:mysql://localhost/" + databaseName;
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return connect;
    }
}
