package service.interfaces;

import Models.courseData;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TeacherCourseInterface1 {

    ObservableList<String> ListOfCourses() throws SQLException;
}
