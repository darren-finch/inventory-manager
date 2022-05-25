package com.example.inventorymanagementsystem.controllers;

import java.util.List;

public class EditPartFormValidator {
    private final List<ValidatableTextField> textFields;

    public EditPartFormValidator(List<ValidatableTextField> textFields) {
        this.textFields = textFields;
    }

    public boolean formIsValid() {
        for (ValidatableTextField textField : textFields) {
            if (!textField.isValid()) {
                return false;
            }
        }
        return true;
    }
}
