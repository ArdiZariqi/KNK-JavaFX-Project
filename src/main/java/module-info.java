module KNK.Projekti {

    opens Models to javafx.base;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.j;
//    requires fontawesomefx;
    requires java.desktop;

    opens Controllers;
    opens KNK_Projekti;
}