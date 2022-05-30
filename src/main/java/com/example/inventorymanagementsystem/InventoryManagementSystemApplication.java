package com.example.inventorymanagementsystem;

import com.example.inventorymanagementsystem.data.Inventory;
import com.example.inventorymanagementsystem.di.BaseControllerConfig;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main application
 */
public class InventoryManagementSystemApplication extends Application {

    /**
     * The entry point for the application
     * @param args the arguments passed to the application from the command line
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application and sets up dependencies
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        ScreenNavigator screenNavigator = new ScreenNavigator(primaryStage);
        Inventory inventory = new Inventory();
        DialogManager dialogManager = new DialogManager();

        BaseControllerConfig controllerConfig = new BaseControllerConfig(screenNavigator, inventory, dialogManager);
        screenNavigator.setControllerConfig(controllerConfig);

        screenNavigator.switchToMainForm();
    }
}
