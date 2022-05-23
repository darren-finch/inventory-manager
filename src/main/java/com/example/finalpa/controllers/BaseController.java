package com.example.finalpa.controllers;

import com.example.finalpa.data.Inventory;
import com.example.finalpa.di.BaseControllerConfig;
import com.example.finalpa.ScreenNavigator;

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
}
