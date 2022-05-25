package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Part;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EditPartFormController extends BaseController {
    private int index;

    private PresentationPart presentationPart;

    private boolean hasError = false;

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
    private TextField machineIdOrCompanyNameTextField;

    @FXML
    private Label machineIdOrCompanyNameLabel;

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

    public void setArgs(int index, Part part) {
        this.index = index;
        this.presentationPart = new PresentationPart(part);
    }

    @Override
    public void setupUI() {
        setupRadioButtons();
        setupTextFields();
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
        idTextField.setText(Integer.toString(presentationPart.getId()));
        if (presentationPart.isExistingPart()) {
            idTextField.setDisable(true);
        }

        nameTextField.setText(presentationPart.getName());
        invTextField.setText(Integer.toString(presentationPart.getStock()));
        priceTextField.setText(Double.toString(presentationPart.getPrice()));
        minTextField.setText(Integer.toString(presentationPart.getMin()));
        maxTextField.setText(Integer.toString(presentationPart.getMax()));

        if (partIsInHouse()) {
            machineIdOrCompanyNameTextField.setText(Integer.toString(presentationPart.getMachineId()));
        } else {
            machineIdOrCompanyNameTextField.setText(presentationPart.getCompanyName());
        }
    }

    public void onInHouseSelected() {
        presentationPart.setIsInHousePart(true);
        machineIdOrCompanyNameTextField.setText(Integer.toString(presentationPart.getMachineId()));
        machineIdOrCompanyNameLabel.setText("Machine ID");
    }

    public void onOutsourcedSelected() {
        presentationPart.setIsInHousePart(false);
        machineIdOrCompanyNameTextField.setText(presentationPart.getCompanyName());
        machineIdOrCompanyNameLabel.setText("Company Name");
    }

    public void onIdTextFieldChanged() {
        try {
            presentationPart.setId(Integer.parseInt(idTextField.getText()));
            idInvalidLabel.setManaged(false);
            idInvalidLabel.setVisible(false);
            hasError = true;
        } catch (Exception e) {
            idInvalidLabel.setManaged(true);
            idInvalidLabel.setVisible(true);
            hasError = false;
        }
    }

    public void onNameTextFieldChanged() {
        String name = nameTextField.getText();
        if (name.isEmpty()) {
            nameInvalidLabel.setManaged(true);
            nameInvalidLabel.setVisible(true);
            hasError = true;
        } else {
            nameInvalidLabel.setManaged(false);
            nameInvalidLabel.setVisible(false);
            hasError = false;
        }
        presentationPart.setName(name);
    }

    public void onInvTextFieldChanged() {
        try {
            int stock = Integer.parseInt(invTextField.getText());
            presentationPart.setStock(stock);
            if (presentationPart.getMin() > stock || presentationPart.getMax() < stock) {
                throw new RuntimeException();
            }
            invInvalidLabel.setManaged(false);
            invInvalidLabel.setVisible(false);
            hasError = true;
        } catch (Exception e) {
            invInvalidLabel.setManaged(true);
            invInvalidLabel.setVisible(true);
            hasError = false;
        }
    }

    public void onPriceTextFieldChanged() {
        try {
            presentationPart.setPrice(Double.parseDouble(priceTextField.getText()));
            priceInvalidLabel.setManaged(false);
            priceInvalidLabel.setVisible(false);
            hasError = true;
        } catch (Exception e) {
            priceInvalidLabel.setManaged(true);
            priceInvalidLabel.setVisible(true);
            hasError = false;
        }
    }

    public void onMinTextFieldChanged() {
        try {
            int min = Integer.parseInt(minTextField.getText());
            presentationPart.setMin(min);
            if (min > presentationPart.getStock() || min > presentationPart.getMax()) {
                throw new RuntimeException();
            }
            minInvalidLabel.setManaged(false);
            minInvalidLabel.setVisible(false);
            hasError = true;
        } catch (Exception e) {
            minInvalidLabel.setManaged(true);
            minInvalidLabel.setVisible(true);
            hasError = false;
        }
    }

    public void onMaxTextFieldChanged() {
        try {
            int max = Integer.parseInt(maxTextField.getText());
            presentationPart.setMin(max);
            if (max < presentationPart.getStock() || max < presentationPart.getMin()) {
                throw new RuntimeException();
            }
            maxInvalidLabel.setManaged(false);
            maxInvalidLabel.setVisible(false);
            hasError = true;
        } catch (Exception e) {
            maxInvalidLabel.setManaged(true);
            maxInvalidLabel.setVisible(true);
            hasError = false;
        }
    }

    public void onMachineIdOrCompanyNameTextFieldChanged() {
        if (partIsInHouse()) {
            try {
                int machineId = Integer.parseInt(machineIdOrCompanyNameTextField.getText());
                presentationPart.setMachineId(machineId);
                machineIdInvalidLabel.setManaged(false);
                machineIdInvalidLabel.setVisible(false);
                hasError = true;
            } catch (Exception e) {
                machineIdInvalidLabel.setManaged(true);
                machineIdInvalidLabel.setVisible(true);
                hasError = false;
            }
        } else {
            String companyName = machineIdOrCompanyNameTextField.getText();
            if (companyName.isEmpty()) {
                companyNameInvalidLabel.setManaged(true);
                companyNameInvalidLabel.setVisible(true);
                hasError = true;
            } else {
                companyNameInvalidLabel.setManaged(false);
                companyNameInvalidLabel.setVisible(false);
                hasError = false;
            }
            presentationPart.setName(companyName);
        }
    }

    public void onSave() {
        if (hasError) {
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
