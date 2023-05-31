package Repository.Interfaces;

import Models.TotalAbsences;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AdminGetAbsSumInterface {
    ObservableList<TotalAbsences> addStudentsListData2(LocalDate startDate,LocalDate endDate) throws SQLException;
    ObservableList<TotalAbsences> showStudentList() throws SQLException;
}
