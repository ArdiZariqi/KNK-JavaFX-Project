package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button loginBtn1;

    @FXML
    private RadioButton signUpAsParent;

    @FXML
    private RadioButton signUpAsTeacher;

    @FXML
    private Button signUpBtn;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUsername;

    @FXML
    void loginAdmin(ActionEvent event) {

    }

    public void signUpAdmin(){
        String sql = "insert into user_account values  username = ? and password = ?";

    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
