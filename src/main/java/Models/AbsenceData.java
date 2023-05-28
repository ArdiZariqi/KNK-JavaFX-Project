package Models;

import java.sql.Date;

public class AbsenceData {

    private Integer a_id;
    private Integer schedule_id;
    private Integer student_id;
    private String year;
    private String course_name;
    private String time;
    private String firstName;
    private String lastName;
    private String gender;
    private Date date_;
    private String status;
    private String reasonability;
    private String day;

    public AbsenceData(Integer a_id, Integer student_id, String year, String firstName, String lastName,
                       String course_name, String time, String day, Date date_, String reasonability) {
        this.a_id = a_id;
        this.student_id = student_id;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course_name = course_name;
        this.time = time;
        this.day = day;
        this.date_ = date_;
        this.reasonability = reasonability;
    }

    public AbsenceData(Integer a_id, Integer student_id, Integer schedule_id, String reasonability) {
        this.a_id = a_id;
        this.student_id = student_id;
        this.schedule_id = schedule_id;
        this.reasonability = reasonability;
    }

    public AbsenceData(int aId) {
        this.a_id=aId;
    }


    public String getDay() { return day; }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate_() {
        return date_;
    }
    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setDate_(Date date_) {
        this.date_ = date_;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonability() {
        return reasonability;
    }

    public void setReasonability(String reasonability) {
        this.reasonability = reasonability;
    }


}
