package KNK_Projekti;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class loginController {

    //trying first commit
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessage;

    private ImageView schoolLogo;

    @FXML
    private Label schoolName;


    @FXML
    private void initialize() {
        // Set the default error message to an empty string
        errorMessage.setText("");
        Image logo = new Image("file:///C:/Users/user/Desktop/school_logo.png");
        schoolLogo.setImage(logo);

        // set the name of the school
        schoolName.setText("Shkolla e Mesme X");
    }


    @FXML
    private void handleLoginButton() {
        // Check if the username and password are correct
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isValid = isValidLogin(username, password);
        if (isValid) {
            // Navigate to the main application view
            // ...
        } else {
            // Display an error message
            errorMessage.setText("Invalid username or password");
        }
    }

    @FXML
    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLoginButton();
        }
    }

    private boolean isValidLogin(String username, String password) {
        // Check if the username and password are correct by querying the database
        // ...
        return false; // Return true if the username and password are correct
    }

}

