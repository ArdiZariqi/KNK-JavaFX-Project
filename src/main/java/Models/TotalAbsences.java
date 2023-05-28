package Models;

public class TotalAbsences{
    private int student_id;
    private String class_;
    private String firstName;
    private String lastName;
    private int total_reasonable_absences_forSemester;
    private int total_unreasonable_absences_forSemester;

    public TotalAbsences(Integer student_id, String class_, String firstName, String lastName, Integer total_reasonable_absences_forSemester,Integer total_unreasonable_absences_forSemester) {

        this.student_id= student_id;
        this.class_ = class_;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total_reasonable_absences_forSemester=total_reasonable_absences_forSemester;
        this.total_unreasonable_absences_forSemester=total_unreasonable_absences_forSemester;
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

    public int getTotal_reasonable_absences_forSemester(){return total_reasonable_absences_forSemester;}

    public int getTotal_unreasonable_absences_forSemester(){return total_unreasonable_absences_forSemester;}

}
