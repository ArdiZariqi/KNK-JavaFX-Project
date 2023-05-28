package Repository;

import Models.AbsenceData;
import Models.scheduleData;
import Repository.Interfaces.TeacherUserInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import service.ConnectionUtil;

import java.sql.*;
import java.util.Optional;

public class TeacherRepository implements TeacherUserInterface {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public int getTotalStudentsAbsenceCount() throws SQLException {
        String sql = "SELECT COUNT(a_id) FROM student_Abstence";
        connect = ConnectionUtil.getConnection();
        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return countEnrolled;
    }

    @Override
    public int getTotalFemaleAbsenceCount() throws SQLException {
        String sql = "SELECT COUNT(a_id) FROM student_Abstence sA INNER JOIN " +
                "student s on sA.student_id = s.id WHERE gender = 'Female' and status = 'Enrolled'";
        connect = ConnectionUtil.getConnection();
        int countFemale = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return countFemale;
    }

    @Override
    public int getTotalMaleAbsenceCount() throws SQLException {
        String sql = "SELECT COUNT(a_id) FROM student_Abstence sA INNER JOIN " +
                "student s on sA.student_id = s.id WHERE gender = 'Male' and status = 'Enrolled'";
        connect = ConnectionUtil.getConnection();
        int countMale = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return countMale;
    }

    @Override
    public XYChart.Series<String, Integer> getTotalAbsenceChartData() throws SQLException {
        String sql = "SELECT sA.date_, COUNT(sA.a_id) FROM student_Abstence sA " +
                "INNER JOIN student s on sA.student_id = s.id" +
                " WHERE s.status = 'Enrolled' GROUP BY sA.date_ ORDER BY TIMESTAMP(sA.date_) ASC LIMIT 5";
        connect = ConnectionUtil.getConnection();
        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return chart;
    }

    @Override
    public XYChart.Series<String, Integer> getFemaleAbsenceChartData() throws SQLException {
        String sql = "SELECT sA.date_, COUNT(sA.a_id) FROM student_Abstence sA " +
                "INNER JOIN student s on sA.student_id = s.id " +
                "WHERE s.status = 'Enrolled' and s.gender = 'Female' " +
                "GROUP BY sA.date_ ORDER BY TIMESTAMP(sA.date_) ASC LIMIT 5";
        connect = ConnectionUtil.getConnection();
        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return chart;
    }

    @Override
    public XYChart.Series<String, Integer> getMaleAbsenceChartData() throws SQLException {
        String sql = "SELECT sA.date_, COUNT(sA.a_id) FROM student_Abstence sA " +
                "INNER JOIN student s on sA.student_id = s.id " +
                "WHERE s.status = 'Enrolled' and s.gender = 'Male' " +
                "GROUP BY sA.date_ ORDER BY TIMESTAMP(sA.date_) ASC LIMIT 5";
        connect = ConnectionUtil.getConnection();
        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }

        return chart;
    }

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

    public void AbsencesAdd(AbsenceData absenceData) throws SQLException {
        String insertData = "INSERT INTO student_Abstence " +
                "(student_id, schedule_id, date_, reasonability) " +
                "VALUES (?, ?, ?, ?)";

        connect = ConnectionUtil.getConnection();

        try {
            prepare = connect.prepareStatement(insertData);
            prepare.setInt(1, absenceData.getStudent_id());
            prepare.setInt(2, absenceData.getSchedule_id());
            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            prepare.setDate(3, sqlDate);
            prepare.setString(4, absenceData.getReasonability());

            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    public void addAbsencesUpdate(AbsenceData absenceData) {
        String updateData = "UPDATE student_Abstence SET "
                + "student_id = ?"
                + ", schedule_id = ?"
                + ", date_ = ?"
                + ", reasonability = ?"
                + " where a_id = ?";

        connect = ConnectionUtil.getConnection();

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setInt(1, absenceData.getStudent_id());
            prepare.setInt(2, absenceData.getSchedule_id());
            prepare.setDate(3, absenceData.getDate_());
            prepare.setString(4, absenceData.getReasonability());
            prepare.setInt(5, absenceData.getA_id());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }

    public void addAbsencesDelete(AbsenceData absenceData) {
        String deleteData = "DELETE FROM student_Abstence WHERE a_id = ?";

        connect = ConnectionUtil.getConnection();

        try {
            PreparedStatement statement = connect.prepareStatement(deleteData);
            statement.setInt(1, absenceData.getA_id()); // Set the value of a_id parameter
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }
    public ObservableList<AbsenceData> addAbsencesListData() {
        ObservableList<AbsenceData> listStudents = FXCollections.observableArrayList();

        String absences = "SELECT sA.a_id, s.id, s.firstName, s.year, " +
                "s.lastName, sc.course, sc.time, sc.day, sA.date_, sA.reasonability " +
                "FROM student_Abstence sA " +
                "INNER JOIN student s ON sA.student_id = s.id " +
                "INNER JOIN schedule sc ON sA.schedule_id = sc.schedule_id";

        try {
            AbsenceData studentD;
            connect=ConnectionUtil.getConnection();
            prepare = connect.prepareStatement(absences);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new AbsenceData(
                        result.getInt("a_id"),
                        result.getInt("id"),
                        result.getString("year"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("course"),
                        result.getString("time"),
                        result.getString("day"),
                        result.getDate("date_"),
                        result.getString("reasonability"));

                listStudents.add(studentD);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    @Override
    public AbsenceData getAbsenceById(Integer absence_id) throws SQLException {
        String sql = "SELECT * FROM student_Abstence WHERE a_id=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, absence_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new AbsenceData(absence_id);
            } else {
                return null;
            }
        }
    }

}


