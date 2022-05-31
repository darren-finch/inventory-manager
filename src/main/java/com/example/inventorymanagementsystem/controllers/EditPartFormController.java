package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.Util;
import com.example.inventorymanagementsystem.controllers.data.PresentationPart;
import com.example.inventorymanagementsystem.controllers.validation.AcceptableInputUtil;
import com.example.inventorymanagementsystem.controllers.validation.FormValidator;
import com.example.inventorymanagementsystem.controllers.validation.TextFieldValidator;
import com.example.inventorymanagementsystem.data.Part;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.List;

/**
 * Functions as both the AddPart form and the ModifyPart form.
 * If a null part is passed to this controller, it functions as the AddPart form.
 * If an existing part is passed to this controller, it functions as the ModifyPart form.
 */
public class EditPartFormController extends BaseController {
    /**
     * The index of the part in the parts TableView on the MainForm
     */
    private int index;

    /**
     * A POJO specifically for form data
     */
    private PresentationPart presentationPart;

    /**
     * The validator for the name text field
     */
    private TextFieldValidator nameValidator;
    /**
     * The validator for the inv text field
     */
    private TextFieldValidator invValidator;
    /**
     * The validator for the price text field
     */
    private TextFieldValidator priceValidator;
    /**
     * The validator for the min text field
     */
    private TextFieldValidator minValidator;
    /**
     * The validator for the max text field
     */
    private TextFieldValidator maxValidator;
    /**
     * The validator for the machine id text field
     */
    private TextFieldValidator machineIdValidator;
    /**
     * The validator for the company name text field
     */
    private TextFieldValidator companyNameValidator;

    /**
     * The validator for the entire form
     */
    private FormValidator formValidator;

    /**
     * The InHouse radio button
     */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * The Outsourced radio button
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * The id text field
     */
    @FXML
    private TextField idTextField;

    /**
     * The name text field
     */
    @FXML
    private TextField nameTextField;

    /**
     * The inv text field
     */
    @FXML
    private TextField invTextField;

    /**
     * The price text field
     */
    @FXML
    private TextField priceTextField;

    /**
     * The min text field
     */
    @FXML
    private TextField minTextField;

    /**
     * The max text field
     */
    @FXML
    private TextField maxTextField;

    /**
     * The machine id text field
     */
    @FXML
    private TextField machineIdTextField;

    /**
     * The company name text field
     */
    @FXML
    private TextField companyNameTextField;

    /**
     * The machine id label
     */
    @FXML
    private Label machineIdLabel;

    /**
     * The company name label
     */
    @FXML
    private Label companyNameLabel;

    /**
     * The error label for the name text field
     */
    @FXML
    private Label nameInvalidLabel;

    /**
     * The error label for the inv text field
     */
    @FXML
    private Label invInvalidLabel;

    /**
     * The error label for the price text field
     */
    @FXML
    private Label priceInvalidLabel;

    /**
     * The error label for the min text field
     */
    @FXML
    private Label minInvalidLabel;

    /**
     * The error label for the machine id text field
     */
    @FXML
    private Label machineIdInvalidLabel;

    /**
     * The error label for the company name text field
     */
    @FXML
    private Label companyNameInvalidLabel;

    /**
     * Sets the arguments for this form.
     * @param index The index of the part in the parts TableView on the MainForm. Pass -1 if adding a new part.
     * @param part The selected part in the parts TableView on the MainForm. Pass null if adding a new part.
     */
    public void setArgs(int index, Part part) {
        this.index = index;
        this.presentationPart = new PresentationPart(part);
    }

    /**
     * Sets up the UI
     */
    @Override
    public void setupUI() {
        setupRadioButtons();
        setupTextFields();
        setupFormValidators();
        revalidateForm();
    }

    /**
     * Sets up the form validators
     */
    private void setupFormValidators() {
        nameValidator = new TextFieldValidator(nameTextField, (s) -> true, (s) -> !s.isBlank(), (s) -> presentationPart.setName(s));
        invValidator = new TextFieldValidator(invTextField, AcceptableInputUtil::isAcceptableInt, (s) -> {
                    try {
                        int stock = Integer.parseInt(s);
                        return Integer.parseInt(presentationPart.getMin()) <= stock
                                && stock <= Integer.parseInt(presentationPart.getMax());
                    } catch (Exception e) {
                        return false;
                    }
                }, (s) -> presentationPart.setStock(s));
        priceValidator = new TextFieldValidator(priceTextField, AcceptableInputUtil::isAcceptableDouble, (s) -> {
                    try {
                        Double.parseDouble(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }, (s) -> presentationPart.setPrice(s));
        minValidator = new TextFieldValidator(minTextField, AcceptableInputUtil::isAcceptableInt, (s) -> {
                    try {
                        int min = Integer.parseInt(s);
                        return min <= Integer.parseInt(presentationPart.getMax());
                    } catch (Exception e) {
                        return false;
                    }
                }, (s) -> presentationPart.setMin(s));
        maxValidator = new TextFieldValidator(maxTextField, AcceptableInputUtil::isAcceptableInt, (s) -> true, (s) -> presentationPart.setMax(s));
        machineIdValidator = new TextFieldValidator(machineIdTextField, AcceptableInputUtil::isAcceptableInt, (s) -> !s.isBlank(), (s) -> presentationPart.setMachineId(s));
        companyNameValidator = new TextFieldValidator(companyNameTextField, (s) -> true, (s) -> !s.isBlank(), (s) -> presentationPart.setCompanyName(s));

        nameValidator.setErrorLabel(nameInvalidLabel, "Name must be non-empty");
        priceValidator.setErrorLabel(priceInvalidLabel, "Price must be a non-empty integer");
        invValidator.setErrorLabel(invInvalidLabel, "Inv must be a valid integer between min and max (inclusive)");
        minValidator.setErrorLabel(minInvalidLabel, "Min must be a valid integer less than or equal to max");
        machineIdValidator.setErrorLabel(machineIdInvalidLabel, "Machine ID must be a non-empty integer");
        companyNameValidator.setErrorLabel(companyNameInvalidLabel, "Company Name must be non-empty");

        formValidator = new FormValidator(List.of(nameValidator, invValidator, priceValidator, minValidator, maxValidator, machineIdValidator, companyNameValidator));
    }

    /**
     * Sets up the radio buttons
     */
    private void setupRadioButtons() {
        ToggleGroup toggleGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(toggleGroup);
        outsourcedRadioButton.setToggleGroup(toggleGroup);

        if (partIsInHouse()) {
            inHouseRadioButton.setSelected(true);
            onInHouseSelected();
        } else {
            outsourcedRadioButton.setSelected(true);
            onOutsourcedSelected();
        }
    }

    /**
     * Sets up the text fields
     */
    private void setupTextFields() {
        idTextField.setDisable(true);
        if (presentationPart.isExistingPart())
            idTextField.setText(presentationPart.getId());
        else
            idTextField.setText("Auto-Gen");

        nameTextField.setText(presentationPart.getName());
        invTextField.setText(presentationPart.getStock());
        priceTextField.setText(presentationPart.getPrice());
        minTextField.setText(presentationPart.getMin());
        maxTextField.setText(presentationPart.getMax());
        machineIdTextField.setText(presentationPart.getMachineId());
        companyNameTextField.setText(presentationPart.getCompanyName());
    }

    /**
     * Hides Outsourced part UI and shows InHouse part UI
     */
    public void onInHouseSelected() {
        presentationPart.setIsInHousePart(true);
        Util.hideAndRemoveFromDocumentFlow(companyNameLabel);
        Util.hideAndRemoveFromDocumentFlow(companyNameInvalidLabel);
        Util.hideAndRemoveFromDocumentFlow(companyNameTextField);
        Util.showAndAddBackToDocumentFlow(machineIdLabel);
        if (machineIdValidator != null && !machineIdValidator.isValid())
            Util.showAndAddBackToDocumentFlow(machineIdInvalidLabel);
        Util.showAndAddBackToDocumentFlow(machineIdTextField);
    }

    /**
     * Hides InHouse part UI and shows Outsourced part UI
     */
    public void onOutsourcedSelected() {
        presentationPart.setIsInHousePart(false);
        Util.hideAndRemoveFromDocumentFlow(machineIdLabel);
        Util.hideAndRemoveFromDocumentFlow(machineIdInvalidLabel);
        Util.hideAndRemoveFromDocumentFlow(machineIdTextField);
        Util.showAndAddBackToDocumentFlow(companyNameLabel);
        if (companyNameValidator != null && !companyNameValidator.isValid())
            Util.showAndAddBackToDocumentFlow(companyNameInvalidLabel);
        Util.showAndAddBackToDocumentFlow(companyNameTextField);
    }

    /**
     * Revalidates the form
     */
    public void revalidateForm() {
        formValidator.revalidateForm();
    }

    /**
     * If the form data is valid, saves the part to inventory
     */
    public void onSave() {
        if (!formValidator.formIsValid()) {
            return;
        }

        if (presentationPart.isExistingPart()) {
            getInventory().updatePart(index, presentationPart.toPart());
        } else {
            getInventory().addPart(presentationPart.toPart());
        }
        getScreenNavigator().switchToMainForm();
    }

    /**
     * Switches back to the MainForm without saving anything upon confirmation via a confirmation dialog
     */
    public void onCancel() {
        getDialogManager().showCancelConfirmationDialog(() -> getScreenNavigator().switchToMainForm(), null);
    }

    /**
     * A shortcut for <code>presentationPart.getIsInHousePart();</code>
     * @return Whether the current part is InHouse
     */
    private boolean partIsInHouse() {
        return presentationPart.getIsInHousePart();
    }
}
