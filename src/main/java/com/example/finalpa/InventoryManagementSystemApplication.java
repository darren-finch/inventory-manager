package com.example.finalpa;

import com.example.finalpa.data.Inventory;
import com.example.finalpa.di.BaseControllerConfig;
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

        BaseControllerConfig controllerConfig = new BaseControllerConfig(screenNavigator, inventory);
        screenNavigator.setControllerConfig(controllerConfig);

        screenNavigator.switchToMainForm();
    }
}
