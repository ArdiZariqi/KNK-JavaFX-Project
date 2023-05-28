package service;

import Models.AbsenceData;
import Repository.Interfaces.TeacherUserInterface;
import Repository.TeacherRepository;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import service.interfaces.TeacherDashboardInterface;
import java.sql.SQLException;

public class TeacherDashboardService implements TeacherDashboardInterface {
    private TeacherUserInterface teacherRepository;

    public TeacherDashboardService() {
        this.teacherRepository = new TeacherRepository();
    }

    public int getTotalAbsenceCount() throws SQLException{
        return  teacherRepository.getTotalStudentsAbsenceCount();
    }
    public int getTotalFemaleAbsence() throws SQLException{
        return teacherRepository.getTotalFemaleAbsenceCount();
    }
    public int getTotalMaleAbsence() throws SQLException{
        return teacherRepository.getTotalMaleAbsenceCount();

    }
    public XYChart.Series<String, Integer> getTotalAbsenceChart() throws SQLException {
        return teacherRepository.getTotalAbsenceChartData();
    }
    public XYChart.Series<String, Integer> getFemaleAbsenceChart() throws SQLException {
        return teacherRepository.getFemaleAbsenceChartData();
    }

    public XYChart.Series<String, Integer> getMaleAbsenceChart() throws SQLException {
        return teacherRepository.getMaleAbsenceChartData();
    }


    public void addAbsence(AbsenceData absenceData) throws SQLException {
        teacherRepository.AbsencesAdd(absenceData);
    }

    public void updateAbsence(AbsenceData absenceData) throws SQLException{
        teacherRepository.addAbsencesUpdate(absenceData);
    }

    public void deleteAbsence(AbsenceData absenceData) throws SQLException{
        teacherRepository.addAbsencesDelete(absenceData);
    }

    public ObservableList<AbsenceData> AbsencesListData() throws SQLException {
        ObservableList<AbsenceData> listStudents = teacherRepository.addAbsencesListData();
        return listStudents;
    }

    public AbsenceData getAbsenceById(Integer absence_id) throws SQLException {
        return teacherRepository.getAbsenceById(absence_id);
    }
}
