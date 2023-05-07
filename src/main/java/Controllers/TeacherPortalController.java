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

public class TeacherPortalController implements Initializable {
    @FXML
    private TextField teacher_teacherID;

    @FXML
    private PasswordField teacher_password;

    @FXML
    private Button teacher_loginBtn;

    @FXML
    private ComboBox<String> teacher_select;

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
        if (teacher_teacherID.getText().isEmpty() || teacher_password.getText().isEmpty()){
            erorMessage("Please fill all blank fields");
        }else {
            String selectData = "SELECT * FROM teacher WHERE teacher_id = ? AND password = ?";

            connect = connectDb.getConnection();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, teacher_teacherID.getText());
                prepare.setString(2, teacher_password.getText());

                result = prepare.executeQuery();
                if(result.next()){
                    successMessage("Login Successfully!");
                    Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/faqja1.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Absence Management System(Admin)");
                    stage.setScene(new Scene(root));

                    stage.show();

                    teacher_loginBtn.getScene().getWindow().hide();
                }else {
                    erorMessage("Incorrect Teacher ID/Password");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void switchForm(){
        try {
            Parent root = null;
            if (teacher_select.getSelectionModel().getSelectedItem().equals("Teacher Portal")){
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/TeacherPortal.fxml"));
            } else if (teacher_select.getSelectionModel().getSelectedItem().equals("Parent Portal")) {
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/ParentPortal.fxml"));
            }else if(teacher_select.getSelectionModel().getSelectedItem().equals("Admin Portal")){
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/AdminPortal.fxml"));
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();

            teacher_select.getScene().getWindow().hide();
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
        teacher_select.setItems(listData);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectuser();
    }
}
