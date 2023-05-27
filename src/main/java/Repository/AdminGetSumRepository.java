package Repository;

import Models.TotalAbsences;
import Models.getAbsenceSummary1;
import Models.studentData;
import Repository.Interfaces.AdminGetAbsSumInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminGetSumRepository implements AdminGetAbsSumInterface {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private void closeDatabaseResources() {
        try {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<TotalAbsences> addStudentsListData2(LocalDate startDate, LocalDate endDate) throws SQLException {
        ObservableList<TotalAbsences> listStudents = FXCollections.observableArrayList();

        String sql = "CALL GetAbsenceSummary1(?, ?)";

        try {
            TotalAbsences studentD2;
            connect=ConnectionUtil.getConnection();
            prepare = connect.prepareStatement(sql);

            if (startDate != null && endDate != null) {
                java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
                java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

                prepare.setDate(1, sqlStartDate);
                prepare.setDate(2, sqlEndDate);

                result = prepare.executeQuery();

                while (result.next()) {
                    studentD2 = new TotalAbsences(
                            result.getInt("id"),
                            result.getString("year"),
                            result.getString("firstName"),
                            result.getString("lastName"),
                            result.getInt("total_reasonable_absences_forSemester"),
                            result.getInt("total_unreasonable_absences_forSemester"));

                    listStudents.add(studentD2);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudents;
    }
    public ObservableList<TotalAbsences> showStudentList() {
        ObservableList<TotalAbsences> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * from   AbsenceSummary1";

        try {
            TotalAbsences studentD1;
            connect= ConnectionUtil.getConnection();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD1 = new TotalAbsences(
                        result.getInt("id"),
                        result.getString("year"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getInt("total_reasonable_absences"),
                        result.getInt("total_unreasonable_absences"));

                listStudents.add(studentD1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return listStudents;
    }
}
