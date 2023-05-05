package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File lockFile = new File("/image2.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        brandingImageView.setImage(lockImage);
    }
    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText("You try to login");
    }
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
