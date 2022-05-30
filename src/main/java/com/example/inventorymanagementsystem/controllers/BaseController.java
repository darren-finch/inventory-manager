package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Inventory;
import com.example.inventorymanagementsystem.di.BaseControllerConfig;
import com.example.inventorymanagementsystem.DialogManager;
import com.example.inventorymanagementsystem.ScreenNavigator;

/**
 * Contains a set of basic functionality for all controllers
 */
public abstract class BaseController {
    /**
     * The base DI configuration
     */
    private BaseControllerConfig controllerConfig;

    /**
     * @param controllerConfig The base DI configuration to set
     */
    public final void setupConfig(BaseControllerConfig controllerConfig) {
        if (controllerConfig == null) {
            throw new RuntimeException("BaseControllerConfig not initialized!");
        }

        this.controllerConfig = controllerConfig;
    }

    /**
     * Called after the UI is ready to access. Can be used to setup any necessary UI components
     */
    public abstract void setupUI();

    /**
     * @return The ScreenNavigator service
     */
    public ScreenNavigator getScreenNavigator() {
        return controllerConfig.getScreenNavigator();
    }

    /**
     * @return The Inventory object
     */
    public Inventory getInventory() { return controllerConfig.getInventory(); }

    /**
     * @return The DialogManager service
     */
    public DialogManager getDialogManager() { return controllerConfig.getDialogManager(); }
}
