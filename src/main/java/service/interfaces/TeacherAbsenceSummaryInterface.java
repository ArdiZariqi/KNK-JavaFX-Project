package service.interfaces;

import Models.AbsenceSummary;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TeacherAbsenceSummaryInterface {

    ObservableList<AbsenceSummary> addAbsencesList1() throws SQLException;
}
