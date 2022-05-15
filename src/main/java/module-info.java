module com.example.finalpa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalpa to javafx.fxml;
    exports com.example.finalpa;
}