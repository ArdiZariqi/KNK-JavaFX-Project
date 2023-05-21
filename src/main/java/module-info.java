module KNK.Projekti {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
//    requires mysql.connector.java;
//    requires fontawesomefx;
    requires java.desktop;

    opens Controllers;
    opens KNK_Projekti;
    opens DBconnection;
}