package com.example.inventorymanagementsystem.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.function.Function;

public class ValidatableTextField {
    private String text;
    private final TextField textField;
    private Label errorLabel;
    private final Function<String, Boolean> acceptInputRule;
    private final Function<String, Boolean> validInputRule;
    private boolean isValid;

    public ValidatableTextField(TextField textField, Function<String, Boolean> acceptInputRule, Function<String, Boolean> validInputRule) {
        this.textField = textField;
        this.acceptInputRule = acceptInputRule;
        this.validInputRule = validInputRule;

        this.textField.setOnAction((e) -> onTextFieldChanged());
    }

    public void setErrorLabel(Label errorLabel, String errorMessage) {
        this.errorLabel = errorLabel;
        this.errorLabel.setText(errorMessage);
    }

    // Returns the current, valid text according to the validation rules
    public String getText() {
        return text;
    }

    public void setText(String newText) {
        textField.setText(newText);
    }

    public boolean isValid() {
        return isValid;
    }

    // TODO: Make sure textField's text does not change if the new text is not acceptable
    private void onTextFieldChanged() {
        if (acceptInputRule.apply(textField.getText())) {
            text = textField.getText();
            if (validInputRule.apply(text)) {
                isValid = true;
                hideErrorLabel();
            } else {
                isValid = false;
                displayErrorLabel();
            }
        }
    }

    private void displayErrorLabel() {
        errorLabel.setManaged(true);
        errorLabel.setVisible(true);
    }

    private void hideErrorLabel() {
        errorLabel.setManaged(false);
        errorLabel.setVisible(false);
    }
}
