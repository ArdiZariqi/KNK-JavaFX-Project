package Controllers;

import DBconnection.connectDb;
import Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ParentPortalController implements Initializable {
    @FXML
    private Button parent_loginBtn;

    @FXML
    private TextField parent_parentID;

    @FXML
    private PasswordField parent_password;

    @FXML
    private ComboBox<String> parent_selectUser;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;
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
        if (parent_parentID.getText().isEmpty() || parent_password.getText().isEmpty()){
            erorMessage("Please fill all blank fields");
        }else {
            String selectData = "SELECT * FROM parent WHERE parent_id = ? AND password = ?";

            connect = connectDb.getConnection();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, parent_parentID.getText());
                prepare.setString(2, parent_password.getText());

                result = prepare.executeQuery();
                if(result.next()){
                    successMessage("Login Successfully!");
                    Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/faqja1.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Absence Management System(Admin)");
                    stage.setScene(new Scene(root));

                    stage.show();

                    parent_loginBtn.getScene().getWindow().hide();
                }else {
                    erorMessage("Incorrect Parent ID/Password");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void switchForm(){
        try {
            Parent root = null;
            if (parent_selectUser.getSelectionModel().getSelectedItem().equals("Teacher Portal")){
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/TeacherPortal.fxml"));
            } else if (parent_selectUser.getSelectionModel().getSelectedItem().equals("Parent Portal")) {
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/ParentPortal.fxml"));
            }else if(parent_selectUser.getSelectionModel().getSelectedItem().equals("Admin Portal")){
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();

            parent_selectUser.getScene().getWindow().hide();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectuser(){

        Users users = new Users();
        List<String> listU = new ArrayList<>();

        for (String data : users.users){
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        parent_selectUser.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        selectuser();
    }
}
