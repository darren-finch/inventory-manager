package com.example.finalpa.di;

import com.example.finalpa.data.Inventory;
import com.example.finalpa.ScreenNavigator;

public class BaseControllerConfig {
    private ScreenNavigator screenNavigator;
    private Inventory inventory;

    public BaseControllerConfig(ScreenNavigator screenNavigator, Inventory inventory) {
        this.screenNavigator = screenNavigator;
        this.inventory = inventory;
    }

    public ScreenNavigator getScreenNavigator() {
        return screenNavigator;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
