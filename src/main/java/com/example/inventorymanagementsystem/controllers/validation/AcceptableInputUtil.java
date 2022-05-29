package com.example.inventorymanagementsystem.controllers.validation;

public class AcceptableInputUtil {
    public static boolean isAcceptableInt(String s) {
        if (s.isEmpty()) {
            return true;
        }

        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isAcceptableDouble(String s) {
        if (s.isEmpty()) {
            return true;
        }

        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
