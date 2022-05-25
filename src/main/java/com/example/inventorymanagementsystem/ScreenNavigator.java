package com.example.inventorymanagementsystem;

import com.example.inventorymanagementsystem.controllers.BaseController;
import com.example.inventorymanagementsystem.controllers.EditPartFormController;
import com.example.inventorymanagementsystem.controllers.EditProductFormController;
import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import com.example.inventorymanagementsystem.di.BaseControllerConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ScreenNavigator {
    private final Stage stage;

    private BaseControllerConfig controllerConfig;

    public ScreenNavigator(Stage stage) {
        this.stage = stage;
    }

    public void setControllerConfig(BaseControllerConfig controllerConfig) {
        this.controllerConfig = controllerConfig;
    }

    public void switchToMainForm() {
        BaseController controller = switchToScreen("main-form.fxml");
        controller.setupUI(); // this definitely needs to be refactored but for now it works
    }

    public void switchToEditPartForm(int index, Part part) {
        EditPartFormController controller = (EditPartFormController) switchToScreen("edit-part-form.fxml");
        controller.setArgs(index, part);
        controller.setupUI(); // this definitely needs to be refactored but for now it works
    }

    public void switchToEditProductForm(int index, Product product) {
        EditProductFormController controller = (EditProductFormController) switchToScreen("edit-product-form.fxml");
        controller.setArgs(index, product);
        controller.setupUI(); // this definitely needs to be refactored but for now it works
    }

    private BaseController switchToScreen(String screenResourceName) {
        BaseController controller;
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(screenResourceName)));
            Parent root = loader.load();

            controller = loader.getController();
            // This is a hack for dependency injection that is pretty dirty, but it will work for this project.
            controller.setupConfig(controllerConfig);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
}
