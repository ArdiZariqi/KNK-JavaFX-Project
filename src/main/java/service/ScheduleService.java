package service;

import Models.ScheduleData;
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

    public ObservableList<ScheduleData> schList() throws SQLException {
        ObservableList<ScheduleData> listStudents =adminScheduleRepository.scheduleList();
        return listStudents;
    }

    public void addSchedule(ScheduleData schData) throws SQLException {
        adminScheduleRepository.ScheduleAdd(schData);
    }
    public boolean checkSch(String scheduleId) throws SQLException {
            return adminScheduleRepository.checkSchedule(scheduleId);

    }
    public void updateSch(ScheduleData schData) throws SQLException {
        adminScheduleRepository.ScheduleUpdate(schData);
    }
    public void deleteSch(ScheduleData schData) throws SQLException {
        adminScheduleRepository.ScheduleDelete(schData);
    }

}
