module eu.telecomnancy.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;
    opens eu.telecomnancy.javafx.controller to javafx.fxml;

    exports eu.telecomnancy.javafx;
    exports eu.telecomnancy.javafx.controller;
}
