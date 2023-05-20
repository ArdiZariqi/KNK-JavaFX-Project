package service.interfaces;

import Models.User;
import java.sql.SQLException;
import java.util.Date;

public interface UserServiceInterface {
    User login(String username, String password) throws SQLException;
    User signUp(String email, String accountType, String username, String password,
                String question, String answer, Date date, Date updateDate) throws SQLException;

    User getUserByUsername(String username) throws SQLException;
    void updateUser(User user) throws SQLException;
}
