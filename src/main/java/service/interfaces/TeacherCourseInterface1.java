package service.interfaces;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TeacherCourseInterface1 {

    ObservableList<String> ListOfCourses() throws SQLException;
}
