package com.example.finalpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InventoryManagementSystemApplication extends Application {

    private ScreenNavigator screenNavigator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        screenNavigator = new ScreenNavigator(primaryStage);
        screenNavigator.switchToMainForm();
    }
}
