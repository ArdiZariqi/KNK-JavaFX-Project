 package Projekti_KNK;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the main window
        primaryStage.setTitle("Absence Management System");
        root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Add menus to the menu bar
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(new MenuItem("Open"), new MenuItem("Save"), new SeparatorMenuItem(), new MenuItem("Exit"));

        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(new MenuItem("Undo"), new MenuItem("Redo"), new SeparatorMenuItem(), new MenuItem("Cut"), new MenuItem("Copy"), new MenuItem("Paste"));

        Menu viewMenu = new Menu("View");
        viewMenu.getItems().addAll(new MenuItem("Zoom In"), new MenuItem("Zoom Out"), new SeparatorMenuItem(), new MenuItem("Dark Mode"), new MenuItem("Light Mode"));

        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().addAll(new MenuItem("Documentation"), new MenuItem("About"));

        // Add the menu bar to the main window
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);
        root.setTop(menuBar);

        // Add tools to the main window
        ListView<String> studentList = new ListView<>();
        studentList.getItems().addAll("Alice", "Bob", "Charlie");

        ListView<String> absenceList = new ListView<>();
        absenceList.getItems().addAll("Alice - 2022-01-01", "Bob - 2022-01-02", "Charlie - 2022-01-03");

        DatePicker datePicker = new DatePicker();

        // Add the tools to the main window
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(studentList, absenceList, datePicker);
        root.setCenter(splitPane);

        // Add a status bar to the main window
        Label statusLabel = new Label("Ready");
        root.setBottom(statusLabel);

        // Add a shortcut menu to the student list
        studentList.setContextMenu(new ContextMenu(new MenuItem("Add Student"), new MenuItem("Edit Student"), new MenuItem("Delete Student")));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
