package Models;

import java.sql.Date;


public class scheduleData {

    private String scheduleId;
    private String scheduleDay;
    private String scheduleTime;
    private String scheduleCourse;


    public scheduleData(String scheduleId, String scheduleDay, String scheduleTime, String scheduleCourse) {
        this.scheduleId = scheduleId;
        this.scheduleDay = scheduleDay;
        this.scheduleTime = scheduleTime;
        this.scheduleCourse = scheduleCourse;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public String getScheduleCourse() {
        return scheduleCourse;
    }

}