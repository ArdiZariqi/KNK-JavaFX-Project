module KNK.Projekti {

    opens Models to javafx.base;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
//    requires mysql.connector.java;
    requires fontawesomefx;

    opens Controllers;
    opens KNK_Projekti;
    opens DBconnection;
}