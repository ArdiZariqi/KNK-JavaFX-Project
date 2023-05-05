module com.example.knk_projekti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.knk_projekti to javafx.fxml;
    opens KNK_Projekti to javafx.fxml;
    exports com.example.knk_projekti;
    exports Projekti_KNK to javafx.graphics;
    exports KNK_Projekti to javafx.graphics;
}