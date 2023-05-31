package service.interfaces;


import Models.ScheduleData;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public interface ScheduleInterface {
    ObservableList<ScheduleData> schList() throws SQLException;
    void addSchedule(ScheduleData schData) throws  SQLException;
    boolean checkSch(String scheduleId) throws  SQLException;
    void updateSch(ScheduleData schData) throws SQLException;
    void deleteSch(ScheduleData schData) throws SQLException;
}
