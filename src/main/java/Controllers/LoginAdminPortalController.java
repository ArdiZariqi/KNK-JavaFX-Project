package Controllers;

import DBconnection.connectDb;
//import DBconnection.Db;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import Models.Users;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginAdminPortalController implements Initializable {
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
    private Button admin_signUpBtn;
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

    public void loginAccount(){

        alertMessage alert = new alertMessage();

        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }else {
            String selectData = "SELECT username, password FROM admin WHERE username = ? and password = ?";

            connect = connectDb.getConnection();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());

                result = prepare.executeQuery();

                if(result.next()){
                    alert.successMessage("Login Successfully!");
                    Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/dashboard.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Absence Management System(Admin)");
                    stage.setScene(new Scene(root));

                    stage.show();

                    admin_loginBtn.getScene().getWindow().hide();
                }else {
                    alert.errorMessage("Incorrect Username/Password");
                }
            }catch (Exception e){
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

    public void switchForm(ActionEvent event){
        try {
            if(event.getSource() == admin_forgotPassword){
                adminPortal_form.setVisible(false);
                forgotPw_form.setVisible(true);
                changePw_form.setVisible(false);
            } else if (event.getSource() == forgetPw_backBtn) {
                adminPortal_form.setVisible(true);
                forgotPw_form.setVisible(false);
                changePw_form.setVisible(false);
            }else if (event.getSource() == admin_signUpBtn) {
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/sign-up.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                admin_user.getScene().getWindow().hide();
            } else if (admin_user.getSelectionModel().getSelectedItem().equals("Teacher Portal")){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/TeacherPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                admin_user.getScene().getWindow().hide();
            } else if (admin_user.getSelectionModel().getSelectedItem().equals("Parent Portal")) {
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/ParentPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                admin_user.getScene().getWindow().hide();
            }else if(admin_user.getSelectionModel().getSelectedItem().equals("Admin Portal")){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.show();

                admin_user.getScene().getWindow().hide();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectUser(){

        Users users = new Users();
        List<String> listU = new ArrayList<>();


        for (String data : users.users){
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        admin_user.setItems(listData);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectUser();
    }
}
