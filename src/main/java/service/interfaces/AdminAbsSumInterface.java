package service.interfaces;

import Models.TotalAbsences;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AdminAbsSumInterface {
    ObservableList<TotalAbsences> StdListData2(LocalDate startDate, LocalDate endDate) throws SQLException;
    ObservableList<TotalAbsences> showStdList() throws SQLException;
}
