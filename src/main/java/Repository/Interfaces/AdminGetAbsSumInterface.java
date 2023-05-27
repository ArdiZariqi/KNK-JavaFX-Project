package Repository.Interfaces;

import Models.TotalAbsences;
import Models.getAbsenceSummary1;
import Models.studentData;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AdminGetAbsSumInterface {
    ObservableList<TotalAbsences> addStudentsListData2(LocalDate startDate,LocalDate endDate) throws SQLException;
}
