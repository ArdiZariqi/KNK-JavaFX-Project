package Controllers;

import DBconnection.connectDb;
//import DBconnection.Db;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import Models.Users;
import javafx.stage.Stage;


public class AdminPortalController implements Initializable {
    @FXML
    private TextField admin_username;
    @FXML
    private PasswordField admin_password;
    @FXML
    private Button admin_loginBtn;
    @FXML
    private ComboBox<String> admin_user;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private  Alert alert;
    private void erorMessage(String message){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void successMessage(String message){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loginAccount(){
        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()){
            erorMessage("Please fill all blank fields");
        }else {
            String selectData = "SELECT * FROM admin WHERE username = ? and password = ?";

            connect = connectDb.getConnection();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());

                result = prepare.executeQuery();
                if(result.next()){
                    successMessage("Login Successfully!");
                    Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/faqja1.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Absence Management System(Admin)");
                    stage.setScene(new Scene(root));

                    stage.show();

                    admin_loginBtn.getScene().getWindow().hide();
                }else {
                    erorMessage("Incorrect Username/Password");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void switchForm(){
        try {
            Parent root = null;
            if (admin_user.getSelectionModel().getSelectedItem().equals("Teacher Portal")){
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/TeacherPortal.fxml"));
            } else if (admin_user.getSelectionModel().getSelectedItem().equals("Parent Portal")) {
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/ParentPortal.fxml"));
            }else if(admin_user.getSelectionModel().getSelectedItem().equals("Admin Portal")){
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();

            admin_user.getScene().getWindow().hide();
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
