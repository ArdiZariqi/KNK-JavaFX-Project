package service.interfaces;

import Models.AbsenceData;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.util.Date;


public interface TeacherDashboardInterface {


        int getTotalAbsenceCount() throws SQLException;
        int getTotalFemaleAbsence() throws SQLException;
        int getTotalMaleAbsence() throws SQLException;
        XYChart.Series<String, Integer> getTotalAbsenceChart() throws SQLException;
        XYChart.Series<String, Integer> getFemaleAbsenceChart() throws SQLException;
        XYChart.Series<String, Integer> getMaleAbsenceChart() throws SQLException;
        void addAbsence(AbsenceData absenceData) throws  SQLException;
        void updateAbsence(AbsenceData absenceData) throws SQLException;

        void deleteAbsence(AbsenceData absenceData) throws SQLException;
        ObservableList<AbsenceData> AbsencesListData() throws SQLException;
}
