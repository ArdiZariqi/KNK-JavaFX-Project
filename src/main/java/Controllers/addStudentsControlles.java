package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class addStudentsControlles implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: Implement initialization code here
    }

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private DatePicker addStudents_birthDate;

    @FXML
    private Button addStudents_btn;

    @FXML
    private ComboBox<?> addStudents_class;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private TableColumn<?, ?> addStudents_col_birthDate;

    @FXML
    private TableColumn<?, ?> addStudents_col_class;

    @FXML
    private TableColumn<?, ?> addStudents_col_email;

    @FXML
    private TableColumn<?, ?> addStudents_col_firstName;

    @FXML
    private TableColumn<?, ?> addStudents_col_gender;

    @FXML
    private TableColumn<?, ?> addStudents_col_id;

    @FXML
    private TableColumn<?, ?> addStudents_col_lastName;

    @FXML
    private TableColumn<?, ?> addStudents_col_subject;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private TextField addStudents_email;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private TextField addStudents_id;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private TextField addStudents_search;

    @FXML
    private ComboBox<?> addStudents_subject;

    @FXML
    private TableView<?> addStudents_table;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private AnchorPane addStudents_view;

    @FXML
    private Button avaliableCourses_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private AnchorPane fill_pane;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_pane;

    public void switchForm(ActionEvent event){
        if (event.getSource() == addStudents_btn){
            addStudents_form.setVisible(true);
        }
    }

    @FXML
    private Button minimize_btn;

    @FXML
    private Button signOut_btn;

    @FXML
    private Button studentAbsences_btn;

    @FXML
    private AnchorPane tableview_pane;

    @FXML
    private BorderPane addStudents_form;

}



