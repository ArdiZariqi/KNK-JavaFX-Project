package Models;

import java.sql.Date;

public class studentData {
    private int Id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Subject;
    private Date BirthDate;
    private String Gender;

    public studentData(int Id, String FirstName, String LastName, String Email, String Subject, Date BirthDate, String Gender){
        this.Id=Id;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Email=Email;
        this.Subject=Subject;
        this.BirthDate=BirthDate;
        this.Gender=Gender;
    }
    public int getId(){
        return Id;
    }
    public String getFirstName(){
        return FirstName;
    }
    public String getLastName()
    {
        return LastName;
    }
    public String getEmail(){
        return Email;
    }
    public String getSubject(){
        return Subject;
    }
    public Date getBirthDate(){
        return BirthDate;
    }
    public String getGender(){
        return Gender;
    }
}
