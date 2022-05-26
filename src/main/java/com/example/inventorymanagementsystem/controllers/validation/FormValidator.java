package com.example.inventorymanagementsystem.controllers.validation;

import java.util.List;

public class FormValidator {
    private final List<TextFieldValidator> validators;

    public FormValidator(List<TextFieldValidator> validators) {
        this.validators = validators;
    }

    public boolean formIsValid() {
        for (TextFieldValidator validator : validators) {
            if (!validator.isValid() && validator.isVisible() && validator.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void revalidateForm() {
        for (TextFieldValidator validator : validators) {
            if (validator.isVisible() && validator.isEnabled()) {
                validator.revalidate();
            }
        }
    }
}
