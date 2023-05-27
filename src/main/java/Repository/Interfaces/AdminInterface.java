package Repository.Interfaces;

import Models.AbsenceData;
import Models.studentData;
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
    void StudentsAdd(studentData sData) throws SQLException;
    void addStudentsUpdate(studentData sData) throws SQLException;
    void addStudentsDelete(studentData sData) throws SQLException;
    ObservableList<studentData> addStudentsListData() throws SQLException;
}
