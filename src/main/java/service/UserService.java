package service;

import Models.User;
import Models.dto.CreateUserDto;
import Repository.UserRepository;
import Repository.Interfaces.UserRepositoryInterface;
import service.interfaces.UserServiceInterface;

import java.sql.SQLException;
import java.util.Date;

public class UserService implements UserServiceInterface {
    // Repositories
    private UserRepositoryInterface userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User login(String username, String password) throws SQLException {
        User loginUser = userRepository.getByUsername(username);
        if (loginUser == null) {
            return null;
        }

        String salt = loginUser.getSalt();
        String saltedPassword = loginUser.getSaltedPassword();

        if (saltedPassword == null || salt == null) {
            return null;
        }

        boolean isPasswordCorrect = PasswordHasher.compareSaltedHash(password, salt, saltedPassword);
        if (isPasswordCorrect) {
            return loginUser;
        }

        return null;
    }

    public User signUp(String email, String accountType, String username, String password,
                       String question, String answer, Date date, Date updateDate) throws SQLException {
        String salt = PasswordHasher.generateSalt();
        String saltedHash = PasswordHasher.hashPassword(password, salt);
        CreateUserDto userDto = new CreateUserDto(email, accountType, username, saltedHash, salt,
                question, answer, date, updateDate);
        this.userRepository.insert(userDto);

        return new User(email, accountType, username, saltedHash, salt, question, answer, date, updateDate);
    }

    public User getUserByUsername(String username) throws SQLException {
        return userRepository.getByUsername(username);
    }

    public String getByAccountType(String username) throws SQLException {
        return userRepository.getAccountType(username);
    }

    public void updateUser(User user) throws SQLException {
        userRepository.update(user);
    }
}
