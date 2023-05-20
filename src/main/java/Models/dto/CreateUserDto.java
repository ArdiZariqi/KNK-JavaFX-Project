package Models.dto;

import java.util.Date;

public class CreateUserDto {
    private String email;
    private String accountType;
    private String username;
    private String saltedPassword;
    private String salt;
    private String question;
    private String answer;
    private Date date;
    private Date updateDate;

    public CreateUserDto(String email, String accountType, String username, String saltedPassword, String salt,
                         String question, String answer, Date date, Date updateDate) {
        this.email = email;
        this.accountType = accountType;
        this.username = username;
        this.saltedPassword = saltedPassword;
        this.salt = salt;
        this.question = question;
        this.answer = answer;
        this.date = date;
        this.updateDate = updateDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
