package Models;

import java.util.Date;

public class User {
    private int userId;
    private String email;
    private String accountType;
    private String username;
    private String saltedPassword;
    private String salt;
    private String question;
    private String answer;
    private Date date;
    private Date updateDate;

    public User(String email, String accountType, String username, String saltedPassword,
                String salt, String question, String answer, Date date, Date updateDate) {
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

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Date getDate() {
        return date;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
