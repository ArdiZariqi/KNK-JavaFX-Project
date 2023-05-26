package Repository.Interfaces;

import Models.AbsenceSummary;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TeachAbsSumInterface {
    ObservableList<AbsenceSummary> addAbsencesListData1() throws SQLException;
}
