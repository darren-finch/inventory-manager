package com.example.inventorymanagementsystem.di;

import com.example.inventorymanagementsystem.data.Inventory;
import com.example.inventorymanagementsystem.services.DialogManager;
import com.example.inventorymanagementsystem.ScreenNavigator;

public class BaseControllerConfig {
    private ScreenNavigator screenNavigator;
    private Inventory inventory;

    private DialogManager dialogManager;

    public BaseControllerConfig(ScreenNavigator screenNavigator, Inventory inventory, DialogManager dialogManager) {
        this.screenNavigator = screenNavigator;
        this.inventory = inventory;
        this.dialogManager = dialogManager;
    }

    public ScreenNavigator getScreenNavigator() {
        return screenNavigator;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public DialogManager getDialogManager() { return dialogManager; }
}
