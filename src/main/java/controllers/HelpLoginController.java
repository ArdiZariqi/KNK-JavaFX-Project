package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.LanguageUtil;

public class HelpLoginController {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<String> languageID;
    @FXML
    private Label helpLogin1;
    @FXML
    private Label helpLogin2;
    @FXML
    private TextArea helpLogin3;

    @FXML
    private void initialize() {
        closeButton.setOnAction(event -> closeHelpWindow());

        languageID.setItems(FXCollections.observableArrayList("English", "Shqip"));
        languageID.setValue("English");
        languageID.setOnAction(e -> {
            setLanguage();
        });

        setLanguage();
    }

    private void closeHelpWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Main/login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            closeButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLanguage() {
        String selectedLanguage = languageID.getValue();
        LanguageUtil.setLanguage(selectedLanguage);
        helpLogin1.setText(LanguageUtil.getMessage("helplogin1"));
        helpLogin2.setText(LanguageUtil.getMessage("helplogin2"));
        helpLogin3.setText(LanguageUtil.getMessage("helplogin3"));
        closeButton.setText(LanguageUtil.getMessage("closeButton"));
    }
}

