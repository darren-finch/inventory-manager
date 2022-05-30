package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Inventory;
import com.example.inventorymanagementsystem.di.BaseControllerConfig;
import com.example.inventorymanagementsystem.DialogManager;
import com.example.inventorymanagementsystem.ScreenNavigator;

public abstract class BaseController {
    private BaseControllerConfig controllerConfig;

    public final void setupConfig(BaseControllerConfig controllerConfig) {
        if (controllerConfig == null) {
            throw new RuntimeException("BaseControllerConfig not initialized!");
        }

        this.controllerConfig = controllerConfig;
    }

    public abstract void setupUI();

    public ScreenNavigator getScreenNavigator() {
        return controllerConfig.getScreenNavigator();
    }
    public Inventory getInventory() { return controllerConfig.getInventory(); }

    public DialogManager getDialogManager() { return controllerConfig.getDialogManager(); }
}
