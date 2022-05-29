package com.example.inventorymanagementsystem;

import com.example.inventorymanagementsystem.data.Inventory;
import com.example.inventorymanagementsystem.di.BaseControllerConfig;
import javafx.application.Application;
import javafx.stage.Stage;

public class InventoryManagementSystemApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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
