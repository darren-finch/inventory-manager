package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.controllers.validation.AcceptableInputUtil;
import com.example.inventorymanagementsystem.controllers.validation.FormValidator;
import com.example.inventorymanagementsystem.controllers.validation.TextFieldValidator;
import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EditProductFormController extends BaseController {
    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TableView<Part> allPartsTableView;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TextField searchPartsTextField;

    @FXML
    private Label searchPartsByIdErrorLabel;

    private TextFieldValidator nameValidator;
    private TextFieldValidator priceValidator;
    private TextFieldValidator invValidator;
    private TextFieldValidator minValidator;
    private TextFieldValidator maxValidator;

    private FormValidator validator;

    private int index;

    private PresentationProduct presentationProduct;

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

    public void setArgs(int index, Product product) {
        this.index = index;
        this.presentationProduct = new PresentationProduct(product);
    }

    @Override
    public void setupUI() {
        setupTextFields();
        setupInputValidators();
        setupAllPartsTableView();
        setupAssociatedPartsTableView();
    }

    @Override
    public void tearDown() {

    }

    private void setupTextFields() {
        idTextField.setDisable(true);
        idTextField.setText(presentationProduct.getId());
        nameTextField.setText(presentationProduct.getName());
        invTextField.setText(presentationProduct.getStock());
        priceTextField.setText(presentationProduct.getPrice());
        minTextField.setText(presentationProduct.getMin());
        maxTextField.setText(presentationProduct.getMax());
    }

    private void setupInputValidators() {
        nameValidator = new TextFieldValidator(nameTextField, (s) -> true, (s) -> !s.isEmpty(), (s) -> presentationProduct.setName(s));
        invValidator = new TextFieldValidator(invTextField, AcceptableInputUtil::isAcceptableInt, (s) -> {
            try {
                int stock = Integer.parseInt(s);
                return Integer.parseInt(presentationProduct.getMin()) <= stock
                        && stock <= Integer.parseInt(presentationProduct.getMax());
            } catch (Exception e) {
                return false;
            }
        }, (s) -> presentationProduct.setStock(s));
        priceValidator = new TextFieldValidator(priceTextField, AcceptableInputUtil::isAcceptableDouble, (s) -> {
            try {
                Double.parseDouble(s);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, (s) -> presentationProduct.setPrice(s));
        minValidator = new TextFieldValidator(minTextField, AcceptableInputUtil::isAcceptableInt, (s) -> {
            try {
                int min = Integer.parseInt(s);
                return min <= Integer.parseInt(presentationProduct.getStock())
                        && min <= Integer.parseInt(presentationProduct.getMax());
            } catch (Exception e) {
                return false;
            }
        }, (s) -> presentationProduct.setMin(s));
        maxValidator = new TextFieldValidator(maxTextField, AcceptableInputUtil::isAcceptableInt, (s) -> {
            try {
                int max = Integer.parseInt(s);
                return Integer.parseInt(presentationProduct.getStock()) <= max
                        && Integer.parseInt(presentationProduct.getMin()) <= max;
            } catch (Exception e) {
                return false;
            }
        }, (s) -> presentationProduct.setMax(s));

        nameValidator.setErrorLabel(nameInvalidLabel, "Name must be non-empty");
        invValidator.setErrorLabel(invInvalidLabel, "Inv must be a non-empty integer greater than/equal to min and less than/equal to max");
        priceValidator.setErrorLabel(priceInvalidLabel, "Price must be a non-empty integer");
        minValidator.setErrorLabel(minInvalidLabel, "Min must be a non-empty integer less than/equal to stock and max");
        maxValidator.setErrorLabel(maxInvalidLabel, "Max must be a non-empty integer greater than/equal to stock and min");

        validator = new FormValidator(List.of(nameValidator, invValidator, priceValidator, minValidator, maxValidator));
        validator.revalidateForm();
    }

    private void setupAllPartsTableView() {
        TableColumn<Part, Integer> idColumn = new TableColumn<>("Part ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> nameColumn = new TableColumn<>("Part Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> priceColumn  = new TableColumn<>("Price / Cost per Unit");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Part, Integer> stockColumn  = new TableColumn<>("Inventory Level");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        allPartsTableView.setItems(getInventory().getAllParts());
        allPartsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        allPartsTableView.setEditable(false);
    }

    private void setupAssociatedPartsTableView() {
        TableColumn<Part, Integer> idColumn = new TableColumn<>("Part ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> nameColumn = new TableColumn<>("Part Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> priceColumn  = new TableColumn<>("Price / Cost per Unit");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Part, Integer> stockColumn  = new TableColumn<>("Inventory Level");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        associatedPartsTableView.setItems(presentationProduct.getAllAssociatedParts());
        presentationProduct.getAllAssociatedParts().addListener((InvalidationListener) observable -> {
            associatedPartsTableView.setItems(presentationProduct.getAllAssociatedParts());
        });
        associatedPartsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        associatedPartsTableView.setEditable(false);
    }

    public void onTextFieldChanged() {
        validator.revalidateForm();
    }

    public void searchParts() {
        String query = searchPartsTextField.getText();
        searchPartsByIdErrorLabel.setVisible(false); // error hidden by default

        // Inventory really should handle this logic of whether to search via id or not, but I'm adhering to the UML diagrams
        try {
            int partId = Integer.parseInt(query);
            List<Part> queryResults = List.of(getInventory().lookupPart(partId));
            allPartsTableView.setItems(FXCollections.observableList(queryResults));
            searchPartsByIdErrorLabel.setVisible(query.isEmpty());
        } catch (NumberFormatException exception) {
            ObservableList<Part> parts = getInventory().lookupPart(query);
            allPartsTableView.setItems(parts);
        } catch (Exception exception) {
            allPartsTableView.setItems(getInventory().getAllParts());
        }
    }
    public void addAssociatedPart() {
        SelectionModel<Part> selectionModel = allPartsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        if (selectedPart != null) {
            presentationProduct.addAssociatedPart(selectedPart);
        }
    }
    public void removeAssociatedPart() {
        SelectionModel<Part> selectionModel = associatedPartsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        if (selectedPart != null) {
            getDialogManager().showDeleteConfirmationDialog(() -> {
                presentationProduct.deleteAssociatedPart(selectedPart);
            }, null);
        }
    }
    public void cancel() {
        getDialogManager().showCancelConfirmationDialog(() -> {
            getScreenNavigator().switchToMainForm();
        }, null);
    }
    public void saveProduct() {
        if (!validator.formIsValid()) {
            return;
        }

        if (presentationProduct.isExistingProduct()) {
            getInventory().updateProduct(index, presentationProduct.toProduct());
        } else {
            getInventory().addProduct(presentationProduct.toProduct());
        }

        getScreenNavigator().switchToMainForm();
    }
}
