package DBconnection;

import java.sql.*;

public class Db {
    public static void main(String[] args) {
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/KNK_projekti",
                    "root",
                    "Ardit2304*"
            );

            // Execute a query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

            // Process the result set
            while (resultSet.next()) {
                // Do something with each row in the result set
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
