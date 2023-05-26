package service;

import Models.AbsenceSummary;
import Repository.Interfaces.TeachAbsSumInterface;
import Repository.Interfaces.TeacherCourseInterface;
import Repository.TeachAbsSumRepository;
import Repository.TeacherCourseRepository;
import javafx.collections.ObservableList;
import service.interfaces.TeacherAbsenceSummaryInterface;

import java.sql.SQLException;

public class TeacherAbsenceSummary implements TeacherAbsenceSummaryInterface {
    private TeachAbsSumInterface teachAbsSumRepository;
    public TeacherAbsenceSummary() {
        this.teachAbsSumRepository = new TeachAbsSumRepository();
    }
    public ObservableList<AbsenceSummary> addAbsencesList1() throws SQLException {
        ObservableList<AbsenceSummary> listC=teachAbsSumRepository.addAbsencesListData1();
        return listC;
    }
}
