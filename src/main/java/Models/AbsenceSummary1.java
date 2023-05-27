package Models;

public class AbsenceSummary1 {
    private Integer id;
    private String year;
    private String firstName;
    private String lastName;
    private Integer total_reasonable_absences;
    private Integer total_unreasonable_absences;

    public AbsenceSummary1(Integer id, String year, String firstName, String lastName, Integer total_reasonable_absences, Integer total_unreasonable_absences) {
        this.id = id;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total_reasonable_absences = total_reasonable_absences;
        this.total_unreasonable_absences = total_unreasonable_absences;
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

    public Integer getTotal_reasonable_absences() {
        return total_reasonable_absences;
    }

    public Integer getTotal_unreasonable_absences() {
        return total_unreasonable_absences;
    }
}
