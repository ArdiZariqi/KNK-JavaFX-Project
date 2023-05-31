package service.interfaces;

import Models.ScheduleData;
import Models.StudentData;
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
    void addStd(StudentData sData) throws  SQLException;
    void updateStd(StudentData sData) throws SQLException;
    void deleteStd(StudentData sData) throws SQLException;
    ObservableList<StudentData> StdListData() throws SQLException;
    StudentData getById(Integer id) throws SQLException;
    ScheduleData getScheduleById(String id) throws SQLException;
}
