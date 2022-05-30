package com.example.inventorymanagementsystem.controllers.validation;

/**
 * A utility for determining acceptable input for text fields
 */
public class AcceptableInputUtil {
    /**
     * @param s The input as a string (usually from a TextField)
     * @return True if s is an integer, else false. Returning true when s is empty allows the last digit to be deleted.
     */
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

    /**
     * @param s The input as a string (usually from a TextField)
     * @return True if s is a double, else false. Returning true when s is empty allows the last digit to be deleted.
     */
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
