package service;

import Models.TotalAbsences;
import Models.studentData;
import Repository.AdminGetSumRepository;
import Repository.AdminRepository;
import Repository.Interfaces.AdminGetAbsSumInterface;
import Repository.Interfaces.AdminInterface;
import javafx.collections.ObservableList;
import service.interfaces.AdminAbsSumInterface;

import java.sql.SQLException;
import java.time.LocalDate;

public class AdminAbsSumService implements AdminAbsSumInterface {
    private AdminGetAbsSumInterface adminGetSumRepository;

    public AdminAbsSumService() {
        this.adminGetSumRepository = new AdminGetSumRepository();
    }
    public ObservableList<TotalAbsences> StdListData2(LocalDate startDate,LocalDate endDate) throws SQLException {
        ObservableList<TotalAbsences> listStudents = adminGetSumRepository.addStudentsListData2(startDate,endDate);
        return listStudents;
    }

    public ObservableList<TotalAbsences> showStdList() throws SQLException {
        ObservableList<TotalAbsences> listStudents = adminGetSumRepository.showStudentList();
        return listStudents;
    }
}
