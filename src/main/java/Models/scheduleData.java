package Models;

public class scheduleData {

    private String schedule_id;
    private String day;
    private String time;
    private String course;



    public scheduleData(String schedule_id, String day, String time, String course) {
        this.schedule_id = schedule_id;
        this.day = day;
        this.time = time;
        this.course = course;
    }
    public scheduleData(String schedule_id){
        this.schedule_id = schedule_id;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getCourse() {
        return course;
    }


}