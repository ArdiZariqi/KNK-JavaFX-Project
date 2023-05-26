package Models;

import java.sql.Date;

public class TotalAbsences{
    private Integer student_id;
    private String class_;
    private String firstName;
    private String lastName;
    private String firstSemester;
    private String secondSemester;

    private Integer total_reasonable_absences;
    private Integer total_unreasonable_absences;
    private Integer total_absences;

    public TotalAbsences(Integer student_id, String class_, String firstName, String lastName, String firstSemester, String secondSemester, Integer total_reasonable_absences,Integer total_unreasonable_absences,Integer total_absences) {

        this.student_id= student_id;
        this.class_ = class_;
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstSemester=firstSemester;
        this.secondSemester=secondSemester;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getFirstSemester(){
        return firstSemester;
    }
    public String getSecondSemester(){
        return secondSemester;
    }

    public Integer getTotal_reasonable_absences(){return total_reasonable_absences;}

    public Integer getTotal_unreasonable_absences(){return total_unreasonable_absences;}
    public Integer getTotal_absences(){return total_absences;}



}
