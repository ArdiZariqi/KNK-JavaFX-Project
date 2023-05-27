package Repository.Interfaces;

import Models.AbsenceData;
import Models.TotalAbsences;
import Models.scheduleData;
import Models.studentData;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AdminScheduleInterface {
    ObservableList<scheduleData> scheduleList() throws SQLException;
    void ScheduleAdd(scheduleData schData) throws SQLException;
    boolean checkSchedule(String scheduleId) throws SQLException;
    void ScheduleUpdate(scheduleData schData) throws SQLException;
    void ScheduleDelete(scheduleData schData) throws SQLException;
}
