module com.example.finalpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.finalpa to javafx.fxml;
    exports com.example.finalpa;
}