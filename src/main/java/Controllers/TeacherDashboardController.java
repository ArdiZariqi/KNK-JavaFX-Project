package Controllers;
import Models.AbsenceData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import DBconnection.connectDb;
import Models.AbsenceSummary;
import Models.courseData;
import Models.getData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class TeacherDashboardController implements Initializable {

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
    private ComboBox<?> addAbsence_course;

    @FXML
    private Button addAbsence_deleteBtn;

    @FXML
    private TextField addAbsence_search;

    @FXML
    private TextField addAbsence_studentNum;

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
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private TableView<AbsenceData> addStudents_tableView;

    @FXML
    private TableView<AbsenceSummary> addStudents_tableView1;

    @FXML
    private Button close;

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
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button studentAbstence_btn;

    @FXML
    private Label username;

    @FXML
    private Button helpButton;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;



    public void homeDisplayTotalStudentsAbsence() {

        String sql = "SELECT COUNT(a_id) FROM Absences";

        connect = connectDb.getConnection();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("COUNT(a_id)");
            }

            home_totalEnrolled.setText(String.valueOf(countEnrolled));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalFemaleAbsences() {

        String sql = "SELECT COUNT(a_id) FROM Absences WHERE gender = 'Female' and status = 'Enrolled'";

        connect = connectDb.getConnection();

        try {
            int countFemale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("COUNT(a_id)");
            }

            home_totalFemale.setText(String.valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalMaleAbsences() {

        String sql = "SELECT COUNT(a_id) FROM Absences WHERE gender = 'Male' and status = 'Enrolled'";

        connect = connectDb.getConnection();
        try {
            int countMale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("COUNT(a_id)");
            }
            home_totalMale.setText(String.valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalAbsenceChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT date_, COUNT(a_id) FROM Absences WHERE status = 'Enrolled' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";

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

    public void homeDisplayFemaleAbsenceChart() {

        home_totalFemaleChart.getData().clear();

        String sql = "SELECT date_, COUNT(a_id) FROM Absences WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";

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

    public void homeDisplayAbsenceMaleChart() {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT date_, COUNT(a_id) FROM Absences WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";

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

    public void AbsencesAdd() {

        String insertData = "INSERT INTO Absences " +
                "(student_id, class_, course_name, time, firstName, lastName, gender, date_, status, reasonability,date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        connect = connectDb.getConnection();

        try {
            Alert alert;

            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_class.getSelectionModel().getSelectedItem() == null
                    || addAbsence_course.getSelectionModel().getSelectedItem() == null
                    || addAbsence_time.getText().isEmpty()
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || Absence_date.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF THE STUDENTNUMBER IS ALREADY EXIST


                statement = connect.createStatement();


                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addAbsence_studentNum.getText());
                    prepare.setString(2, (String) addAbsence_class.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addAbsence_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addAbsence_time.getText());
                    prepare.setString(5, addStudents_firstName.getText());
                    prepare.setString(6, addStudents_lastName.getText());
                    prepare.setString(7, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(Absence_date.getValue()));
                    prepare.setString(9, (String) addStudents_status.getSelectionModel().getSelectedItem());

                    // Set the missing reasonability value
                    prepare.setString(10, (String) addStudents_Absences.getSelectionModel().getSelectedItem());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(11, String.valueOf(sqlDate));

                    prepare.executeUpdate();


                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addAbsencesShowListData();
                    // TO CLEAR THE FIELDS
                    addAbsencesClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAbsencesUpdate() {


        String updateData = "UPDATE Absences SET "
                + "student_id = '" + addAbsence_studentNum.getText()
                + "', class_ = '" + addAbsence_class.getSelectionModel().getSelectedItem()
                + "', course_name = '" + addAbsence_course.getSelectionModel().getSelectedItem()
                + "', time = '" + addAbsence_time.getText()
                + "', firstName = '" + addStudents_firstName.getText()
                + "', lastName = '" + addStudents_firstName.getText()
                + "', gender = '" + addStudents_gender.getSelectionModel().getSelectedItem()
                + "', date_ = '" + Absence_date.getValue()
                + "', status = '" + addStudents_status.getSelectionModel().getSelectedItem()
                + "', reasonability = '" + addStudents_Absences.getSelectionModel().getSelectedItem()
                + "' where a_id = '"
                + addAbsence_Id.getText() + "'";


        connect = connectDb.getConnection();

        try {
            Alert alert;
            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_class.getSelectionModel().getSelectedItem() == null
                    || addAbsence_course.getSelectionModel().getSelectedItem() == null
                    || addAbsence_time.getText().isEmpty()
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || Absence_date.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null
                    ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student Absences #" + addAbsence_studentNum.getText() + "?");
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
                    addAbsencesShowListData();
                    // TO CLEAR THE FIELDS
                    addAbsencesClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addAbsencesDelete() {

        String deleteData = "DELETE FROM Absences WHERE a_id = '"
                + addAbsence_Id.getText() + "'";

        connect = connectDb.getConnection();

        try {
            Alert alert;
            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_class.getSelectionModel().getSelectedItem() == null
                    || addAbsence_course.getSelectionModel().getSelectedItem() == null
                    || addAbsence_time.getText().isEmpty()
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || Absence_date.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addAbsence_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);




                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addAbsencesShowListData();
                    // TO CLEAR THE FIELDS
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
        addAbsence_class.getSelectionModel().clearSelection();
        addAbsence_course.getSelectionModel().clearSelection();
        addAbsence_time.setText("");
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        Absence_date.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
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
                } else if (predicateStudentData.getClass_().toLowerCase().startsWith(searchKey)) {
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


    public void addAbsencesCourseList() {

        String listCourse = "SELECT * FROM course";

        connect = connectDb.getConnection();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addAbsence_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   private String[]  classList ={ "Klasa 9","Klasa 10","Klasa 11","Klasa 12"};
    public void addStudentsClassList() {
        List<String> classL = new ArrayList<>();

        for (String data : classList) {
            classL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(classL);
        addAbsence_class.setItems(ObList);
    }



    private String[] genderList = {"Male", "Female"};

    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }
    //Koment
    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};

    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }
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

        ObservableList<AbsenceData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM Absences";

        connect = connectDb.getConnection();

        try {
            AbsenceData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new AbsenceData(
                        result.getInt("a_id"),
                        result.getInt("student_id"),
                        result.getString("class_"),
                        result.getString("course_name"),
                        result.getInt("time"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("date_"),
                        result.getString("status"),
                        result.getString("reasonability"));

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    private ObservableList<AbsenceData> addStudentsListD;

    public void addAbsencesShowListData() {
        addStudentsListD = addAbsencesListData();

        addAbsence_col_absence.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        addAbsence_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        addAbsence_col_class.setCellValueFactory(new PropertyValueFactory<>("class_"));
        addAbsence_col_Course.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        addAbsence_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        addAbsence_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addAbsence_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addAbsence_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addAbsence_col_date.setCellValueFactory(new PropertyValueFactory<>("date_"));
        addAbsence_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
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
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        Absence_date.setValue(LocalDate.parse(String.valueOf(studentD.getDate_())));

    }

    //Page 3
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
        ObservableList<AbsenceSummary> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT *from  AbsenceSummary";

        connect = connectDb.getConnection();

        try {
            AbsenceSummary studentD1;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD1 = new AbsenceSummary(
                        result.getInt("student_id"),
                        result.getString("class_"),
                        result.getString("course_name"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getInt("total_reasonable_absences"),
                        result.getInt("total_unreasonable_absences"),
                        result.getInt("total_absences"));

                listStudents.add(studentD1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources in the finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("AdminPortal.fxml"));
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

//            TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV


            addAbsencesShowListData();
            addStudentsClassList();
            addAbsencesCourseList();
            addStudentsGenderList();
            addStudentsStatusList();
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

    private double previousWidth;
    private double previousHeight;

    public void maximize() {
        Stage stage = (Stage) main_form.getScene().getWindow();

        if (stage.isMaximized()) {
            // Restore the previous size
            stage.setMaximized(false);
        } else {
            // Maximize the window
            stage.setMaximized(true);
        }
    }




    // SORRY ABOUT THAT, I JUST NAMED THE DIFFERENT COMPONENTS WITH THE SAME NAME
    // MAKE SURE THAT THE NAME YOU GAVE TO THEM ARE DIFFERENT TO THE OTHER OKAY?
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

        // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
        addAbsencesShowListData();
        addStudentsGenderList();
        addStudentsStatusList();
        addAbsencesCourseList();
        addStudentsClassList();
        AbsenceList();


    }

}