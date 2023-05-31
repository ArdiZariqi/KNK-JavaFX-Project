package Repository.Interfaces;

import Models.ScheduleData;
import Models.StudentData;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface AdminInterface {
    int homeDisplayTotalEnrolledStudents() throws SQLException;
    int homeDisplayFemaleEnrolled() throws SQLException;
    int homeDisplayMaleEnrolled() throws SQLException;
    XYChart.Series<String, Integer> homeDisplayTotalEnrolledChart() throws SQLException;
    XYChart.Series<String, Integer> homeDisplayFemaleEnrolledChart() throws SQLException;
    XYChart.Series<String, Integer> homeDisplayEnrolledMaleChart() throws SQLException;
    void StudentsAdd(StudentData sData) throws SQLException;
    void addStudentsUpdate(StudentData sData) throws SQLException;
    void addStudentsDelete(StudentData sData) throws SQLException;
    ObservableList<StudentData> addStudentsListData() throws SQLException;
    StudentData getById(Integer id) throws SQLException;
    ScheduleData getScheduleById(String schedule_id) throws SQLException;
}
