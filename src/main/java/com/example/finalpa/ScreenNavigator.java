package com.example.finalpa;

import com.example.finalpa.controllers.BaseController;
import com.example.finalpa.controllers.EditPartFormController;
import com.example.finalpa.controllers.EditProductFormController;
import com.example.finalpa.controllers.MainFormController;
import com.example.finalpa.data.Part;
import com.example.finalpa.data.Product;
import com.example.finalpa.di.BaseControllerConfig;
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
        switchToScreen("main-form.fxml");
    }

    public void switchToEditPartForm(Part part) {
        EditPartFormController controller = (EditPartFormController) switchToScreen("edit-part-form.fxml");
        controller.setArgs(part);
    }

    public void switchToEditProductForm(Product product) {
        EditProductFormController controller = (EditProductFormController) switchToScreen("edit-product-form.fxml");
        controller.setArgs(product);
    }

    private BaseController switchToScreen(String screenResourceName) {
        BaseController controller;
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(screenResourceName)));
            Parent root = loader.load();

            controller = loader.getController();
            // This is a hack for dependency injection that is pretty dirty, but it will work for this project.
            controller.setupConfig(controllerConfig);
            controller.setupUI();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
}
