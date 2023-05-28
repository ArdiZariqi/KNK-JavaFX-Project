package service;

import Repository.Interfaces.TeacherCourseInterface;
import Repository.TeacherCourseRepository;
import javafx.collections.ObservableList;
import service.interfaces.TeacherCourseInterface1;

import java.sql.SQLException;

public class TeacherCourseService implements TeacherCourseInterface1 {
    private TeacherCourseInterface teacherCourseRepository;
    public TeacherCourseService() {
        this.teacherCourseRepository = new TeacherCourseRepository();
    }

    public ObservableList<String> ListOfCourses() throws SQLException {
        ObservableList<String> listC=teacherCourseRepository.getCourseList();
        return listC;
    }
}
