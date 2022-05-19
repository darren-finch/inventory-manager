package com.example.finalpa;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ScreenNavigator {
    private final Stage stage;

    ScreenNavigator(Stage stage) {
        this.stage = stage;
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
            controller.initialize(this);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
}
