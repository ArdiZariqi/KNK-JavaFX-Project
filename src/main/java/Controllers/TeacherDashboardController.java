package Controllers;

import Models.AbsenceData;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import Models.AbsenceSummary;
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
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.*;

public class TeacherDashboardController implements Initializable {

    private final TeacherDashboardService teacherDashboardService;
    private final TeacherCourseService teacherCourseService;

    private final TeacherAbsenceSummary teacherAbsenceSummary;

    private final AlertService alertService;

    @FXML
    private DatePicker Absence_date;

    @FXML
    private TextField Absence_search1;

    @FXML
    private Button addAbsence_addBtn;

    @FXML
    private ComboBox<?> addAbsence_class;

    @FXML
    private Button addAbsence_clearBtn;

    @FXML
    private TableColumn<AbsenceData,String > addAbsence_col_Course;

    @FXML
    private TableColumn<?, ?> addAbsence_col_Course1;

    @FXML
    private TableColumn<AbsenceData, Integer> addAbsence_col_absenceNum;

    @FXML
    private TableColumn<AbsenceData, Integer> addAbsence_col_absence;
    @FXML
    private TableColumn<AbsenceData, String> addAbsence_col_class;

    @FXML
    private TableColumn<?, ?> addAbsence_col_class1;

    @FXML
    private TableColumn<AbsenceData,String> addAbsence_col_date;

    @FXML
    private TableColumn<AbsenceData,String > addAbsence_col_firstName;

    @FXML
    private TableColumn<?, ?> addAbsence_col_firstName1;

    @FXML
    private TableColumn<AbsenceData, String> addAbsence_col_gender;

    @FXML
    private TableColumn<?, ?> addAbsence_col_gender1;

    @FXML
    private TextField addAbsence_Id;
    @FXML
    private TableColumn<AbsenceData,String > addAbsence_col_lastName;

    @FXML
    private TableColumn<?, ?> addAbsence_col_lastName1;

    @FXML
    private TableColumn<AbsenceData,String > addAbsence_col_reasonability;

    @FXML
    private TableColumn<?, ?> addAbsence_col_reasonable;

    @FXML
    private TableColumn<AbsenceData, String> addAbsence_col_status;

    @FXML
    private TableColumn<?, ?> addAbsence_col_stid;

    @FXML
    private TableColumn<AbsenceData,Integer> addAbsence_col_studentNum;

    @FXML
    private TableColumn<AbsenceData, Integer> addAbsence_col_time;

    @FXML
    private TableColumn<?, ?> addAbsence_col_total;

    @FXML
    private TableColumn<?, ?> addAbsence_col_unreasonable;

    @FXML
    private ComboBox<String> addAbsence_course;

    @FXML
    private Button addAbsence_deleteBtn;
    @FXML
    private Label reasonabilityLabel;

    @FXML
    private TextField addAbsence_search;

    @FXML
    private TextField addAbsence_studentNum;
    @FXML
    private Label label;

    @FXML
    private TextField addAbsence_time;

    @FXML
    private Button addAbsence_updateBtn;

    @FXML
    private ComboBox<?> addStudents_Absences;

    @FXML
    private Button addStudents_btn;

    @FXML
    private TextField addStudents_firstName;
    @FXML
    private TextField addStudents_lastName;
    @FXML
    private Label signout;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<?> addStudents_gender;


    @FXML
    private ComboBox<?> addStudents_status;
    @FXML
    private ComboBox<String> languageId;

    @FXML
    private TableView<AbsenceData> addStudents_tableView;

    @FXML
    private TableView<AbsenceSummary> addStudents_tableView1;

    @FXML
    private Button close;
    @FXML
    private Label timeLabel;

    @FXML
    private Button maximize;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane studentAbstence_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<String,Integer > home_totalEnrolledChart;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;

    @FXML
    private Label home_totalFemale;

    @FXML
    private AreaChart<String,Integer > home_totalFemaleChart;

    @FXML
    private Label home_totalMale;
    @FXML
    private Label courseLabel;

    @FXML
    private LineChart<String,Integer> home_totalMaleChart;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;
    @FXML
    private Label statusLabel;

    @FXML
    private Button minimize;
    @FXML
    private Label classLabel;

    @FXML
    private Button studentAbstence_btn;

    @FXML
    private Label username;

    @FXML
    private Button helpButton;
    @FXML
    private Label home_totalAbsencesLabel;
    @FXML
    private Label home_totalFemaleAbsences;
    @FXML
    private Label home_totalMaleAbsences;
    @FXML
    private Label studentLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label birthDateLabel;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public TeacherDashboardController() {
        teacherDashboardService = new TeacherDashboardService();
        teacherCourseService = new TeacherCourseService();
        teacherAbsenceSummary = new TeacherAbsenceSummary();
        alertService =new AlertService();
    }

    public void homeDisplayTotalStudentsAbsence() {
        try {
            int countEnrolled = teacherDashboardService.getTotalAbsenceCount();
            home_totalEnrolled.setText(String.valueOf(countEnrolled));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayTotalFemaleAbsences() {
        try {
            int countFemale = teacherDashboardService.getTotalFemaleAbsence();
            home_totalFemale.setText(String.valueOf(countFemale));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayTotalMaleAbsences() {
        try {
            int countMale = teacherDashboardService.getTotalMaleAbsence();
            home_totalMale.setText(String.valueOf(countMale));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayTotalAbsenceChart() {
        home_totalEnrolledChart.getData().clear();

        try {
            XYChart.Series<String, Integer> chartData = teacherDashboardService.getTotalAbsenceChart();
            home_totalEnrolledChart.getData().add(chartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayFemaleAbsenceChart() {
        home_totalFemaleChart.getData().clear();

        try {
            XYChart.Series<String, Integer> chartData = teacherDashboardService.getFemaleAbsenceChart();
            home_totalFemaleChart.getData().add(chartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayAbsenceMaleChart() {
        home_totalMaleChart.getData().clear();

        try {
            XYChart.Series<String, Integer> chartData = teacherDashboardService.getMaleAbsenceChart();
            home_totalMaleChart.getData().add(chartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void AbsencesAdd() {
        Alert alert;

        if (addAbsence_studentNum.getText().isEmpty()
                || addAbsence_time.getText().isEmpty()
                || addStudents_Absences.getSelectionModel().getSelectedItem() == null) {
                alertService.errorAlert();
        } else {
            try {

                Integer a_id=1;
                AbsenceData absenceData = new AbsenceData(a_id, Integer.parseInt(addAbsence_studentNum.getText()),
                        Integer.parseInt(addAbsence_time.getText()),
                        (String) addStudents_Absences.getSelectionModel().getSelectedItem());

                teacherDashboardService.addAbsence(absenceData);
                alertService.informationAlert();
                addAbsencesShowListData();

                addAbsencesClear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addAbsencesUpdate() {
        try {
            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_time.getText().isEmpty()
                    || Absence_date.getValue() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null) {
                alertService.errorAlert();
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student Absences #" + addAbsence_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    int a_id = Integer.parseInt(addAbsence_Id.getText());
                    AbsenceData absenceData = new AbsenceData(a_id, Integer.parseInt(addAbsence_studentNum.getText()),
                            Integer.parseInt(addAbsence_time.getText()),
//                            java.sql.Date.valueOf(Absence_date.getValue()),
                            (String) addStudents_Absences.getSelectionModel().getSelectedItem());

                    teacherDashboardService.updateAbsence(absenceData);
                    alertService.updateAlert();
                    addAbsencesShowListData();
                    addAbsencesClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAbsencesDelete() {
        try {
            Alert alert;
            if (addAbsence_Id.getText() == null) {
                alertService.errorAlert();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addAbsence_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    int a_id = Integer.parseInt(addAbsence_Id.getText());
                    AbsenceData absenceData = new AbsenceData(a_id);

                    teacherDashboardService.deleteAbsence(absenceData);
                    alertService.deleteAlert();
                    addAbsencesShowListData();
                    addAbsencesClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addAbsencesClear() {
        addAbsence_Id.setText("");
        addAbsence_studentNum.setText("");
        addAbsence_time.setText("");
        addStudents_Absences.getSelectionModel().clearSelection();


    }
    public void addAbsenceSearch() {
        FilteredList<AbsenceData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addAbsence_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getA_id().toString().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStudent_id().toString().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse_name().toLowerCase().startsWith(searchKey)) {
                    return true;
                }else if (predicateStudentData.getTime().toString().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getDate_().toString().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getReasonability().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<AbsenceData> sortList1 = new SortedList<>(filter);
        sortList1.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList1);
    }
//    public void addAbsencesCourseList() {
//        try {
//            ObservableList<String> listC = teacherCourseService.ListOfCourses();
//            addAbsence_course.setItems(listC);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private String[]  classList ={ "Klasa 9","Klasa 10","Klasa 11","Klasa 12"};
//    public void addStudentsClassList() {
//        List<String> classL = new ArrayList<>();
//
//        for (String data : classList) {
//            classL.add(data);
//        }
//
//        ObservableList ObList = FXCollections.observableArrayList(classL);
//        addAbsence_class.setItems(ObList);
//    }
//    private String[] genderList = {"Male", "Female"};

//    public void addStudentsGenderList() {
//        List<String> genderL = new ArrayList<>();
//
//        for (String data : genderList) {
//            genderL.add(data);
//        }
//
//        ObservableList ObList = FXCollections.observableArrayList(genderL);
//        addStudents_gender.setItems(ObList);
//    }
//    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};
//    public void addStudentsStatusList() {
//        List<String> statusL = new ArrayList<>();
//
//        for (String data : statusList) {
//            statusL.add(data);
//        }
//
//        ObservableList ObList = FXCollections.observableArrayList(statusL);
//        addStudents_status.setItems(ObList);
//    }
    private String[] reasonabilityList = {"Reasonable", "Unreasonable"};

    public void AbsenceList() {
        List<String> reasonabilityL = new ArrayList<>();

        for (String data : reasonabilityList) {
            reasonabilityL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(reasonabilityL);
        addStudents_Absences.setItems(ObList);
    }
    public ObservableList<AbsenceData> addAbsencesListData() {
        ObservableList<AbsenceData> listStudents = null;

        try {
            listStudents = teacherDashboardService.AbsencesListData();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException here (e.g., show an error message, log the exception, etc.)
        }

        return listStudents;
    }
    private ObservableList<AbsenceData> addStudentsListD;
    public void addAbsencesShowListData() {
        addStudentsListD = addAbsencesListData();

        addAbsence_col_absence.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        addAbsence_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        addAbsence_col_class.setCellValueFactory(new PropertyValueFactory<>("year"));
        addAbsence_col_Course.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        addAbsence_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        addAbsence_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addAbsence_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addAbsence_col_gender.setCellValueFactory(new PropertyValueFactory<>("day"));
        addAbsence_col_date.setCellValueFactory(new PropertyValueFactory<>("date_"));
        addAbsence_col_reasonability.setCellValueFactory(new PropertyValueFactory<>("reasonability"));

        addStudents_tableView.setItems(addStudentsListD);

    }
    public void addStudentsSelect() {

        AbsenceData studentD = (AbsenceData) addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) <-1) {
            return;
        }

        addAbsence_Id.setText(String.valueOf(studentD.getA_id()));
        addAbsence_studentNum.setText(String.valueOf(studentD.getStudent_id()));
        addAbsence_time.setText(String.valueOf(studentD.getTime()));

    }
    public void addAbsenceSearch1() {
        FilteredList<AbsenceSummary> filter = new FilteredList<>(addStudentsListD1, e -> true);

        Absence_search1.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudent_id().toString().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getClass_().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse_name().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().startsWith(searchKey)) {
                    return true;
                } else{
                    return false;
                }
            });
        });

        SortedList<AbsenceSummary> sortList1 = new SortedList<>(filter);
        sortList1.comparatorProperty().bind(addStudents_tableView1.comparatorProperty());
        addStudents_tableView1.setItems(sortList1);
    }

    public ObservableList<AbsenceSummary> addAbsencesListData1() {
        ObservableList<AbsenceSummary> listStudents = null;

        try {
            listStudents = teacherAbsenceSummary.addAbsencesList1();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listStudents;
    }
    private ObservableList<AbsenceSummary> addStudentsListD1;
    public void addAbsencesShowListData1() {
        addStudentsListD1 = addAbsencesListData1();

        addAbsence_col_stid.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        addAbsence_col_class1.setCellValueFactory(new PropertyValueFactory<>("class_"));
        addAbsence_col_Course1.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        addAbsence_col_firstName1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addAbsence_col_lastName1.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addAbsence_col_gender1.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addAbsence_col_reasonable.setCellValueFactory(new PropertyValueFactory<>("total_reasonable_absences"));
        addAbsence_col_unreasonable.setCellValueFactory(new PropertyValueFactory<>("total_unreasonable_absences"));
        addAbsence_col_total.setCellValueFactory(new PropertyValueFactory<>("total_absences"));

        addStudents_tableView1.setItems(addStudentsListD1);
    }
    private double x = 0;
    private double y = 0;
    public void logout() {
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/login.fxml"));
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
    public void displayUsername(){
        username.setText(getData.username);
    }
    public void defaultNav(){
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }
    @FXML
    public void openHelp(ActionEvent event){
        try {
            if (event.getSource() == helpButton ){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/helpn.fxml"));
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
    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            studentAbstence_form.setVisible(false);


            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalStudentsAbsence();
            homeDisplayTotalFemaleAbsences();
            homeDisplayTotalMaleAbsences();
            homeDisplayTotalAbsenceChart();
            homeDisplayFemaleAbsenceChart();
            homeDisplayAbsenceMaleChart();

        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            studentAbstence_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");

            addAbsencesShowListData();
//            addStudentsClassList();
//            addAbsencesCourseList();
//            addStudentsGenderList();
//            addStudentsStatusList();
            AbsenceList();
            addAbsenceSearch();


        } else if (event.getSource() == studentAbstence_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentAbstence_form.setVisible(true);

            studentAbstence_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            addAbsencesShowListData1();
            addAbsenceSearch1();
        }
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
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        defaultNav();

        homeDisplayTotalStudentsAbsence();
        homeDisplayTotalFemaleAbsences();
        homeDisplayTotalMaleAbsences();
        homeDisplayTotalAbsenceChart();
        homeDisplayFemaleAbsenceChart();
        homeDisplayAbsenceMaleChart();

        addAbsencesShowListData();
//        addStudentsGenderList();
//        addStudentsStatusList();
//        addAbsencesCourseList();
//        addStudentsClassList();
        AbsenceList();

        languageId.setItems(FXCollections.observableArrayList("English", "Shqip"));
        languageId.setValue("English");
        languageId.setOnAction(e -> {
            setLanguage();
        });

        setLanguage();
    }
    public void setLanguage() {

        String selectedLanguage = languageId.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        home_btn.setText(LanguageUtil.getMessage("home.btn"));
        home_totalAbsencesLabel.setText(LanguageUtil.getMessage("home.totalAbsence"));
        home_totalFemaleAbsences.setText(LanguageUtil.getMessage("home.totalFemale.Absences"));
        home_totalMaleAbsences.setText(LanguageUtil.getMessage("home.totalMale.Absences"));
        home_totalEnrolledChart.setTitle(LanguageUtil.getMessage("home.totalAbsence"));
        home_totalFemaleChart.setTitle(LanguageUtil.getMessage("tot.female.absences"));
        home_totalMaleChart.setTitle(LanguageUtil.getMessage("tot.male.absences"));
        addStudents_btn.setText(LanguageUtil.getMessage("add.absences.btn"));
        addAbsence_addBtn.setText(LanguageUtil.getMessage("addStudents.addBtn"));
        addAbsence_clearBtn.setText(LanguageUtil.getMessage("addStudents.clearBtn"));
        addAbsence_deleteBtn.setText(LanguageUtil.getMessage("addStudents.deleteBtn"));
        addAbsence_updateBtn.setText(LanguageUtil.getMessage("addStudents.updateBtn"));
//        addAbsence_col_firstName.setText(LanguageUtil.getMessage("student.first.name"));
        addAbsence_col_firstName1.setText(LanguageUtil.getMessage("student.first.name"));
//        addAbsence_col_lastName.setText(LanguageUtil.getMessage("student.last.name"));
        addAbsence_col_lastName1.setText(LanguageUtil.getMessage("student.last.name"));
//        addAbsence_col_gender.setText(LanguageUtil.getMessage("student.gender"));
        addAbsence_col_gender1.setText(LanguageUtil.getMessage("student.gender"));
        addAbsence_col_date.setText(LanguageUtil.getMessage("student.date"));
//        addAbsence_col_status.setText(LanguageUtil.getMessage("student.status"));
        addAbsence_col_reasonability.setText(LanguageUtil.getMessage("add.absence.reasonability"));
        addAbsence_col_reasonable.setText(LanguageUtil.getMessage("total.reasonable"));
        addAbsence_col_unreasonable.setText(LanguageUtil.getMessage("total.unreasonable"));
        addAbsence_col_total.setText(LanguageUtil.getMessage("total.absences"));
        studentLabel.setText(LanguageUtil.getMessage("student.id"));
//        classLabel.setText(LanguageUtil.getMessage("class.label"));
        timeLabel.setText(LanguageUtil.getMessage("time.label"));
        label.setText(LanguageUtil.getMessage("label."));
        reasonabilityLabel.setText(LanguageUtil.getMessage("add.absence.reasonability"));
        addAbsence_col_Course1.setText(LanguageUtil.getMessage("studentAbstence.col_course"));
        addAbsence_col_class1.setText(LanguageUtil.getMessage("class.col"));
        addAbsence_col_stid.setText(LanguageUtil.getMessage("student.id"));
//        addAbsence_class.setPromptText(LanguageUtil.getMessage("select.class"));
//        addAbsence_course.setPromptText(LanguageUtil.getMessage("select.course"));
//        addStudents_gender.setPromptText(LanguageUtil.getMessage("select.gender"));
//        addStudents_status.setPromptText(LanguageUtil.getMessage("select.status"));
        addStudents_Absences.setPromptText(LanguageUtil.getMessage("select.reasonability"));
//        courseLabel.setText(LanguageUtil.getMessage("studentAbstence.col_course"));
//        fNameLabel.setText(LanguageUtil.getMessage("student.first.name"));
//        lNameLabel.setText(LanguageUtil.getMessage("student.last.name"));
//        genderLabel.setText(LanguageUtil.getMessage("student.gender"));
//        birthDateLabel.setText(LanguageUtil.getMessage("student.birth.date"));
//        statusLabel.setText(LanguageUtil.getMessage("student.status"));
        Absence_search1.setPromptText(LanguageUtil.getMessage("search.student"));
        addAbsence_search.setPromptText(LanguageUtil.getMessage("search.student"));
        studentAbstence_btn.setText(LanguageUtil.getMessage("student.absences"));
        addAbsence_col_Course.setText(LanguageUtil.getMessage("studentAbstence.col_course"));
        addAbsence_col_class.setText(LanguageUtil.getMessage("class.col"));
        addAbsence_col_time.setText(LanguageUtil.getMessage("time.col"));
        addAbsence_col_studentNum.setText(LanguageUtil.getMessage("student.id"));
        addAbsence_col_absence.setText(LanguageUtil.getMessage("absence.id"));
        signout.setText(LanguageUtil.getMessage("signout"));
        helpButton.setText(LanguageUtil.getMessage("help.btn"));
    }
}