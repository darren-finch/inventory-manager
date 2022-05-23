module com.example.finalpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;

    exports com.example.finalpa.controllers;
    opens com.example.finalpa.controllers to javafx.fxml;
    exports com.example.finalpa.data;
    opens com.example.finalpa.data to javafx.fxml;
}