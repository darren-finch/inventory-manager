package com.example.inventorymanagementsystem.controllers;

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
    private EditPartFormValidator validator;

    public void setArgs(int index, Part part) {
        this.index = index;
        this.presentationPart = new PresentationPart(part);
    }

    // TODO: COMPLETE ADDING THE REST OF THE VALIDATABLE TEXT FIELDS
    @Override
    public void setupUI() {
        setupRadioButtons();
        setupTextFields();
        validator = new EditPartFormValidator(List.of(
                new ValidatableTextField(idTextField, (s) -> true, (s) -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }),
                new ValidatableTextField(nameTextField, (s) -> true, (s) -> !s.isEmpty()),
                new ValidatableTextField(invTextField, (s) -> true, (s) -> {
                    try {
                        int stock = Integer.parseInt(s);
                        if (Integer.parseInt(presentationPart.getMin()) <= stock
                                && Integer.parseInt(presentationPart.getMax()) >= stock) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }),
                new ValidatableTextField(priceTextField, (s) -> true, (s) -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }),
                new ValidatableTextField(idTextField, (s) -> true, (s) -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }),
                new ValidatableTextField(idTextField, (s) -> true, (s) -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }),
                new ValidatableTextField(idTextField, (s) -> true, (s) -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
        ));
    }

    private void setupRadioButtons() {
        ToggleGroup toggleGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(toggleGroup);
        outsourcedRadioButton.setToggleGroup(toggleGroup);

        if (partIsInHouse()) {
            inHouseRadioButton.setSelected(true);
        } else {
            outsourcedRadioButton.setSelected(true);
        }
    }

    private void setupTextFields() {
        idTextField.setText(presentationPart.getId());
        if (presentationPart.isExistingPart()) {
            idTextField.setDisable(true);
        }

        nameTextField.setText(presentationPart.getName());
        invTextField.setText(presentationPart.getStock());
        priceTextField.setText(presentationPart.getPrice());
        minTextField.setText(presentationPart.getMin());
        maxTextField.setText(presentationPart.getMax());

        if (partIsInHouse()) {
            onInHouseSelected();
        } else {
            onOutsourcedSelected();
        }
    }

    public void onInHouseSelected() {
        presentationPart.setIsInHousePart(true);

    }

    public void onOutsourcedSelected() {
        presentationPart.setIsInHousePart(false);

    }

    public void onTextFieldChanged() {

    }

    public void onSave() {
        if (!validator.formIsValid()) {
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
