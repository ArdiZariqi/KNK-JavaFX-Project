package Controllers;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class faqja1controller implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button availableCourse_btn;

    @FXML
    private Button studentAbstence_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private Label home_totalFemale;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private TextField addStudents_search;

    @FXML
    private TableView<?> addStudents_tableView;

    @FXML
    private TableColumn<?, String> addStudents_col_studentNum;

    @FXML
    private TableColumn<?, String> addStudents_col_year;

    @FXML
    private TableColumn<?, String> addStudents_col_course;

    @FXML
    private TableColumn<?, String> addStudents_col_firstName;

    @FXML
    private TableColumn<?, String> addStudents_col_lastName;

    @FXML
    private TableColumn<?, String> addStudents_col_gender;

    @FXML
    private TableColumn<?, String> addStudents_col_birth;

    @FXML
    private TableColumn<?, String> addStudents_col_status;

    @FXML
    private TextField addStudents_studentNum;

    @FXML
    private ComboBox<?> addStudents_year;

    @FXML
    private ComboBox<?> addStudents_course;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private DatePicker addStudents_birth;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TableView<?> availableCourse_tableView;

    @FXML
    private TableColumn<?, String> availableCourse_col_course;

    @FXML
    private TableColumn<?, String> availableCourse_col_description;

    @FXML
    private TableColumn<?, String> availableCourse_col_degree;

    @FXML
    private AnchorPane studentAbstence_form;

    @FXML
    private TextField studentAbstence_studentNum;

    @FXML
    private Label studentAbstence_year;

    @FXML
    private Label studentAbstence_course;

    @FXML
    private TextField studentAbstence_firstSem;

    @FXML
    private TextField studentAbstence_secondSem;

    @FXML
    private Button studentAbstence_updateBtn;

    @FXML
    private Button studentAbstence_clearBtn;

    @FXML
    private TableView<?> studentAbstence_tableView;

    @FXML
    private TableColumn<?, String> studentAbstence_col_studentNum;

    @FXML
    private TableColumn<?, String> studentAbstence_col_year;

    @FXML
    private TableColumn<?, String> studentAbstence_col_course;

    @FXML
    private TableColumn<?, String> studentAbstence_col_firstSem;

    @FXML
    private TableColumn<?, String> studentAbstence_col_secondSem;

    @FXML
    private TableColumn<?, ?> studentAbstence_col_final;

    @FXML
    private TextField studentAbstence_search;

    private Image image;

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void initialize(URL location,ResourceBundle resources){

    }


    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentAbstence_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentAbstence_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == availableCourse_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentAbstence_form.setVisible(false);

            availableCourse_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == studentAbstence_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentAbstence_form.setVisible(true);

            studentAbstence_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

        }
    }
}