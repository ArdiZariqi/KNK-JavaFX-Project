package Repository;

import Models.ScheduleData;
import Repository.Interfaces.AdminScheduleInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ConnectionUtil;

import java.sql.*;

public class AdminScheduleRepository implements AdminScheduleInterface {
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

    public ObservableList<ScheduleData> scheduleList() throws SQLException {
        ObservableList<ScheduleData> listStudents = FXCollections.observableArrayList();

        String absences = "SELECT *from schedule";

        try {
            ScheduleData studentD;
            connect=ConnectionUtil.getConnection();
            prepare = connect.prepareStatement(absences);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new ScheduleData(
                        result.getString("schedule_id"),
                        result.getString("day"),
                        result.getString("time"),
                        result.getString("course"));

                listStudents.add(studentD);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudents;
    }
    public void ScheduleAdd(ScheduleData schData) throws SQLException {
        String insertData = "INSERT INTO schedule" +"(schedule_id, day, time, course)" +"VALUES(?,?,?,?)";

        connect = ConnectionUtil.getConnection();

        try {
            prepare = connect.prepareStatement(insertData);
            prepare.setString(1, schData.getSchedule_id());
            prepare.setString(2, schData.getDay());
            prepare.setString(3, schData.getTime());
            prepare.setString(4, schData.getCourse());

            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }
    public boolean checkSchedule(String scheduleId) throws SQLException {
        String checkData = "SELECT schedule_id FROM schedule WHERE schedule_id = ?";
        connect = ConnectionUtil.getConnection();
        PreparedStatement statement = connect.prepareStatement(checkData);
        statement.setString(1, scheduleId);
        ResultSet resultSet = statement.executeQuery();
        boolean scheduleExists = resultSet.next();
        resultSet.close();
        statement.close();
        return scheduleExists;
    }

    public void ScheduleUpdate(ScheduleData schData){
        String updateData = "UPDATE schedule SET "
                + "day = ?"
                + ",time = ?"
                + ",course = ?"
                + " where schedule_id = ?";

        connect = ConnectionUtil.getConnection();

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, schData.getDay());
            prepare.setString(2, schData.getTime());
            prepare.setString(3, schData.getCourse());
            prepare.setString(4, schData.getSchedule_id());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }
    public void ScheduleDelete(ScheduleData schData) {
        String deleteData = "DELETE FROM schedule WHERE schedule_id = ?";

        connect = ConnectionUtil.getConnection();

        try {
            PreparedStatement statement = connect.prepareStatement(deleteData);
            statement.setString(1, schData.getSchedule_id());
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabaseResources();
        }
    }


}
