package service;
import Models.AbsenceData;
import Models.scheduleData;
import Repository.AdminScheduleRepository;
import Repository.Interfaces.AdminScheduleInterface;
import javafx.collections.ObservableList;
import service.interfaces.ScheduleInterface;

import java.sql.SQLException;

public class ScheduleService implements ScheduleInterface{
    private AdminScheduleInterface adminScheduleRepository;

    public ScheduleService() {
        this.adminScheduleRepository = new AdminScheduleRepository();
    }

    public ObservableList<scheduleData> schList() throws SQLException {
        ObservableList<scheduleData> listStudents =adminScheduleRepository.scheduleList();
        return listStudents;
    }

    public void addSchedule(scheduleData schData) throws SQLException {
        adminScheduleRepository.ScheduleAdd(schData);
    }
    public boolean checkSch(String scheduleId) throws SQLException {
            return adminScheduleRepository.checkSchedule(scheduleId);

    }
    public void updateSch(scheduleData schData) throws SQLException {
        adminScheduleRepository.ScheduleUpdate(schData);
    }
    public void deleteSch(scheduleData schData) throws SQLException {
        adminScheduleRepository.ScheduleDelete(schData);
    }

}
