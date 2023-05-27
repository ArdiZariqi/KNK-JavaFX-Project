package service.interfaces;


import Models.AbsenceData;
import Models.scheduleData;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public interface ScheduleInterface {
    ObservableList<scheduleData> schList() throws SQLException;
    void addSchedule(scheduleData schData) throws  SQLException;
    boolean checkSch(String scheduleId) throws  SQLException;
    void updateSch(scheduleData schData) throws SQLException;
    void deleteSch(scheduleData schData) throws SQLException;
}
