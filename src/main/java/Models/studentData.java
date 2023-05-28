package Models;

import java.sql.Date;


public class studentData {

    private Integer id;
    private String year;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birth;
    private String status;
    private String image;


    public studentData(Integer id, String year, String firstName, String lastName,
                       String gender, Date birth, String status, String image) {
        this.id = id;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.image = image;
    }

    public studentData(Integer id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public Date getBirth() {
        return birth;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
      this.image=image;
    }



}