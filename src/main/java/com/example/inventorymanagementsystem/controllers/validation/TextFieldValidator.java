package com.example.inventorymanagementsystem.controllers.validation;

import com.example.inventorymanagementsystem.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.function.Consumer;
import java.util.function.Function;

public class TextFieldValidator implements ChangeListener<String> {
    private String text;
    private final TextField textField;
    private Label errorLabel;
    private final Function<String, Boolean> acceptInputRule;
    private final Function<String, Boolean> validInputRule;
    private final Consumer<String> onTextChangedExternal;
    private boolean isValid;

    public TextFieldValidator(TextField textField, Function<String, Boolean> acceptInputRule, Function<String, Boolean> validInputRule, Consumer<String> onTextChanged) {
        this.textField = textField;
        this.acceptInputRule = acceptInputRule;
        this.validInputRule = validInputRule;
        this.onTextChangedExternal = onTextChanged;

        this.textField.textProperty().addListener(this);
        determineValidityOfInitialState();
    }

    private void determineValidityOfInitialState() {
        onTextChangedInternal(false);
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

    private void onTextChangedInternal(boolean triggerOnTextChangedExternal) {
        if (acceptInputRule.apply(textField.getText())) {
            text = textField.getText();
            if (triggerOnTextChangedExternal)
                onTextChangedExternal.accept(text);
            revalidate();
        } else {
            undoNewTextFieldChange();
        }
    }

    private void undoNewTextFieldChange() {
        textField.textProperty().removeListener(this);
        textField.setText(text);
        textField.textProperty().addListener(this);
    }

    public void revalidate() {
        if (validInputRule.apply(text)) {
            isValid = true;
            if (errorLabel != null)
                Util.hideAndRemoveFromDocumentFlow(errorLabel);
        } else {
            isValid = false;
            if (errorLabel != null)
                Util.showAndAddBackToDocumentFlow(errorLabel);
        }
    }

    public boolean isVisible() {
        return textField.isVisible();
    }

    public boolean isEnabled() {
        return !textField.isDisabled();
    }

    @Override
    public final void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        onTextChangedInternal(true);
    }
}
