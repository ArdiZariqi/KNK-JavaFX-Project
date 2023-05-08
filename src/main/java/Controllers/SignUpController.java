package Controllers;

import DBconnection.connectDb;
import Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button admin_SignUpBtn;
    @FXML
    private PasswordField adminSignUp_confirmPassword;
    @FXML
    private TextField adminSignUp_email;
    @FXML
    private Button adminSignUp_loginBtn;
    @FXML
    private PasswordField adminSignUp_password;
    @FXML
    private ComboBox<?> adminSignUp_user;
    @FXML
    private TextField adminSignUp_username;
    @FXML
    private ComboBox<?> adminSignUp_selectQuestion;
    @FXML
    private TextField adminSignUp_answer;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private final String[] questionList = {"What is your favorite food?", "What is your favorite color?", "What is the name of your pet?"
            , "What is your most favorite sport"};

    public void questions(){
        List<String> listQ = new ArrayList<>();

        for (String data : questionList){
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        adminSignUp_selectQuestion.setItems(listData);
    }
    public void register() {
        alertMessage alert = new alertMessage();
        if(adminSignUp_email.getText().isEmpty() || adminSignUp_username.getText().isEmpty()
                || adminSignUp_password.getText().isEmpty() || adminSignUp_confirmPassword.getText().isEmpty()
                || adminSignUp_selectQuestion.getSelectionModel().getSelectedItem() == null
                || adminSignUp_answer.getText().isEmpty()){
            alert.errorMessage("All fields are necessary to be filled");
        }else if (adminSignUp_password.getText() == adminSignUp_confirmPassword.getText()){
            alert.errorMessage("Password does not match");
        } else if (adminSignUp_password.getText().length() < 8) {
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        }else {
            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + adminSignUp_username.getText() + "'";

            connect = connectDb.getConnection();

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkUsername);

                if (result.next()){
                    alert.errorMessage(adminSignUp_username.getText() + " is already taken");
                }else {

                    String insertData = "INSERT INTO admin "
                            + "(email, username, password, question, answer, date_) "
                            + "VALUES(?, ?, ?, ?, ?, ?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, adminSignUp_email.getText());
                    prepare.setString(2, adminSignUp_username.getText());
                    prepare.setString(3, adminSignUp_password.getText());
                    prepare.setString(4,
                            (String)adminSignUp_selectQuestion.getSelectionModel().getSelectedItem());
                    prepare.setString(5, adminSignUp_answer.getText());

                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully");

                    registerClearFields();

                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    stage.show();

                    adminSignUp_user.getScene().getWindow().hide();

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerClearFields(){
        adminSignUp_email.setText("");
        adminSignUp_password.setText("");
        adminSignUp_confirmPassword.setText("");
        adminSignUp_selectQuestion.getSelectionModel().clearSelection();
        adminSignUp_answer.setText("");
    }

    public void switchFormSignUp(ActionEvent event){
        try {
            if (event.getSource() == adminSignUp_loginBtn){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                adminSignUp_user.getScene().getWindow().hide();
            }else if (adminSignUp_user.getSelectionModel().getSelectedItem().equals("Teacher Portal")){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/TeacherPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                adminSignUp_user.getScene().getWindow().hide();
            } else if (adminSignUp_user.getSelectionModel().getSelectedItem().equals("Parent Portal")) {
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/ParentPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                adminSignUp_user.getScene().getWindow().hide();
            }else if(adminSignUp_user.getSelectionModel().getSelectedItem().equals("Admin Portal")){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                adminSignUp_user.getScene().getWindow().hide();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUpSelectUser(){

        Users users = new Users();
        List<String> listU = new ArrayList<>();


        for (String data : users.users){
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        adminSignUp_user.setItems(listData);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUpSelectUser();
        questions();
    }
}