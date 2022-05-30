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

/**
 * Allows controllers to easily navigate between screens without messing with the guts of FXMLLoader
 */
public class ScreenNavigator {
    /**
     * The current stage
     */
    private final Stage stage;

    /**
     * The base DI configuration passed to all controllers
     */
    private BaseControllerConfig controllerConfig;

    /**
     * Constructs a new ScreenNavigator
     * @param stage the starting stage
     */
    public ScreenNavigator(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the DI configuration for all controllers
     * @param controllerConfig the configuration to set
     */
    public void setControllerConfig(BaseControllerConfig controllerConfig) {
        this.controllerConfig = controllerConfig;
    }

    /**
     * Switch to the Main form
     */
    public void switchToMainForm() {
        BaseController controller = switchToScreen("main-form.fxml");
        controller.setupUI();
    }

    /**
     * Switch to the EditPart form
     */
    public void switchToEditPartForm(int index, Part part) {
        EditPartFormController controller = (EditPartFormController) switchToScreen("edit-part-form.fxml");
        controller.setArgs(index, part);
        controller.setupUI();
    }

    /**
     * Switch to the EditProduct form
     */
    public void switchToEditProductForm(int index, Product product) {
        EditProductFormController controller = (EditProductFormController) switchToScreen("edit-product-form.fxml");
        controller.setArgs(index, product);
        controller.setupUI();
    }

    /**
     * Switches to a generic screen, providing it with a basic level of DI
     * @param screenResourceName the name of the FXML file for the new screen
     */
    private BaseController switchToScreen(String screenResourceName) {
        BaseController newController;
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(screenResourceName)));
            Parent root = loader.load();

            newController = loader.getController();
            newController.setupConfig(controllerConfig);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newController;
    }
}
