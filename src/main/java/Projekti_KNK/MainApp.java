package Projekti_KNK;

import service.ConnectionUtil;

import java.sql.*;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = 1";
        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, 2);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            System.out.println(results.getString(2));
        }
    }
}