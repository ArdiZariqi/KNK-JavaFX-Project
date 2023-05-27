package service;

import Models.AbsenceData;
import Models.studentData;
import Repository.AdminRepository;
import Repository.Interfaces.AdminInterface;
import Repository.Interfaces.TeacherUserInterface;
import Repository.TeacherRepository;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import service.interfaces.AdminUserInterface;

import java.sql.SQLException;

public class AdminUserService implements AdminUserInterface {
    private AdminInterface adminRepository;

    public AdminUserService() {
        this.adminRepository = new AdminRepository();
    }

    public int getTotalStudentCount() throws SQLException {
        return  adminRepository.homeDisplayTotalEnrolledStudents();
    }
    public int getTotalFemaleStudent() throws SQLException{
        return adminRepository.homeDisplayFemaleEnrolled();
    }
    public int getTotalMaleStudent() throws SQLException{
        return adminRepository.homeDisplayMaleEnrolled();

    }
    public XYChart.Series<String, Integer> getTotalStudentChart() throws SQLException {
        return adminRepository.homeDisplayTotalEnrolledChart();
    }
    public XYChart.Series<String, Integer> getFemaleStudentChart() throws SQLException {
        return adminRepository.homeDisplayFemaleEnrolledChart();
    }

    public XYChart.Series<String, Integer> getMaleStudentChart() throws SQLException {
        return adminRepository.homeDisplayEnrolledMaleChart();
    }

    public void addStd(studentData sData) throws SQLException {
        adminRepository.StudentsAdd(sData);
    }
    public void updateStd(studentData sData) throws SQLException{
        adminRepository.addStudentsUpdate(sData);
    }
    public void deleteStd(studentData sData) throws SQLException{
        adminRepository.addStudentsDelete(sData);
    }
    public ObservableList<studentData> StdListData() throws SQLException {
        ObservableList<studentData> listStudents = adminRepository.addStudentsListData();
        return listStudents;
    }
}
