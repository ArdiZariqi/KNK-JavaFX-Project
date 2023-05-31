package controllers;

import Models.*;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class AdminController implements Initializable {

    private final AdminUserService adminUserService;
    private final TeacherCourseService teacherCourseService;
    private final AdminAbsSumService adminAbsSumService;
    private final ScheduleService scheduleService;
    private final AlertService alertService;
    @FXML
    private Button helpButton;
    @FXML
    private Button addStudents_addBtn;
    @FXML
    private DatePicker addStudents_birth;
    @FXML
    private Button addStudents_btn;
    @FXML
    private Button addStudents_clearBtn;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_birth;
    @FXML
    private TableColumn<StudentData, String> addStudents_col_firstName;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_gender;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_lastName;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_status;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_studentNum;

    @FXML
    private TableColumn<StudentData, String> addStudents_col_year;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;


    @FXML
    private TextField addStudents_lastName;

    @FXML
    private TextField addStudents_search;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private TextField addStudents_studentNum;

    @FXML
    private TableView<StudentData> addStudents_tableView;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private ComboBox<?> addStudents_year;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;
    @FXML
    private Label studentLabel;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<String,Integer > home_totalEnrolledChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private AreaChart<String, Integer> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private LineChart<String, Integer> home_totalMaleChart;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private AnchorPane studentAbstence2_form;

    @FXML
    private Label username;
    @FXML
    private ComboBox<String> languageID;
    @FXML
    private Label signout;
    @FXML
    private Label home_totalEnrolledLabel;
    @FXML
    private Label home_totalFemaleEnrolled;
    @FXML
    private Label home_totalMaleEnrolled;
    @FXML
    private Label yearLabel;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label birthDateLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField scheduleLabel;
    @FXML
    private TextField dayLabel;
    @FXML
    private TextField timeLabel;
    @FXML
    private ComboBox<String> courseLabel1;
    @FXML
    private Button scheduleAdd;
    @FXML
    private Button scheduleUpdate;
    @FXML
    private Button scheduleClear;
    @FXML
    private Button scheduleDelete;
    @FXML
    private TableColumn<ScheduleData, String> scheduleId;
    @FXML
    private TableColumn<ScheduleData, String> scheduleDay;
    @FXML
    private TableColumn<ScheduleData, String> scheduleTime;
    @FXML
    private TableColumn<ScheduleData, String> scheduleCourse1;
    @FXML
    private TableView<ScheduleData> scheduleTableView;
    @FXML
    private AnchorPane studentSchedule_form;
    @FXML
    private Button studentSchedule_btn;
    @FXML
    private TextField Absence_search2;
    @FXML
    private TableColumn<TotalAbsences, String> addAbsence_col_lastName2;
    @FXML
    private TableColumn<TotalAbsences, String> addAbsence_col_firstName2;
    @FXML
    private TableColumn<TotalAbsences, String> addAbsence_col_stid2;
    @FXML
    private TableColumn<TotalAbsences, String> addAbsence_col_class2;

    @FXML
    private TableColumn<TotalAbsences, String> addAbsence_col_reasonable2;
    @FXML
    private TableColumn <TotalAbsences, String> addAbsence_col_unreasonable2;
    @FXML
    private Button studentAbstence_btn2;
    @FXML
    private TableView<TotalAbsences> studentAbsence_tableView2;
    @FXML
    private DatePicker start_date;
    @FXML
    private DatePicker end_date;
    @FXML
    private Button scheduleAdd1;
    private Image image;

    public AdminController() {
        adminUserService = new AdminUserService();
        teacherCourseService = new TeacherCourseService();
        adminAbsSumService = new AdminAbsSumService();
        scheduleService= new ScheduleService();
        alertService = new AlertService();

    }

    public void homeDisplayTotalEnrolledStudents() {
        try {
            int countEnrolled = adminUserService.getTotalStudentCount();
            home_totalEnrolled.setText(String.valueOf(countEnrolled));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayFemaleEnrolled() {
        try {
            int countFemale = adminUserService.getTotalFemaleStudent();
            home_totalFemale.setText(String.valueOf(countFemale));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayMaleEnrolled() {
        try {
            int countMale = adminUserService.getTotalMaleStudent();
            home_totalMale.setText(String.valueOf(countMale));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayTotalEnrolledChart() {
        home_totalEnrolledChart.getData().clear();

        try {
            XYChart.Series<String, Integer> chartData = adminUserService.getTotalStudentChart();
            home_totalEnrolledChart.getData().add(chartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayFemaleEnrolledChart() {
        home_totalFemaleChart.getData().clear();

        try {
            XYChart.Series<String, Integer> chartData = adminUserService.getFemaleStudentChart();
            home_totalFemaleChart.getData().add(chartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayEnrolledMaleChart() {
        home_totalMaleChart.getData().clear();

        try {
            XYChart.Series<String, Integer> chartData = adminUserService.getMaleStudentChart();
            home_totalMaleChart.getData().add(chartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[] classList = {"First year","Second year","Third year" };

    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        Collections.addAll(yearL, classList);

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);
    }

    private String[] genderList = {"Male", "Female"};

    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();

        Collections.addAll(genderL, genderList);

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }

    private String[] statusList = {"Enrolled", "Inactive"};

    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();

        Collections.addAll(statusL, statusList);

        ObservableList ObList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }

    public void addStudentsInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addStudents_imageView.setImage(image);

            GetData.path = file.getAbsolutePath();

        }
    }

    public void addStudentsAdd() {

        if (addStudents_studentNum.getText().isEmpty()
                || addStudents_year.getSelectionModel().getSelectedItem()==null
                || addStudents_firstName.getText().isEmpty()
                || addStudents_lastName.getText().isEmpty()
                || addStudents_gender.getSelectionModel().getSelectedItem() == null
                || addStudents_birth.getValue() == null
                || addStudents_status.getSelectionModel().getSelectedItem() == null
                || GetData.path == null || GetData.path == "") {
            alertService.errorAlert();
        } else {
            try {
                int id = Integer.parseInt(addStudents_studentNum.getText());
                String uri = GetData.path;
                uri = uri.replace("\\", "\\\\");

                StudentData sData = new StudentData(
                        id,
                        (String) addStudents_year.getSelectionModel().getSelectedItem(),
                        addStudents_firstName.getText(),
                        addStudents_lastName.getText(),
                        (String) addStudents_gender.getSelectionModel().getSelectedItem(),
                        java.sql.Date.valueOf(addStudents_birth.getValue()),
                        (String) addStudents_status.getSelectionModel().getSelectedItem(),
                        uri
                );

                adminUserService.addStd(sData);
                alertService.informationAlert();
                addStudentsShowListData();

                addStudentsClear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addStudentsClear() {
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birth.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);

        GetData.path = "";
    }

    public void addStudentsUpdate() {
    try {
        Alert alert;
        if (    addStudents_year.getSelectionModel().getSelectedItem() == null
                || addStudents_firstName.getText().isEmpty()
                || addStudents_lastName.getText().isEmpty()
                || addStudents_gender.getSelectionModel().getSelectedItem() == null
                || addStudents_birth.getValue() == null
                || addStudents_status.getSelectionModel().getSelectedItem() == null
                || GetData.path == null || GetData.path == "") {
            alertService.errorAlert();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                int id = Integer.parseInt(addStudents_studentNum.getText());
                String uri = GetData.path;
                uri = uri.replace("\\", "\\\\");

                StudentData sData = new StudentData(
                        id,
                        (String) addStudents_year.getSelectionModel().getSelectedItem(),
                        addStudents_firstName.getText(),
                        addStudents_lastName.getText(),
                        (String) addStudents_gender.getSelectionModel().getSelectedItem(),
                        java.sql.Date.valueOf(addStudents_birth.getValue()),
                        (String) addStudents_status.getSelectionModel().getSelectedItem(),
                        uri
                );
                adminUserService.updateStd(sData);
                alertService.deleteAlert();
                addStudentsShowListData();
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
        try {
            Alert alert;
            alertMessage alertMessage = new alertMessage();
            if (addStudents_studentNum.getText() == null) {
                alertService.errorAlert();
            } else {
                StudentData student = adminUserService.getById(Integer.valueOf(addStudents_studentNum.getText()));
                if (student != null) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        int id = Integer.parseInt(addStudents_studentNum.getText());
                        StudentData sData = new StudentData(id);

                        adminUserService.deleteStd(sData);
                        alertService.deleteAlert();
                        addStudentsShowListData();
                        addStudentsClear();
                    }
                } else {
                    alertMessage.errorMessage("Error, the student does not exist!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<StudentData> addStudentsListData() {
        ObservableList<StudentData> listStudents = null;

        try {
            listStudents = adminUserService.StdListData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudents;
    }

    public void addStudentsSearch() {

        FilteredList<StudentData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
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

        SortedList<StudentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);
    }

    private ObservableList<StudentData> addStudentsListD;


    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);

    }

    public void addAbsenceSearch2() {
        FilteredList<TotalAbsences> filter = new FilteredList<>(addStudentsListD2, e -> true);

        Absence_search2.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudent_id().toString().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getClass_().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().startsWith(searchKey)) {
                    return true;
                }else{
                    return false;
                }
            });
        });

        SortedList<TotalAbsences> sortList1 = new SortedList<>(filter);
        sortList1.comparatorProperty().bind(studentAbsence_tableView2.comparatorProperty());
        studentAbsence_tableView2.setItems(sortList1);
    }

    public ObservableList<TotalAbsences> addAbsencesListData2() throws SQLException {

        LocalDate startDate = start_date.getValue();
        LocalDate endDate = end_date.getValue();

        return adminAbsSumService.StdListData2(startDate, endDate);
    }


    private ObservableList<TotalAbsences> addStudentsListD2;

    public void addAbsencesShowListData2() throws SQLException {
        addStudentsListD2 = addAbsencesListData2();

        addAbsence_col_stid2.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        addAbsence_col_class2.setCellValueFactory(new PropertyValueFactory<>("class_"));
        addAbsence_col_firstName2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addAbsence_col_lastName2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addAbsence_col_reasonable2.setCellValueFactory(new PropertyValueFactory<>("total_reasonable_absences_forSemester"));
        addAbsence_col_unreasonable2.setCellValueFactory(new PropertyValueFactory<>("total_unreasonable_absences_forSemester"));

        studentAbsence_tableView2.setItems(addStudentsListD2);
    }

    public ObservableList<ScheduleData> availableScheduleListData() {
        ObservableList<ScheduleData> listStudents = null;

        try {
            listStudents = scheduleService.schList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudents;
    }

    private ObservableList<ScheduleData> availableScheduleList;
    public void availableScheduleShowListData() {
        availableScheduleList = availableScheduleListData();

        scheduleId.setCellValueFactory(new PropertyValueFactory<>("schedule_id"));
        scheduleDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        scheduleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        scheduleCourse1.setCellValueFactory(new PropertyValueFactory<>("course"));

        scheduleTableView.setItems(availableScheduleList);

    }

    public void studentScheduleAdd() {


        try {
            Alert alert;

            if (scheduleLabel.getText().isEmpty()
                    || dayLabel.getText().isEmpty()
                    || timeLabel.getText().isEmpty()
                    || courseLabel1.getSelectionModel().getSelectedItem() == null){
                alertService.errorAlert();
            } else {

               if(scheduleService.checkSch( scheduleLabel.getText())==true){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Schedule: " + scheduleLabel.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    ScheduleData schData = new ScheduleData(
                             scheduleLabel.getText(),
                             dayLabel.getText(),
                             timeLabel.getText(),
                            courseLabel1.getSelectionModel().getSelectedItem());
                    scheduleService.addSchedule(schData);

                    alertService.informationAlert();
                    availableScheduleShowListData();
                    studentScheduleClear();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addStudentsScheduleCourseList() {

        try {
            ObservableList<String> listC = teacherCourseService.ListOfCourses();
            courseLabel1.setItems(listC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void studentScheduleUpdate() {


        try {
            Alert alert;

            if (scheduleLabel.getText().isEmpty()
                    || dayLabel.getText().isEmpty()
                    || timeLabel.getText().isEmpty()
                    || courseLabel1.getSelectionModel().getSelectedItem() == null) {
                alertService.errorAlert();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Schedule: " + scheduleLabel.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    ScheduleData schData = new ScheduleData(
                            scheduleLabel.getText(),
                            dayLabel.getText(),
                            timeLabel.getText(),
                            courseLabel1.getSelectionModel().getSelectedItem());
                    scheduleService.updateSch(schData);

                    alertService.updateAlert();
                    availableScheduleShowListData();
                    studentScheduleClear();

                } else {
                    return;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void studentScheduleClear() {
        scheduleLabel.setText("");
        dayLabel.setText("");
        timeLabel.setText("");
        courseLabel1.getSelectionModel().clearSelection();
    }

    public void studentScheduleDelete() {
        try {
            Alert alert;
            alertMessage alertMessage = new alertMessage();

            if (scheduleLabel.getText().isEmpty()) {
                alertService.errorAlert();
            } else {
                String scheduleId = scheduleLabel.getText();

                ScheduleData scheduleD = adminUserService.getScheduleById(scheduleId);
                if (scheduleD != null) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to DELETE Schedule #" + scheduleId + "?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        scheduleD = new ScheduleData(scheduleId);
                        scheduleService.deleteSch(scheduleD);
                        alertService.deleteAlert();
                        availableScheduleShowListData();
                        studentScheduleClear();
                    }
                } else {
                    alertMessage.errorMessage("Schedule #" + scheduleId + " doesn't exist!");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<TotalAbsences> addAbsencesListData1() {
        ObservableList<TotalAbsences> listStudents = null;

        try {
            listStudents = adminAbsSumService.showStdList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudents;
    }
    private ObservableList<TotalAbsences> addStudentsListD1;
    public void addAbsencesShowListData1() {
        addStudentsListD1 = addAbsencesListData1();

        addAbsence_col_stid2.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        addAbsence_col_class2.setCellValueFactory(new PropertyValueFactory<>("class_"));
        addAbsence_col_firstName2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addAbsence_col_lastName2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addAbsence_col_reasonable2.setCellValueFactory(new PropertyValueFactory<>("total_reasonable_absences_forSemester"));
        addAbsence_col_unreasonable2.setCellValueFactory(new PropertyValueFactory<>("total_unreasonable_absences_forSemester"));

        studentAbsence_tableView2.setItems(addStudentsListD1);
    }

    public void availableScheduleSelect() {
        ScheduleData scheduleD = scheduleTableView.getSelectionModel().getSelectedItem();
        int num = scheduleTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        scheduleLabel.setText(scheduleD.getSchedule_id());
        dayLabel.setText(scheduleD.getDay());
        timeLabel.setText(scheduleD.getTime());
    }

    @FXML
    public void openHelp(ActionEvent event){
        try {
            if (event.getSource() == helpButton ){
                Parent root = FXMLLoader.load(getClass().getResource("/Main/helpn1.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.show();

                helpButton.getScene().getWindow().hide();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void switchForm(ActionEvent event) throws SQLException {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            studentSchedule_form.setVisible(false);
            studentAbstence2_form.setVisible(false);


            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            studentSchedule_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn2.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayEnrolledMaleChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();


        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            studentSchedule_form.setVisible(false);
            studentAbstence2_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            studentSchedule_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn2.setStyle("-fx-background-color:transparent");


            addStudentsShowListData();
            addStudentsYearList();
            addStudentsGenderList();
            addStudentsStatusList();
            addStudentsSearch();

        } else if (event.getSource() == studentSchedule_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentSchedule_form.setVisible(true);
            studentAbstence2_form.setVisible(false);

            studentSchedule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn2.setStyle("-fx-background-color:transparent");


            availableScheduleShowListData();
            addStudentsScheduleCourseList();

        }else if (event.getSource() == studentAbstence_btn2) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentSchedule_form.setVisible(false);
            studentAbstence2_form.setVisible(true);



            studentAbstence_btn2.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentSchedule_btn.setStyle("-fx-background-color:transparent");
            addAbsencesShowListData2();
            addAbsencesShowListData1();
        }
    }
    private double x=0;
    private double y=0;
    public void logout() {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {


                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/Main/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addStudentsSelect() {

        StudentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_studentNum.setText(String.valueOf(studentD.getId()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birth.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));

        String uri = "file:" + studentD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addStudents_imageView.setImage(image);

        GetData.path = studentD.getImage();
    }

    @FXML
    private void close() {
        System.exit(0);
    }
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){

        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayEnrolledMaleChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayTotalEnrolledChart();

        addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        availableScheduleShowListData();
        addStudentsScheduleCourseList();
        addAbsencesShowListData1();
        languageID.setItems(FXCollections.observableArrayList("English", "Shqip"));
        languageID.setValue("English");
        languageID.setOnAction(e -> setLanguage());

        setLanguage();
    }

    public void setLanguage() {

        String selectedLanguage = languageID.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        home_btn.setText(LanguageUtil.getMessage("home.btn"));
        home_totalEnrolledLabel.setText(LanguageUtil.getMessage("home.totalEnrolled"));
        home_totalFemaleEnrolled.setText(LanguageUtil.getMessage("home.totalFemale"));
        home_totalMaleEnrolled.setText(LanguageUtil.getMessage("home.totalMale"));
        home_totalEnrolledChart.setTitle(LanguageUtil.getMessage("tot.enrolled.chart"));
        home_totalFemaleChart.setTitle(LanguageUtil.getMessage("tot.female.enrolled"));
        home_totalMaleChart.setTitle(LanguageUtil.getMessage("tot.male.enrolled"));
        addStudents_btn.setText(LanguageUtil.getMessage("addStudents.btn"));
        addStudents_addBtn.setText(LanguageUtil.getMessage("addStudents.addBtn"));
        addStudents_clearBtn.setText(LanguageUtil.getMessage("addStudents.clearBtn"));
        addStudents_deleteBtn.setText(LanguageUtil.getMessage("addStudents.deleteBtn"));
        addStudents_insertBtn.setText(LanguageUtil.getMessage("addStudents.insertBtn"));
        addStudents_updateBtn.setText(LanguageUtil.getMessage("addStudents.updateBtn"));
        addStudents_col_studentNum.setText(LanguageUtil.getMessage("student.id"));
        addStudents_col_year.setText(LanguageUtil.getMessage("studentAbstence.col_year"));
        addStudents_col_firstName.setText(LanguageUtil.getMessage("student.first.name"));
        addStudents_col_lastName.setText(LanguageUtil.getMessage("student.last.name"));
        addStudents_col_gender.setText(LanguageUtil.getMessage("student.gender"));
        addStudents_col_birth.setText(LanguageUtil.getMessage("student.birth.date"));
        addStudents_col_status.setText(LanguageUtil.getMessage("student.status"));
        studentLabel.setText(LanguageUtil.getMessage("student.id"));
        yearLabel.setText(LanguageUtil.getMessage("studentAbstence.col_year"));
        fNameLabel.setText(LanguageUtil.getMessage("student.first.name"));
        lNameLabel.setText(LanguageUtil.getMessage("student.last.name"));
        genderLabel.setText(LanguageUtil.getMessage("student.gender"));
        birthDateLabel.setText(LanguageUtil.getMessage("student.birth.date"));
        statusLabel.setText(LanguageUtil.getMessage("student.status"));
        addStudents_search.setPromptText(LanguageUtil.getMessage("search.student"));
        studentAbstence_btn2.setText(LanguageUtil.getMessage("home.totalAbsence"));
        studentSchedule_btn.setText(LanguageUtil.getMessage("schedule.admin.dashboard"));
        addAbsence_col_stid2.setText(LanguageUtil.getMessage("student.id"));
        addAbsence_col_class2.setText(LanguageUtil.getMessage("studentAbstence.col_year"));
        addAbsence_col_firstName2.setText(LanguageUtil.getMessage("student.first.name"));
        addAbsence_col_lastName2.setText(LanguageUtil.getMessage("student.last.name"));
        addAbsence_col_reasonable2.setText(LanguageUtil.getMessage("total.reasonable.absences.forSemester"));
        addAbsence_col_unreasonable2.setText(LanguageUtil.getMessage("total.unreasonable.absences.forSemester"));
        scheduleAdd1.setText(LanguageUtil.getMessage("find.total.absences"));
        helpButton.setText(LanguageUtil.getMessage("help.btn"));

        signout.setText(LanguageUtil.getMessage("signout"));
    }
}