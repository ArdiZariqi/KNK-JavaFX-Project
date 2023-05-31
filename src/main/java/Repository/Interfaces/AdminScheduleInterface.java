package Repository.Interfaces;

import Models.ScheduleData;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface AdminScheduleInterface {
    ObservableList<ScheduleData> scheduleList() throws SQLException;
    void ScheduleAdd(ScheduleData schData) throws SQLException;
    boolean checkSchedule(String scheduleId) throws SQLException;
    void ScheduleUpdate(ScheduleData schData) throws SQLException;
    void ScheduleDelete(ScheduleData schData) throws SQLException;
}
