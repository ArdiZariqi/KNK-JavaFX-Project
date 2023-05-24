package Repository.Interfaces;

import Models.User;
import Models.dto.CreateUserDto;

import java.sql.SQLException;

public interface UserRepositoryInterface {
    User insert(CreateUserDto user) throws SQLException;
    User getByUsername(String username) throws SQLException;
    void update(User user) throws SQLException;
}
