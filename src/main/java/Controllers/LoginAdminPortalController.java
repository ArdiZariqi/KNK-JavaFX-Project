package Controllers;

import DBconnection.connectDb;
//import DBconnection.Db;
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
import service.PasswordHasher;
import service.UserService;
import service.interfaces.UserServiceInterface;


public class LoginAdminPortalController implements Initializable {
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

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private double x= 0 ;
    private double y= 0;

//    public void loginAccount(){
//
//        alertMessage alert = new alertMessage();
//
//        if (admin_username.getText().isEmpty()
//                || admin_password.getText().isEmpty()
//                || admin_user.getSelectionModel().getSelectedItem() == null){
//            alert.errorMessage("Please fill all blank fields");
//        }else {
//            String selectData = "SELECT username, password " +
//                    "FROM users " +
//                    "WHERE account_type = ? AND username = ? AND password = ?";
//
//            connect = connectDb.getConnection();
//
//            try {
//                prepare = connect.prepareStatement(selectData);
//                prepare.setString(1, admin_user.getSelectionModel().getSelectedItem());
//                prepare.setString(2, admin_username.getText());
//                prepare.setString(3, admin_password.getText());
//
//                result = prepare.executeQuery();
//
//                if(result.next()){
//                    alert.successMessage("Login Successfully!");
//                    Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/dashboard.fxml"));
//
//                    Stage stage = new Stage();
//                    stage.setTitle("Absence Management System(Admin)");
//                    stage.setScene(new Scene(root));
//
//                    root.setOnMousePressed((MouseEvent event) ->{
//                        x = event.getSceneX();
//                        y = event.getSceneY();
//                    });
//
//                    root.setOnMouseDragged((MouseEvent event) ->{
//                        stage.setX(event.getScreenX() - x);
//                        stage.setY(event.getScreenY() - y);
//                    });
//
//                    stage.show();
//
//                    admin_loginBtn.getScene().getWindow().hide();
//                }else {
//                    alert.errorMessage("Incorrect Username/Password");
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
    public LoginAdminPortalController() {
            this.userService = new UserService();
    }

    public void loginAccount() {
        alertMessage alert = new alertMessage();

        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()
                || admin_user.getSelectionModel().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            try {
                User loginUser = userService.login(admin_username.getText(), admin_password.getText());

                if (loginUser != null) {
                    alert.successMessage("Login Successfully!");
                    admin_loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/teacherdashboard.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Absence Management System(Admin)");
                    stage.setScene(new Scene(root));

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();



                } else {
                    alert.errorMessage("Incorrect Username/Password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

//    public void forgotPassword(){
//        alertMessage alert = new alertMessage();
//
//        if (forgetPw_username.getText().isEmpty()
//                || forgetPw_selectQuestion.getSelectionModel().getSelectedItem() == null
//                || forgetPw_ansewer.getText().isEmpty()){
//            alert.errorMessage("Please fill all blank fields");
//        }else {
//            String checkData = "SELECT username, question, answer " +
//                    "FROM users " +
//                    "WHERE username = ? AND question = ? AND answer = ?";
//
//            connect = connectDb.getConnection();
//
//            try {
//                prepare = connect.prepareStatement(checkData);
//                prepare.setString(1, forgetPw_username.getText());
//                prepare.setString(2, (String)forgetPw_selectQuestion.getSelectionModel().getSelectedItem());
//                prepare.setString(3, forgetPw_ansewer.getText());
//
//                result = prepare.executeQuery();
//
//                if (result.next()){
//                    adminPortal_form.setVisible(false);
//                    forgotPw_form.setVisible(false);
//                    changePw_form.setVisible(true);
//                }else {
//                    alert.errorMessage("Incorrect information");
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    public void forgotPassword() {
        alertMessage alert = new alertMessage();

        if (forgetPw_username.getText().isEmpty()
                || forgetPw_selectQuestion.getSelectionModel().getSelectedItem() == null
                || forgetPw_ansewer.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
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
                    alert.errorMessage("Incorrect information");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


//    public void changePassword(){
//        alertMessage alert = new alertMessage();
//
//        if (changePw_newPassword.getText().isEmpty() || changePW_confirmPassword.getText().isEmpty()){
//            alert.errorMessage("Please fill all blank fields");
//        } else if (!changePw_newPassword.getText().equals(changePW_confirmPassword.getText())){
//            alert.errorMessage("Password does not match");
//        }else if (changePw_newPassword.getText().length() < 8){
//            alert.errorMessage("Password must contain at least 8 characters");
//        }else {
//            String updateData = "UPDATE users " +
//                    "SET password = ?," +
//                    "update_date = ?" +
//                    "WHERE username = '" + forgetPw_username.getText() + "'";
//
//            connect = connectDb.getConnection();
//
//            try {
//                prepare = connect.prepareStatement(updateData);
//                prepare.setString(1, changePw_newPassword.getText());
//
//                java.util.Date utilDate = new java.util.Date();
//                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//
//                prepare.setString(2, String.valueOf(sqlDate));
//
//                prepare.executeUpdate();
//                alert.successMessage("Succesfully changed Password");
//
//                adminPortal_form.setVisible(true);
//                forgotPw_form.setVisible(false);
//                changePw_form.setVisible(false);
//
//                admin_username.setText("");
//                admin_password.setVisible(true);
//                admin_password.setText("");
//                admin_showPassword.setVisible(false);
//                admin_selectShowPassword.setSelected(false);
//
//                changePw_newPassword.setText("");
//                changePW_confirmPassword.setText("");
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
public void changePassword() {
    alertMessage alert = new alertMessage();

    if (changePw_newPassword.getText().isEmpty() || changePW_confirmPassword.getText().isEmpty()) {
        alert.errorMessage("Please fill all blank fields");
    } else if (!changePw_newPassword.getText().equals(changePW_confirmPassword.getText())) {
        alert.errorMessage("Password does not match");
    } else if (changePw_newPassword.getText().length() < 8) {
        alert.errorMessage("Password must contain at least 8 characters");
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

                alert.successMessage("Successfully changed Password");
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
                alert.errorMessage("User not found");
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
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/sign-up.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.show();
                admin_user.getScene().getWindow().hide();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectUser(){

        List<String> listU = new ArrayList<>();


        for (String data : users){
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        admin_user.setItems(listData);
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
        selectUser();
        forgotListQuestion();
    }
}