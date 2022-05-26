module com.example.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens com.example.inventorymanagementsystem.controllers to javafx.fxml;
    exports com.example.inventorymanagementsystem.data;
    opens com.example.inventorymanagementsystem.data to javafx.fxml;
    exports com.example.inventorymanagementsystem to javafx.graphics;
    exports com.example.inventorymanagementsystem.services to javafx.graphics;
    opens com.example.inventorymanagementsystem.controllers.validation to javafx.fxml;
}