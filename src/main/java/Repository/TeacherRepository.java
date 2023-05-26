package Repository;

import Models.AbsenceData;
import Repository.Interfaces.TeacherUserInterface;
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
        String sql = "SELECT COUNT(a_id) FROM student_Abstence WHERE gender = 'Female' and status = 'Enrolled'";
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
        String sql = "SELECT COUNT(a_id) FROM student_Abstence WHERE gender = 'Male' and status = 'Enrolled'";
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
        String sql = "SELECT date_, COUNT(a_id) FROM student_Abstence WHERE status = 'Enrolled' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";
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
        String sql = "SELECT date_, COUNT(a_id) FROM student_Abstence WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";
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
        String sql = "SELECT date_, COUNT(a_id) FROM student_Abstence WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";
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
                "(student_id, class_, course_name, time, firstName, lastName, gender, date_, status, reasonability, date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = ConnectionUtil.getConnection();

        try {
            prepare = connect.prepareStatement(insertData);
            prepare.setInt(1, absenceData.getStudent_id());
            prepare.setString(2, absenceData.getClass_());
            prepare.setString(3, absenceData.getCourse_name());
            prepare.setInt(4, absenceData.getTime());
            prepare.setString(5, absenceData.getFirstName());
            prepare.setString(6, absenceData.getLastName());
            prepare.setString(7, absenceData.getGender());
            prepare.setDate(8, absenceData.getDate_());
            prepare.setString(9, absenceData.getStatus());
            prepare.setString(10, absenceData.getReasonability());

            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            prepare.setDate(11, sqlDate);

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
                + ", class_ = ?"
                + ", course_name = ?"
                + ", time = ?"
                + ", firstName = ?"
                + ", lastName = ?"
                + ", gender = ?"
                + ", date_ = ?"
                + ", status = ?"
                + ", reasonability = ?"
                + " where a_id = ?";

        connect = ConnectionUtil.getConnection();

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setInt(1, absenceData.getStudent_id());
            prepare.setString(2, absenceData.getClass_());
            prepare.setString(3, absenceData.getCourse_name());
            prepare.setInt(4, absenceData.getTime());
            prepare.setString(5, absenceData.getFirstName());
            prepare.setString(6, absenceData.getLastName());
            prepare.setString(7, absenceData.getGender());
            prepare.setDate(8, absenceData.getDate_());
            prepare.setString(9, absenceData.getStatus());
            prepare.setString(10, absenceData.getReasonability());
            prepare.setInt(11, absenceData.getA_id());
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
}


