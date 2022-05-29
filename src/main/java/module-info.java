module com.example.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.inventorymanagementsystem.controllers to javafx.fxml;
    exports com.example.inventorymanagementsystem.data;
    opens com.example.inventorymanagementsystem.data to javafx.fxml;
    exports com.example.inventorymanagementsystem to javafx.graphics;
    opens com.example.inventorymanagementsystem.controllers.validation to javafx.fxml;
    opens com.example.inventorymanagementsystem to javafx.fxml;
}