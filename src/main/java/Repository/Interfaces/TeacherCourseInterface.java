package Repository.Interfaces;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TeacherCourseInterface {
    default ObservableList<String> getCourseList() throws SQLException {
        return null;
    }
}
