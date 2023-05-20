package Controllers;
import DBconnection.connectDb;
import Models.getData;
import Models.studentData;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class AdminController implements Initializable {

        @FXML
        private Button addStudents_addBtn;

        @FXML
        private DatePicker addStudents_birth;

        @FXML
        private Button addStudents_btn;

        @FXML
        private Button addStudents_clearBtn;

        @FXML
        private TableColumn<?, ?> addStudents_col_birth;

        @FXML
        private TableColumn<?, ?> addStudents_col_course;

        @FXML
        private TableColumn<?, ?> addStudents_col_firstName;

        @FXML
        private TableColumn<?, ?> addStudents_col_gender;

        @FXML
        private TableColumn<?, ?> addStudents_col_lastName;

        @FXML
        private TableColumn<?, ?> addStudents_col_status;

        @FXML
        private TableColumn<?, ?> addStudents_col_studentNum;

        @FXML
        private TableColumn<?, ?> addStudents_col_year;

        @FXML
        private ComboBox<?> addStudents_course;

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
        private TableView<?> addStudents_tableView;

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
        private TableColumn<?, ?> studentAbstence_col_course;

        @FXML
        private TableColumn<?, ?> studentAbstence_col_final;

        @FXML
        private TableColumn<?, ?> studentAbstence_col_firstSem;

        @FXML
        private TableColumn<?, ?> studentAbstence_col_secondSem;

        @FXML
        private TableColumn<?, ?> studentAbstence_col_studentNum;

        @FXML
        private TableColumn<?, ?> studentAbstence_col_year;

        @FXML
        private AnchorPane studentAbstence_form;

        @FXML
        private TextField studentAbstence_search;

        @FXML
        private TableView<?> studentAbstence_tableView;

        @FXML
        private Label username;

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
                alert = new Alert(Alert.AlertType.ERROR);
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
                    alert = new Alert(Alert.AlertType.ERROR);
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

                    alert = new Alert(Alert.AlertType.INFORMATION);
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
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
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
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
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

                    alert = new Alert(Alert.AlertType.INFORMATION);
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
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

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

    private String[] yearList = {"First Year", "Second Year", "Third Year", "Fourth Year"};

    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        for (String data : yearList) {
            yearL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);

    }

    public void addStudentsCourseList() {

        String listCourse = "SELECT * FROM course";

        connect = connectDb.getConnection();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] genderList = {"Male", "Female", "Others"};

    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }

    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};

    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }

    //    NOW WE NEED THE COURSE, SO LETS WORK NOW THE AVAILABLE COURSE FORM : )
//    LETS WORK FIRST THE ADD STUDENTS FORM : )
    public ObservableList<studentData> addStudentsListData() {

        ObservableList<studentData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";

        connect = connectDb.getConnection();

        try {
            studentData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("birth"),
                        result.getString("status"),
                        result.getString("image"));

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    private ObservableList<studentData> addStudentsListD;

    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);

    }

    public void addStudentsSelect() {

        studentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_studentNum.setText(String.valueOf(studentD.getStudentNum()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birth.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));

        String uri = "file:" + studentD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addStudents_imageView.setImage(image);

        getData.path = studentD.getImage();

    }

    public ObservableList<studentData> studentAbstencesListData() {

        ObservableList<studentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_Abstence";

        connect = connectDb.getConnection();
        try {
            studentData studentD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getDouble("first_sem"),
                        result.getDouble("second_sem"),
                        result.getDouble("final"));

                listData.add(studentD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<studentData> studentAbstencesList;

    public void studentAbstencesShowListData() {
        studentAbstencesList = studentAbstencesListData();

        studentAbstence_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        studentAbstence_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentAbstence_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentAbstence_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
        studentAbstence_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
        studentAbstence_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));
//        WE NEED TO FIX THE DELETE ON ADD STUDENT FORM
        studentAbstence_tableView.setItems(studentAbstencesList);

    }

    public void studentAbstencesSearch() {

        FilteredList<studentData> filter = new FilteredList<>(studentAbstencesList, e -> true);

        studentAbstence_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstSem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSecondSem().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFinals().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(studentAbstence_tableView.comparatorProperty());
        studentAbstence_tableView.setItems(sortList);

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

        @FXML
        void addStudentsAdd(ActionEvent event) {

        }

        @FXML
        void addStudentsClear(ActionEvent event) {

        }

        @FXML
        void addStudentsCourseList(ActionEvent event) {

        }

        @FXML
        void addStudentsDelete(ActionEvent event) {

        }

        @FXML
        void addStudentsGenderList(ActionEvent event) {

        }

        @FXML
        void addStudentsInsertImage(ActionEvent event) {

        }

        @FXML
        void addStudentsSearch(KeyEvent event) {

        }

        @FXML
        void addStudentsSelect(MouseEvent event) {

        }

        @FXML
        void addStudentsStatusList(ActionEvent event) {

        }

        @FXML
        void addStudentsUpdate(ActionEvent event) {

        }

        @FXML
        void addStudentsYearList(ActionEvent event) {

        }

        @FXML
        void close(ActionEvent event) {

        }

        @FXML
        void logout(ActionEvent event) {

        }

        @FXML
        void minimize(ActionEvent event) {

        }

        @FXML
        void studentAbstencesSearch(KeyEvent event) {

        }

        @FXML
        void studentAbstencesSelect(MouseEvent event) {

        }

        @FXML
        void switchForm(ActionEvent event) {

        }
    public void initialize(URL location, ResourceBundle resources){

        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayEnrolledMaleChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayTotalEnrolledChart();

        // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
        addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        addStudentsCourseList();

        studentAbstencesShowListData();

    }

}




