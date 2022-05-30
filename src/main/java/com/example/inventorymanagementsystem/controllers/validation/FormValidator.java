package com.example.inventorymanagementsystem.controllers.validation;

import java.util.List;

/**
 * The validator for an entire form.
 */
public class FormValidator {
    /**
     * A list of TextFieldValidators
     */
    private final List<TextFieldValidator> textFieldValidators;

    /**
     * Constructs a new FormValidator
     * @param textFieldValidators A list of pre-constructed TextFieldValidators
     */
    public FormValidator(List<TextFieldValidator> textFieldValidators) {
        this.textFieldValidators = textFieldValidators;
    }

    /**
     * @return True if all inputs are valid, else false
     */
    public boolean formIsValid() {
        for (TextFieldValidator validator : textFieldValidators) {
            if (!validator.isValid() && validator.isVisible() && validator.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Forces all inputs to revalidate
     */
    public void revalidateForm() {
        for (TextFieldValidator validator : textFieldValidators) {
            if (validator.isVisible() && validator.isEnabled()) {
                validator.revalidate();
            }
        }
    }
}
