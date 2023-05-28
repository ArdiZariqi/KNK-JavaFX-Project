package Models;

import java.sql.Date;

public class AbsenceSummary {
    private Integer a_id;
    private Integer student_id;
    private String class_;
    private String course_name;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer total_reasonable_absences;
    private Integer total_unreasonable_absences;
    private Integer total_absences;

    public AbsenceSummary(Integer student_id, String class_, String course_name, String firstName, String lastName, String gender,Integer total_reasonable_absences,Integer total_unreasonable_absences,Integer total_absences) {

        this.student_id= student_id;
        this.class_ = class_;
        this.course_name = course_name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.total_reasonable_absences=total_reasonable_absences;
        this.total_unreasonable_absences=total_unreasonable_absences;
        this.total_absences=total_absences;
    }



    public Integer getStudent_id() {
        return student_id;
    }

    public String getClass_() {
        return class_;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getTotal_reasonable_absences(){return total_reasonable_absences;}

    public Integer getTotal_unreasonable_absences(){return total_unreasonable_absences;}
    public Integer getTotal_absences(){return total_absences;}



}


