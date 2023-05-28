package service;

import javafx.scene.control.Alert;
import service.interfaces.AlertInterface;

public class AlertService implements AlertInterface {
    public void errorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }
    public void informationAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Added!");
        alert.showAndWait();
    }

    public void updateAlert(){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Information Message");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Successfully Updated!");
        successAlert.showAndWait();
    }
    public void deleteAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Deleted!");
        alert.showAndWait();
    }


}
