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

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setScheduleCourse(String scheduleCourse) {
        this.scheduleCourse = scheduleCourse;
    }
}