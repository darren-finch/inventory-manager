package com.example.inventorymanagementsystem.di;

import com.example.inventorymanagementsystem.data.Inventory;
import com.example.inventorymanagementsystem.DialogManager;
import com.example.inventorymanagementsystem.ScreenNavigator;

/**
 * The base DI configuration for all controllers
 */
public class BaseControllerConfig {
    /**
     * The screen navigator
     */
    private ScreenNavigator screenNavigator;

    /**
     * The inventory object (basically a repository)
     */
    private Inventory inventory;

    /**
     * The dialog manager
     */
    private DialogManager dialogManager;

    /**
     * Constructs a new BaseControllerConfig
     * @param screenNavigator the screen navigator
     * @param inventory the inventory
     * @param dialogManager the dialogManager
     */
    public BaseControllerConfig(ScreenNavigator screenNavigator, Inventory inventory, DialogManager dialogManager) {
        this.screenNavigator = screenNavigator;
        this.inventory = inventory;
        this.dialogManager = dialogManager;
    }

    /**
     * @return the screen navigator
     */
    public ScreenNavigator getScreenNavigator() {
        return screenNavigator;
    }

    /**
     * @return the inventory object
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @return the dialog manager
     */
    public DialogManager getDialogManager() { return dialogManager; }
}
