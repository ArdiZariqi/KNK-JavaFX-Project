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
import DBconnection.connectDb;
import Models.courseData;
import Models.studentData;
import Models.getData;
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


public class TeacherDashboardController implements Initializable {

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
    private TableView<studentData> addStudents_tableView;

    @FXML
    private TableColumn<studentData, String> addStudents_col_studentNum;

    @FXML
    private TableColumn<studentData, String> addStudents_col_year;

    @FXML
    private TableColumn<studentData, String> addStudents_col_course;

    @FXML
    private TableColumn<studentData, String> addStudents_col_firstName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_lastName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_gender;

    @FXML
    private TableColumn<studentData, String> addStudents_col_birth;

    @FXML
    private TableColumn<studentData, String> addStudents_col_status;

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
    private TableView<courseData> availableCourse_tableView;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_description;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_degree;

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
    private TableView<studentData> studentAbstence_tableView;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_studentNum;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_year;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_course;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_firstSem;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_secondSem;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_final;

    @FXML
    private TextField studentAbstence_search;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void homeDisplayTotalEnrolledStudents() {

        String sql = "SELECT COUNT(id) FROM student";

        connect = connectDb.getConnection();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("COUNT(id)");
            }

            home_totalEnrolled.setText(String.valueOf(countEnrolled));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolled() {

        String sql = "SELECT COUNT(id) FROM student WHERE gender = 'Female' and status = 'Enrolled'";

        connect = connectDb.getConnection();

        try {
            int countFemale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("COUNT(id)");
            }

            home_totalFemale.setText(String.valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayMaleEnrolled() {

        String sql = "SELECT COUNT(id) FROM student WHERE gender = 'Male' and status = 'Enrolled'";

        connect = connectDb.getConnection();
        try {
            int countMale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("COUNT(id)");
            }
            home_totalMale.setText(String.valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalEnrolledChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = connectDb.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalEnrolledChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolledChart() {

        home_totalFemaleChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = connectDb.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalFemaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayEnrolledMaleChart() {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = connectDb.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalMaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addStudentsAdd() {

        String insertData = "INSERT INTO student "
                + "(studentNum,year,course,firstName,lastName,gender,birth,status,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = connectDb.getConnection();

        try {
            Alert alert;

            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF THE STUDENTNUMBER IS ALREADY EXIST
                String checkData = "SELECT studentNum FROM student WHERE studentNum = '"
                        + addStudents_studentNum.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student #" + addStudents_studentNum.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addStudents_studentNum.getText());
                    prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addStudents_firstName.getText());
                    prepare.setString(5, addStudents_lastName.getText());
                    prepare.setString(6, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(addStudents_birth.getValue()));
                    prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(9, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(10, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    String insertStudentAbstence = "INSERT INTO student_Abstence "
                            + "(studentNum,year,course,first_sem,second_sem,final) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertStudentAbstence);
                    prepare.setString(1, addStudents_studentNum.getText());
                    prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, "0");
                    prepare.setString(5, "0");
                    prepare.setString(6, "0");

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String updateData = "UPDATE student SET "
                + "year = '" + addStudents_year.getSelectionModel().getSelectedItem()
                + "', course = '" + addStudents_course.getSelectionModel().getSelectedItem()
                + "', firstName = '" + addStudents_firstName.getText()
                + "', lastName = '" + addStudents_lastName.getText()
                + "', gender = '" + addStudents_gender.getSelectionModel().getSelectedItem()
                + "', birth = '" + addStudents_birth.getValue()
                + "', status = '" + addStudents_status.getSelectionModel().getSelectedItem()
                + "', image = '" + uri + "' WHERE studentNum = '"
                + addStudents_studentNum.getText() + "'";

        connect = connectDb.getConnection();

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsDelete() {

        String deleteData = "DELETE FROM student WHERE studentNum = '"
                + addStudents_studentNum.getText() + "'";

        connect = connectDb.getConnection();

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    String checkData = "SELECT studentNum FROM student_Abstence "
                            + "WHERE studentNum = '" + addStudents_studentNum.getText() + "'";

                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();

                    // IF THE STUDENT NUMBER IS EXIST THEN PROCEED TO DELETE
                    if (result.next()) {
                        String deleteAbstence = "DELETE FROM student_Abstence WHERE "
                                + "studentNum = '" + addStudents_studentNum.getText() + "'";

                        statement = connect.createStatement();
                        statement.executeUpdate(deleteAbstence);

                    }// IF NOT THEN NVM

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addStudentsClear() {
        addStudents_studentNum.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birth.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);

        getData.path = "";
    }

    public void addStudentsInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addStudents_imageView.setImage(image);

            getData.path = file.getAbsolutePath();

        }
    } //WHILE WE INSERT THE DATA ON STUDENT, WE SHOULD INSERT ALSO THE DATA TO STUDENT_Abstence

    public void addStudentsSearch() {

        FilteredList<studentData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }