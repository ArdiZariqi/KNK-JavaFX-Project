package Models;

public class GetAbsenceSummary1 {
    private Integer id;
    private String year;
    private String firstName;
    private String lastName;
    private Integer total_reasonable_absences_forSemester;
    private Integer total_unreasonable_absences_forSemester;
    private Integer total_absences;


    public GetAbsenceSummary1(Integer id, String year, String firstName, String lastName,
                              Integer total_reasonable_absences_forSemester,
                              Integer total_unreasonable_absences_forSemester, Integer total_absences) {
        this.id = id;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total_reasonable_absences_forSemester = total_reasonable_absences_forSemester;
        this.total_unreasonable_absences_forSemester = total_unreasonable_absences_forSemester;
        this.total_absences = total_absences;
    }

    public Integer getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getTotal_reasonable_absences_forSemester() {
        return total_reasonable_absences_forSemester;
    }

    public Integer getTotal_unreasonable_absences_forSemester() {
        return total_unreasonable_absences_forSemester;
    }

    public Integer getTotal_absences() {
        return total_absences;
    }
}
