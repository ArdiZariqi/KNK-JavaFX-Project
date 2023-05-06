
package KNK_Projekti;

import Repository.UserRepository;
import Models.User;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) {
        User user = new User("Aaaaa", "Bbbb", 19);
        try {
            UserRepository.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
