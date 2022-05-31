package com.example.inventorymanagementsystem.controllers.validation;

import com.example.inventorymanagementsystem.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A real-time validator for TextFields.
 * It allows the user to specify two rules for validation:
 *
 * 1. Acceptable Input Rule
 * (Returns true if the next character can change the current text, else false)
 * For example, letters aren't allowed in a numeric TextField. That would go into the Acceptable Input Rule
 * This validator will ensure the user cannot input an unacceptable character.
 *
 * 2. Valid Input Rule
 * (Returns true if the current input is valid according to business rules, else false)
 * For example, a minimum needs to be less than a maximum.
 */
public class TextFieldValidator implements ChangeListener<String> {
    /**
     * The current text for the text field. This is guaranteed to be acceptable input, but not necessarily valid input.
     */
    private String text = "";

    /**
     * The TextField this validator will monitor
     */
    private final TextField textField;

    /**
     * An error label to display when the input is acceptable but not
     */
    private Label invalidInputErrorLabel;

    /**
     * The acceptable input rule
     */
    private final Function<String, Boolean> acceptInputRule;

    /**
     * The valid input rule
     */
    private final Function<String, Boolean> validInputRule;

    /**
     * A Consumer that can run when the text has changed.
     */
    private final Consumer<String> onTextChangedExternal;

    /**
     * Constructs a new TextFieldValidator
     * @param textField The TextField to monitor
     * @param acceptInputRule The Acceptable Input Rule
     * @param validInputRule The Valid Input Rule
     * @param onTextChanged A Consumer that runs when a new acceptable input is entered
     */
    public TextFieldValidator(TextField textField, Function<String, Boolean> acceptInputRule, Function<String, Boolean> validInputRule, Consumer<String> onTextChanged) {
        if (textField == null || acceptInputRule == null || validInputRule == null || onTextChanged == null) {
            throw new RuntimeException("TextFieldValidator: Null arguments in constructor");
        }

        this.textField = textField;
        this.acceptInputRule = acceptInputRule;
        this.validInputRule = validInputRule;
        this.onTextChangedExternal = onTextChanged;

        this.textField.textProperty().addListener(this);
        determineValidityOfInitialState();
    }

    /**
     * Determines whether the initial state of this validator is valid
     */
    private void determineValidityOfInitialState() {
        onTextChangedInternal(false);
    }

    /**
     * Sets an error label to be displayed when the current input is invalid
     * @param errorLabel The label to be displayed
     * @param errorMessage The error message to display on the label
     */
    public void setErrorLabel(Label errorLabel, String errorMessage) {
        this.invalidInputErrorLabel = errorLabel;
        this.invalidInputErrorLabel.setText(errorMessage);
    }

    /**
     * @return The current, acceptable text according to the validation rules
     */
    public String getText() {
        return text;
    }

    /**
     * @return Whether the current input is valid
     */
    public boolean isValid() {
        return validInputRule.apply(text);
    }

    /**
     * Called when the validator is initialized and called when the text field changes
     * @param triggerOnTextChangedExternal Whether this text field change should trigger onTextChangedExternal
     */
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

    /**
     * If the next input will make the current input unacceptable, then this method discards that input
     */
    private void undoNewTextFieldChange() {
        textField.textProperty().removeListener(this);
        textField.setText(text);
        textField.textProperty().addListener(this);
    }

    /**
     * RUNTIME ERROR
     * NullReferenceException when I tried to access invalidInputErrorLabel before it was initialized.
     * Because the user decides when he wants to set invalidInputErrorLabel, it's not guaranteed for that object
     * to be available. So I corrected this error by simply checking to see if invalidInputErrorLabel is null before
     * using it.
     *
     * Redetermines input validity and updates error labels accordingly
     */
    public void revalidate() {
        if (validInputRule.apply(text)) {
            if (invalidInputErrorLabel != null)
                Util.hideAndRemoveFromDocumentFlow(invalidInputErrorLabel);
        } else {
            if (invalidInputErrorLabel != null)
                Util.showAndAddBackToDocumentFlow(invalidInputErrorLabel);
        }
    }

    /**
     * @return True if the TextField we're monitoring is visible
     */
    public boolean isVisible() {
        return textField.isVisible();
    }

    /**
     * @return True if the TextField we're monitoring is enabled
     */
    public boolean isEnabled() {
        return !textField.isDisabled();
    }

    /**
     * Called when the text field changes
     * @param observableValue
     * @param s
     * @param t1
     */
    @Override
    public final void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        onTextChangedInternal(true);
    }
}
