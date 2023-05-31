package service;

import Models.ScheduleData;
import Models.StudentData;
import Repository.AdminRepository;
import Repository.Interfaces.AdminInterface;
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

    public void addStd(StudentData sData) throws SQLException {
        adminRepository.StudentsAdd(sData);
    }
    public void updateStd(StudentData sData) throws SQLException{
        adminRepository.addStudentsUpdate(sData);
    }
    public void deleteStd(StudentData sData) throws SQLException{
        adminRepository.addStudentsDelete(sData);
    }
    public ObservableList<StudentData> StdListData() throws SQLException {
        ObservableList<StudentData> listStudents = adminRepository.addStudentsListData();
        return listStudents;
    }

    public StudentData getById(Integer id) throws SQLException {
        return adminRepository.getById(id);
    }

    public ScheduleData getScheduleById(String schedule_id) throws SQLException {
        return adminRepository.getScheduleById(schedule_id);
    }
}
