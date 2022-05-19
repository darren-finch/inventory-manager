package com.example.finalpa;

public abstract class BaseController {
    private ScreenNavigator screenNavigator;

    public void initialize(ScreenNavigator screenNavigator) {
        this.screenNavigator = screenNavigator;
    }

    public ScreenNavigator getScreenNavigator() {
        return screenNavigator;
    }
}
