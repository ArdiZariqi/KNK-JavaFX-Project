package Repository.Interfaces;
import Models.AbsenceData;
import Models.scheduleData;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface TeacherUserInterface {
        int getTotalStudentsAbsenceCount() throws SQLException;
        int getTotalFemaleAbsenceCount() throws SQLException;
        int getTotalMaleAbsenceCount() throws SQLException;
        XYChart.Series<String, Integer> getTotalAbsenceChartData() throws SQLException;
        XYChart.Series<String, Integer> getFemaleAbsenceChartData() throws SQLException;
        XYChart.Series<String, Integer> getMaleAbsenceChartData() throws SQLException;
        void AbsencesAdd(AbsenceData absenceData) throws SQLException;
        void addAbsencesUpdate(AbsenceData absenceData) throws SQLException;
        void addAbsencesDelete(AbsenceData absenceData) throws SQLException;
        ObservableList<AbsenceData> addAbsencesListData() throws SQLException;
        AbsenceData getAbsenceById(Integer absence_id) throws SQLException;
}
