package controllers;

import Models.Lists;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.LanguageUtil;
import service.UserService;
import service.alertMessage;
import service.interfaces.UserServiceInterface;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static Models.Lists.questionList;

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
    private ComboBox<String> adminSignUp_user;
    @FXML
    private TextField adminSignUp_username;
    @FXML
    private ComboBox<String> adminSignUp_selectQuestion;
    @FXML
    private TextField adminSignUp_answer;
    @FXML
    private ComboBox<String> signupLanguage;
    @FXML
    private Label signupLabel;
    @FXML
    private Label signUp;

    private final UserServiceInterface userService;

    public SignUpController() {
        this.userService = new UserService();
    }

    public void questions() {
        List<String> listQ = new ArrayList<>();

        Collections.addAll(listQ, questionList);

        ObservableList<String> listData = FXCollections.observableArrayList(listQ);
        adminSignUp_selectQuestion.setItems(listData);
    }

    public void register() {
        alertMessage alert = new alertMessage();
        String selectedLanguage = signupLanguage.getValue();
        LanguageUtil.setLanguage(selectedLanguage);
        if (adminSignUp_email.getText().isEmpty() || adminSignUp_username.getText().isEmpty()
                || adminSignUp_password.getText().isEmpty() || adminSignUp_confirmPassword.getText().isEmpty()
                || adminSignUp_selectQuestion.getSelectionModel().isEmpty()
                || adminSignUp_answer.getText().isEmpty()) {
            alert.errorMessage(LanguageUtil.getMessage("error.fieldsRequired"));
        } else if (!adminSignUp_password.getText().equals(adminSignUp_confirmPassword.getText())) {
            alert.errorMessage(LanguageUtil.getMessage("error.passwordMismatch"));
        } else if (adminSignUp_password.getText().length() < 8) {
            alert.errorMessage(LanguageUtil.getMessage("error.invalidPassword"));
        } else {
            try {
                String email = adminSignUp_email.getText();
                String accountType = adminSignUp_user.getSelectionModel().getSelectedItem();
                String username = adminSignUp_username.getText();
                String password = adminSignUp_password.getText();
                String question = adminSignUp_selectQuestion.getSelectionModel().getSelectedItem();
                String answer = adminSignUp_answer.getText();
                Date date = new Date();

                User user = userService.getUserByUsername(adminSignUp_username.getText());

                if (user == null) {

                    userService.signUp(email, accountType, username, password, question, answer, date, null);

                    alert.successMessage(LanguageUtil.getMessage("signup.success.registered"));

                    registerClearFields();

                    Parent root = FXMLLoader.load(getClass().getResource("/Main/login.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    stage.show();

                    adminSignUp_user.getScene().getWindow().hide();
                } else {
                    alert.errorMessage(LanguageUtil.getMessage("signup.error.registrationFailed"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage(LanguageUtil.getMessage("signup.error.registrationFailed"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void registerClearFields() {
        adminSignUp_email.setText("");
        adminSignUp_password.setText("");
        adminSignUp_confirmPassword.setText("");
        adminSignUp_selectQuestion.getSelectionModel().clearSelection();
        adminSignUp_answer.setText("");
    }

    public void switchFormSignUp(ActionEvent event) {
        try {
            if (event.getSource() == adminSignUp_loginBtn || event.getSource() == admin_SignUpBtn) {
                Parent root = FXMLLoader.load(getClass().getResource("/Main/login.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.show();

                adminSignUp_user.getScene().getWindow().hide();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUpSelectUser() {
        ObservableList<String> listData = FXCollections.observableArrayList(Lists.users);
        adminSignUp_user.setItems(listData);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void min(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUpSelectUser();
        questions();

        signupLanguage.setItems(FXCollections.observableArrayList("English", "Shqip"));
        signupLanguage.setValue("English");
        signupLanguage.setOnAction(e -> {
            String language = signupLanguage.getValue().toLowerCase();
            LanguageUtil.setLanguage(language);
            setLanguage();
        });

        setLanguage();
    }

    public void setLanguage() {
        String selectedLanguage = signupLanguage.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        admin_SignUpBtn.setText(LanguageUtil.getMessage("button.signup"));
        adminSignUp_confirmPassword.setPromptText(LanguageUtil.getMessage("prompt.confirmPassword"));
        adminSignUp_email.setPromptText(LanguageUtil.getMessage("prompt.email"));
        adminSignUp_loginBtn.setText(LanguageUtil.getMessage("button.login"));
        adminSignUp_password.setPromptText(LanguageUtil.getMessage("prompt.password"));
        adminSignUp_selectQuestion.setPromptText(LanguageUtil.getMessage("prompt.selectQuestion"));
        adminSignUp_username.setPromptText(LanguageUtil.getMessage("prompt.username"));
        adminSignUp_answer.setPromptText(LanguageUtil.getMessage("prompt.answer"));
        adminSignUp_user.setPromptText(LanguageUtil.getMessage("prompt.selectUser"));
        ObservableList userTypeList = FXCollections.observableArrayList(
                LanguageUtil.getMessage("signup.user.userType1"),
                LanguageUtil.getMessage("signup.user.userType2"));
        adminSignUp_user.setItems(userTypeList);
        ObservableList questionList = FXCollections.observableArrayList(
                LanguageUtil.getMessage("signup.question.question1"),
                LanguageUtil.getMessage("signup.question.question2"),
                LanguageUtil.getMessage("signup.question.question3"),
                LanguageUtil.getMessage("signup.question.question4"));
        adminSignUp_selectQuestion.setItems(questionList);
        signupLabel.setText(LanguageUtil.getMessage("signup.label.haveAnAccount"));
        signUp.setText(LanguageUtil.getMessage("button.signup"));
    }
}
