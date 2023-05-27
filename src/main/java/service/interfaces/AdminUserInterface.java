package service.interfaces;

import Models.AbsenceData;
import Models.studentData;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface AdminUserInterface {
    int getTotalStudentCount() throws SQLException;
    int getTotalFemaleStudent() throws SQLException;
    int getTotalMaleStudent() throws SQLException;
    XYChart.Series<String, Integer> getTotalStudentChart() throws SQLException;
    XYChart.Series<String, Integer> getFemaleStudentChart() throws SQLException;
    XYChart.Series<String, Integer> getMaleStudentChart() throws SQLException;
    void addStd(studentData sData) throws  SQLException;
    void updateStd(studentData sData) throws SQLException;
    void deleteStd(studentData sData) throws SQLException;
    ObservableList<studentData> StdListData() throws SQLException;
}
