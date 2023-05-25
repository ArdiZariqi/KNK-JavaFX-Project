package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import static Models.Users.questionList;
import static Models.Users.users;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.LanguageUtil;
import service.PasswordHasher;
import service.UserService;
import service.interfaces.UserServiceInterface;


public class LoginController implements Initializable {
    private UserService userService;
    @FXML
    private TextField admin_username;
    @FXML
    private PasswordField admin_password;
    @FXML
    private CheckBox admin_selectShowPassword;
    @FXML
    private TextField admin_showPassword;
    @FXML
    private Hyperlink admin_forgotPassword;
    @FXML
    private Button admin_loginBtn;
    @FXML
    private AnchorPane adminPortal_form;
    @FXML
    private Hyperlink admin_signUpBtn;
    @FXML
    private ComboBox<String> admin_user;
    @FXML
    private PasswordField changePW_confirmPassword;
    @FXML
    private Button changePw_backBtn;
    @FXML
    private AnchorPane changePw_form;
    @FXML
    private PasswordField changePw_newPassword;
    @FXML
    private Button changePw_proceedBtn;
    @FXML
    private Button helpButton;
    @FXML
    private TextField forgetPw_ansewer;
    @FXML
    private Button forgetPw_backBtn;
    @FXML
    private Button forgetPw_proceedBtn;
    @FXML
    private ComboBox<?> forgetPw_selectQuestion;
    @FXML
    private TextField forgetPw_username;
    @FXML
    private AnchorPane forgotPw_form;
    @FXML
    private ComboBox<String> loginLanguage;
    @FXML
    private Label loginLabel;
    @FXML
    private Label loginAccount;
    @FXML
    private ComboBox<String> loginLanguage1;
    @FXML
    private ComboBox<String> loginLanguage2;
    @FXML
    private Label forgetPwLabel;
    @FXML
    private Label forgetPwLabel1;
    private double x= 0 ;
    private double y= 0;

    public LoginController() {
            this.userService = new UserService();
    }

    public void loginAccount() {
        alertMessage alert = new alertMessage();

        String selectedLanguage = loginLanguage.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
            alert.errorMessage(LanguageUtil.getMessage("error.fieldsRequired"));
        } else {
            try {
                User loginUser = userService.login(admin_username.getText(), admin_password.getText());
                if (loginUser != null) {
                    String accountType = userService.getByAccountType(admin_username.getText());
                    if (accountType.equals("Teacher")){
                        alert.successMessage(LanguageUtil.getMessage("login.success"));
                        admin_loginBtn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/teacherdashboard.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle(LanguageUtil.getMessage("login.admin.title"));
                        stage.setScene(new Scene(root));
                        root.setOnMousePressed((MouseEvent event) -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);

                        });

                        KeyCombination closeKeyCombination = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
                        KeyCombination minimizeKeyCombination = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);

                        stage.getScene().setOnKeyPressed(event -> {
                            if (minimizeKeyCombination.match(event)) {
                                Stage window = (Stage) stage.getScene().getWindow();
                                window.setIconified(true);
                            }
                        });

                        root.setOnKeyPressed(event-> {
                            if (closeKeyCombination.match(event)) {
                                System.exit(0);
                            }
                        });

                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                    }else {

                        alert.successMessage(LanguageUtil.getMessage("login.success"));
                        admin_loginBtn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/adminDashboard.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle(LanguageUtil.getMessage("login.admin.title"));
                        stage.setScene(new Scene(root));


                        root.setOnMousePressed((MouseEvent event) -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);

                        });

                        KeyCombination closeKeyCombination = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
                        KeyCombination minimizeKeyCombination = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);

                        stage.getScene().setOnKeyPressed(event -> {
                            if (minimizeKeyCombination.match(event)) {
                                Stage window = (Stage) stage.getScene().getWindow();
                                window.setIconified(true);
                            }
                        });

                        root.setOnKeyPressed(event-> {
                            if (closeKeyCombination.match(event)) {
                                System.exit(0);
                            }
                        });

                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                    }

                } else {
                    alert.errorMessage(LanguageUtil.getMessage("login.incorrectData"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void openHelp(ActionEvent event){
        try {
            if (event.getSource() == helpButton ){
                Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/helpLogin.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.show();

                helpButton.getScene().getWindow().hide();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPassword(){
        if (admin_selectShowPassword.isSelected()){
            admin_showPassword.setText(admin_password.getText());
            admin_showPassword.setVisible(true);
            admin_password.setVisible(false);
        }else {
            admin_password.setText(admin_showPassword.getText());
            admin_showPassword.setVisible(false);
            admin_password.setVisible(true);
        }
    }

    public void forgotPassword() {
        alertMessage alert = new alertMessage();

        String selectedLanguage = loginLanguage1.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        if (forgetPw_username.getText().isEmpty()
                || forgetPw_selectQuestion.getSelectionModel().getSelectedItem() == null
                || forgetPw_ansewer.getText().isEmpty()) {
            alert.errorMessage(LanguageUtil.getMessage("error.fieldsRequired"));
        } else {
            try {
                UserServiceInterface userService = new UserService();
                User user = userService.getUserByUsername(forgetPw_username.getText());

                if (user != null && user.getQuestion().equals(forgetPw_selectQuestion.getSelectionModel().getSelectedItem())
                        && user.getAnswer().equals(forgetPw_ansewer.getText())) {
                    adminPortal_form.setVisible(false);
                    forgotPw_form.setVisible(false);
                    changePw_form.setVisible(true);
                } else {
                    alert.errorMessage(LanguageUtil.getMessage("forgotPw.incorrectInfo"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void changePassword() {
        alertMessage alert = new alertMessage();

        String selectedLanguage = loginLanguage.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        if (changePw_newPassword.getText().isEmpty() || changePW_confirmPassword.getText().isEmpty()) {
            alert.errorMessage(LanguageUtil.getMessage("error.fieldsRequired"));
        } else if (!changePw_newPassword.getText().equals(changePW_confirmPassword.getText())) {
            alert.errorMessage(LanguageUtil.getMessage("error.passwordMismatch"));
        } else if (changePw_newPassword.getText().length() < 8) {
            alert.errorMessage(LanguageUtil.getMessage("error.invalidPassword"));
        } else {
            try {
                UserServiceInterface userService = new UserService();
                User user = userService.getUserByUsername(forgetPw_username.getText());

                if (user != null) {
                    String salt = PasswordHasher.generateSalt();
                    String saltedHash = PasswordHasher.hashPassword(changePw_newPassword.getText(), salt);

                    user.setSalt(salt);
                    user.setSaltedPassword(saltedHash);

                    userService.updateUser(user);

                    alert.successMessage(LanguageUtil.getMessage("changePw.success"));
                    adminPortal_form.setVisible(true);
                    forgotPw_form.setVisible(false);
                    changePw_form.setVisible(false);

                    admin_username.setText("");
                    admin_password.setVisible(true);
                    admin_password.setText("");
                    admin_showPassword.setVisible(false);
                    admin_selectShowPassword.setSelected(false);

                    changePw_newPassword.setText("");
                    changePW_confirmPassword.setText("");
                } else {
                    alert.errorMessage(LanguageUtil.getMessage("changePw.userNotFound"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void forgotListQuestion(){
        List<String> listQ = new ArrayList<>();
        for (String data : questionList){
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        forgetPw_selectQuestion.setItems(listData);
    }

    public void switchForm(ActionEvent event){
        try {
            if(event.getSource() == admin_forgotPassword){
                adminPortal_form.setVisible(false);
                forgotPw_form.setVisible(true);
                changePw_form.setVisible(false);

                forgotListQuestion();
            } else if (event.getSource() == forgetPw_backBtn) {
                adminPortal_form.setVisible(true);
                forgotPw_form.setVisible(false);
                changePw_form.setVisible(false);
            }else if (event.getSource() == changePw_backBtn) {
                adminPortal_form.setVisible(false);
                forgotPw_form.setVisible(true);
                changePw_form.setVisible(false);
            }else if (event.getSource() == admin_signUpBtn) {
                Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/sign-up.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
//                stage.setMaximized(true);
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.show();
                admin_user.getScene().getWindow().hide();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void min(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        forgotListQuestion();
        loginLanguage1.setItems(FXCollections.observableArrayList("English", "Shqip"));
        loginLanguage1.setValue("English");
        loginLanguage1.setOnAction(e -> {
            setLanguage1();
        });
        setLanguage1();
        loginLanguage2.setItems(FXCollections.observableArrayList("English", "Shqip"));
        loginLanguage2.setValue("English");
        loginLanguage2.setOnAction(e -> {
            setLanguage2();
        });
        setLanguage2();
        loginLanguage.setItems(FXCollections.observableArrayList("English", "Shqip"));
        loginLanguage.setValue("English");
        loginLanguage.setOnAction(e -> {
            setLanguage();
        });

        setLanguage();
    }

    public void setLanguage() {

        String selectedLanguage = loginLanguage.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        admin_username.setPromptText(LanguageUtil.getMessage("prompt.username"));
        admin_password.setPromptText(LanguageUtil.getMessage("prompt.password"));
        admin_selectShowPassword.setText(LanguageUtil.getMessage("login.showPw"));
        admin_forgotPassword.setText(LanguageUtil.getMessage("login.forgotPw"));
        admin_loginBtn.setText(LanguageUtil.getMessage("button.login"));
        admin_signUpBtn.setText(LanguageUtil.getMessage("login.signupBtn"));
        loginAccount.setText(LanguageUtil.getMessage("login.Acc"));
        loginLabel.setText(LanguageUtil.getMessage("login.label"));
    }

    public void setLanguage1(){
        String selectedLanguage = loginLanguage1.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        forgetPwLabel.setText(LanguageUtil.getMessage("change.password"));
        changePw_newPassword.setPromptText(LanguageUtil.getMessage("new.password"));
        changePW_confirmPassword.setPromptText(LanguageUtil.getMessage("prompt.confirmPassword"));
        changePw_proceedBtn.setText(LanguageUtil.getMessage("change.password"));
        changePw_backBtn.setText(LanguageUtil.getMessage("back.btn"));
    }

    public void setLanguage2(){
        String selectedLanguage = loginLanguage2.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        forgetPwLabel1.setText(LanguageUtil.getMessage("forgot.password.label"));
        forgetPw_username.setPromptText(LanguageUtil.getMessage("prompt.username"));
        forgetPw_selectQuestion.setPromptText(LanguageUtil.getMessage("prompt.selectQuestion"));
        ObservableList questionList = FXCollections.observableArrayList(
                LanguageUtil.getMessage("signup.question.question1"),
                LanguageUtil.getMessage("signup.question.question2"),
                LanguageUtil.getMessage("signup.question.question3"),
                LanguageUtil.getMessage("signup.question.question4")
        );
        forgetPw_selectQuestion.setItems(questionList);
        forgetPw_ansewer.setPromptText(LanguageUtil.getMessage("prompt.answer"));
        forgetPw_proceedBtn.setText(LanguageUtil.getMessage("proceed.btn"));
        forgetPw_backBtn.setText(LanguageUtil.getMessage("back.btn"));
    }
}