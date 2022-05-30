package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.controllers.validation.AcceptableInputUtil;
import com.example.inventorymanagementsystem.controllers.validation.FormValidator;
import com.example.inventorymanagementsystem.controllers.validation.TextFieldValidator;
import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Functions as both the AddProduct form and the ModifyProduct form.
 * If a null product is passed to this controller, it functions as the AddProduct form.
 * If an existing product is passed to this controller, it functions as the ModifyProduct form.
 */
public class EditProductFormController extends BaseController {
    /**
     * The index of this product in the products TableView on the MainForm
     */
    private int index;

    /**
     * A POJO specifically for form data
     */
    private PresentationProduct presentationProduct;

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
     * The validator for the entire form
     */
    private FormValidator formValidator;

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
     * The TableView for all parts
     */
    @FXML
    private TableView<Part> allPartsTableView;

    /**
     * The TableView for associated parts
     */
    @FXML
    private TableView<Part> associatedPartsTableView;

    /**
     * The search bar for the allPartsTableView
     */
    @FXML
    private TextField searchPartsTextField;

    /**
     * The error label for the searchPartsTextField, displays search errors for the allPartsTableView
     */
    @FXML
    private Label searchPartsErrorLabel;

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
     * Sets the arguments for this form.
     * @param index The index of the product in the products TableView on the MainForm. Pass -1 if adding a new product.
     * @param product The selected product in the products TableView on the MainForm. Pass null if adding a new product.
     */
    public void setArgs(int index, Product product) {
        this.index = index;
        this.presentationProduct = new PresentationProduct(product);
    }

    /**
     * Sets up UI
     */
    @Override
    public void setupUI() {
        setupTextFields();
        setupFormValidators();
        setupAllPartsTableView();
        setupAssociatedPartsTableView();
    }

    /**
     * Set up text fields
     */
    private void setupTextFields() {
        idTextField.setDisable(true);
        if (presentationProduct.isExistingProduct())
            idTextField.setText(presentationProduct.getId());
        else
            idTextField.setText("Auto-Gen");

        nameTextField.setText(presentationProduct.getName());
        invTextField.setText(presentationProduct.getStock());
        priceTextField.setText(presentationProduct.getPrice());
        minTextField.setText(presentationProduct.getMin());
        maxTextField.setText(presentationProduct.getMax());
    }

    /**
     * Sets up the form validators
     */
    private void setupFormValidators() {
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
                return min <= Integer.parseInt(presentationProduct.getMax());
            } catch (Exception e) {
                return false;
            }
        }, (s) -> presentationProduct.setMin(s));
        maxValidator = new TextFieldValidator(maxTextField, AcceptableInputUtil::isAcceptableInt, (s) -> true, (s) -> presentationProduct.setMax(s));

        nameValidator.setErrorLabel(nameInvalidLabel, "Name must be non-empty");
        priceValidator.setErrorLabel(priceInvalidLabel, "Price must be a non-empty integer");
        invValidator.setErrorLabel(invInvalidLabel, "Inv must be a valid integer between min and max (inclusive)");
        minValidator.setErrorLabel(minInvalidLabel, "Min must be a valid integer less than or equal to max");

        formValidator = new FormValidator(List.of(nameValidator, invValidator, priceValidator, minValidator, maxValidator));
        formValidator.revalidateForm();
    }

    /**
     * Sets up the allPartsTableView
     */
    private void setupAllPartsTableView() {
        TableColumn<Part, Integer> idColumn = new TableColumn<>("Part ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> nameColumn = new TableColumn<>("Part Name");
        nameColumn.setMinWidth(50);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> priceColumn  = new TableColumn<>("Price / Cost per Unit");
        priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Part, Integer> stockColumn  = new TableColumn<>("Inventory Level");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        allPartsTableView.setItems(getInventory().getAllParts());
        allPartsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        allPartsTableView.setEditable(false);
    }

    /**
     * Sets up the associatedPartsTableView
     */
    private void setupAssociatedPartsTableView() {
        TableColumn<Part, Integer> idColumn = new TableColumn<>("Part ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> nameColumn = new TableColumn<>("Part Name");
        nameColumn.setMinWidth(50);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> priceColumn  = new TableColumn<>("Price / Cost per Unit");
        priceColumn.setMinWidth(150);
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

    /**
     * Revalidates the form
     */
    public void revalidateForm() {
        formValidator.revalidateForm();
    }

    /**
     * Search for parts in the allPartsTableView using the query in the searchPartsTextField
     */
    public void searchParts() {
        String query = searchPartsTextField.getText();
        boolean hasError = false;
        searchPartsErrorLabel.setVisible(false);
        try {
            int id = Integer.parseInt(query);
            Part result = getInventory().lookupPart(id);
            if (result == null) {
                hasError = true;
            }
            else {
                allPartsTableView.scrollTo(result);
                allPartsTableView.getSelectionModel().select(result);
            }
        } catch (NumberFormatException e) {
            ObservableList<Part> results = getInventory().lookupPart(query);
            if (results.isEmpty() && !query.isBlank()) {
                hasError = true;
            } else {
                allPartsTableView.setItems(results);
                allPartsTableView.getSelectionModel().clearSelection();
            }
        } finally {
            if (hasError) {
                allPartsTableView.setItems(getInventory().getAllParts());
                searchPartsErrorLabel.setVisible(true);
            }
        }
    }

    /**
     * Adds the selected part in the allPartsTableView to the product (and the associatedPartsTableView)
     */
    public void addAssociatedPart() {
        SelectionModel<Part> selectionModel = allPartsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        if (selectedPart != null) {
            presentationProduct.addAssociatedPart(selectedPart);
        }
    }

    /**
     * Removes the selected part in the associatedPartsTableView from the product
     */
    public void removeAssociatedPart() {
        SelectionModel<Part> selectionModel = associatedPartsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        if (selectedPart != null) {
            getDialogManager().showDeleteConfirmationDialog(() -> {
                presentationProduct.deleteAssociatedPart(selectedPart);
            }, null);
        }
    }

    /**
     * If the form data is valid, saves the product to inventory
     */
    public void onSave() {
        if (!formValidator.formIsValid()) {
            return;
        }

        if (presentationProduct.isExistingProduct()) {
            getInventory().updateProduct(index, presentationProduct.toProduct());
        } else {
            getInventory().addProduct(presentationProduct.toProduct());
        }

        getScreenNavigator().switchToMainForm();
    }

    /**
     * Switches back to the MainForm without saving anything upon confirmation via a confirmation dialog
     */
    public void onCancel() {
        getDialogManager().showCancelConfirmationDialog(() -> {
            getScreenNavigator().switchToMainForm();
        }, null);
    }
}
