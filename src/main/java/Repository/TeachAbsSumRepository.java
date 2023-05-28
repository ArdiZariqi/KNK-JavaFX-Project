package Repository;

import Models.AbsenceSummary;
import Repository.Interfaces.TeachAbsSumInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachAbsSumRepository implements TeachAbsSumInterface {
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
    public ObservableList<AbsenceSummary> addAbsencesListData1() {
        ObservableList<AbsenceSummary> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * from   AbsenceSummary";

        try {
            AbsenceSummary studentD1;
            connect= ConnectionUtil.getConnection();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD1 = new AbsenceSummary(
                        result.getInt("id"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getInt("total_reasonable_absences"),
                        result.getInt("total_unreasonable_absences"),
                        result.getInt("total_absences"));

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
