package KNK_Projekti;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application {
    private double x = 0;
    private double y = 0;
    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);

        KeyCombination minimizeKeyCombination = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
        KeyCombination closeKeyCombination = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);

        scene.setOnKeyPressed(event-> {
            if (minimizeKeyCombination.match(event)) {
                stage.setIconified(true);
            }
        });

        root.setOnKeyPressed(event-> {
            if (closeKeyCombination.match(event)) {
                System.exit(0);
            }
        });


        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}

