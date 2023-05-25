package Repository;

import Models.dto.CreateUserDto;
import Models.User;
import Repository.Interfaces.UserRepositoryInterface;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserRepository implements UserRepositoryInterface {

    public User insert(CreateUserDto user) throws SQLException {
        String sql = "INSERT INTO users (email, account_type, username, saltedPassword, salt, question, answer, date_) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getAccountType());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getSaltedPassword());
            statement.setString(5, user.getSalt());
            statement.setString(6, user.getQuestion());
            statement.setString(7, user.getAnswer());
            statement.setDate(8, new java.sql.Date(user.getDate().getTime()));
            statement.executeUpdate();
            return getByUsername(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String accountType = resultSet.getString("account_type");
                String saltedPassword = resultSet.getString("saltedPassword");
                String salt = resultSet.getString("salt");
                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");
                Date date = resultSet.getDate("date_");
                Date updateDate = resultSet.getDate("update_date");

                statement.executeQuery();

                return new User(email, accountType, username, saltedPassword, salt, question, answer, date, updateDate);

            } else {
                return null;
            }
        }
    }

    public String getAccountType(String username) throws SQLException {
        String sql = "SELECT account_type FROM users WHERE username=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("account_type");
            } else {
                return null;
            }
        }
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET saltedPassword=?, salt=?, update_date=? WHERE username=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getSaltedPassword());
            statement.setString(2, user.getSalt());
            statement.setDate(3, new java.sql.Date(user.getDate().getTime()));
            statement.setString(4,user.getUsername());
            statement.executeUpdate();
        }
    }
}