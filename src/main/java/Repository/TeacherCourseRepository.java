package Repository;

import Repository.Interfaces.TeacherCourseInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherCourseRepository implements TeacherCourseInterface {

    public ObservableList<String> getCourseList() throws SQLException {
        Connection connect = ConnectionUtil.getConnection();
        String listCourse = "SELECT * FROM course";
        ObservableList<String> listC = FXCollections.observableArrayList();

        try (PreparedStatement prepare = connect.prepareStatement(listCourse);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                listC.add(result.getString("course"));
            }
        }
        return listC;
    }
}