package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.Util;
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

public class EditPartFormController extends BaseController {
    private int index;

    private PresentationPart presentationPart;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField machineIdTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private Label machineIdLabel;

    @FXML
    private Label companyNameLabel;

    @FXML
    private Label idInvalidLabel;

    @FXML
    private Label nameInvalidLabel;

    @FXML
    private Label invInvalidLabel;

    @FXML
    private Label priceInvalidLabel;

    @FXML
    private Label minInvalidLabel;

    @FXML
    private Label maxInvalidLabel;

    @FXML
    private Label machineIdInvalidLabel;

    @FXML
    private Label companyNameInvalidLabel;

    private TextFieldValidator idValidator;
    private TextFieldValidator nameValidator;
    private TextFieldValidator invValidator;
    private TextFieldValidator priceValidator;
    private TextFieldValidator minValidator;
    private TextFieldValidator maxValidator;
    private TextFieldValidator machineIdValidator;
    private TextFieldValidator companyNameValidator;

    private FormValidator formValidator;

    public void setArgs(int index, Part part) {
        this.index = index;
        this.presentationPart = new PresentationPart(part);
    }

    @Override
    public void setupUI() {
        setupRadioButtons();
        setupTextFields();
        setupValidation();
        revalidateForm();
    }

    private void setupValidation() {
        idValidator = new TextFieldValidator(idTextField, AcceptableInputUtil::isAcceptableInt, (s) -> true, (s) -> presentationPart.setId(s));
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
                        return min <= Integer.parseInt(presentationPart.getStock())
                                && min <= Integer.parseInt(presentationPart.getMax());
                    } catch (Exception e) {
                        return false;
                    }
                }, (s) -> presentationPart.setMin(s));
        maxValidator = new TextFieldValidator(maxTextField, AcceptableInputUtil::isAcceptableInt, (s) -> {
                    try {
                        int max = Integer.parseInt(s);
                        return Integer.parseInt(presentationPart.getStock()) <= max
                                && Integer.parseInt(presentationPart.getMin()) <= max;
                    } catch (Exception e) {
                        return false;
                    }
                }, (s) -> presentationPart.setMax(s));
        machineIdValidator = new TextFieldValidator(machineIdTextField, AcceptableInputUtil::isAcceptableInt, (s) -> true, (s) -> presentationPart.setMachineId(s));
        companyNameValidator = new TextFieldValidator(companyNameTextField, (s) -> true, (s) -> !s.isBlank(), (s) -> presentationPart.setCompanyName(s));

        idValidator.setErrorLabel(idInvalidLabel, "ID must be a non-empty integer");
        nameValidator.setErrorLabel(nameInvalidLabel, "Name must be non-empty");
        invValidator.setErrorLabel(invInvalidLabel, "Inv must be a non-empty integer greater than/equal to min and less than/equal to max");
        priceValidator.setErrorLabel(priceInvalidLabel, "Price must be a non-empty integer");
        minValidator.setErrorLabel(minInvalidLabel, "Min must be a non-empty integer less than/equal to stock and max");
        maxValidator.setErrorLabel(maxInvalidLabel, "Max must be a non-empty integer greater than/equal to stock and min");
        machineIdValidator.setErrorLabel(machineIdInvalidLabel, "Machine ID must be a non-empty integer");
        companyNameValidator.setErrorLabel(companyNameInvalidLabel, "Company Name must be non-empty");

        formValidator = new FormValidator(List.of(idValidator, nameValidator, invValidator, priceValidator, minValidator, maxValidator, machineIdValidator, companyNameValidator));
    }

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

    private void setupTextFields() {
        if (presentationPart.isExistingPart()) {
            idTextField.setDisable(true);
        }

        idTextField.setText(presentationPart.getId());
        nameTextField.setText(presentationPart.getName());
        invTextField.setText(presentationPart.getStock());
        priceTextField.setText(presentationPart.getPrice());
        minTextField.setText(presentationPart.getMin());
        maxTextField.setText(presentationPart.getMax());
        machineIdTextField.setText(presentationPart.getMachineId());
        companyNameTextField.setText(presentationPart.getCompanyName());
    }

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

    public void revalidateForm() {
        formValidator.revalidateForm();
    }

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

    public void onCancel() {
        getDialogManager().showCancelConfirmationDialog(() -> getScreenNavigator().switchToMainForm(), null);
    }

    private boolean partIsInHouse() {
        return presentationPart.getIsInHousePart();
    }
}
