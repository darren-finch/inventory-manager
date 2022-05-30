package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The main screen for the application. Allows the user to add/modify/delete/search parts and products.
 */
public class MainFormController extends BaseController {
    /**
     * The TableView for parts
     */
    @FXML
    public TableView<Part> partsTableView;
    /**
     * The TableView for products
     */
    @FXML
    private TableView<Product> productsTableView;
    /**
     * The search bar for parts
     */
    @FXML
    private TextField searchPartsTextField;
    /**
     * The search bar for products
     */
    @FXML
    private TextField searchProductsTextField;
    /**
     * The error label for searching parts
     */
    @FXML
    private Label searchPartsErrorLabel;
    /**
     * The error label for searching products
     */
    @FXML
    private Label searchProductsErrorLabel;

    /**
     * Sets up UI
     */
    @Override
    public void setupUI() {
        setupPartsTableView();
        setupProductsTableView();
    }

    /**
     * Sets up the parts table view
     */
    private void setupPartsTableView() {
        TableColumn<Part, Integer> idColumn = new TableColumn<>("Part ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> nameColumn = new TableColumn<>("Part Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Integer> stockColumn  = new TableColumn<>("Inventory Level");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Part, Double> priceColumn  = new TableColumn<>("Price / Cost per Unit");
        priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTableView.setItems(getInventory().getAllParts());
        getInventory().getAllParts().addListener((InvalidationListener) observable -> {
            searchParts();
        });
        partsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        partsTableView.setEditable(false);
    }

    /**
     * Sets up the products table view
     */
    private void setupProductsTableView() {
        TableColumn<Product, Integer> idColumn = new TableColumn<>("Product ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> stockColumn  = new TableColumn<>("Inventory Level");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Product, Double> priceColumn  = new TableColumn<>("Price / Cost per Unit");
        priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(getInventory().getAllProducts());
        getInventory().getAllProducts().addListener((InvalidationListener) observable -> {
            searchProducts();
        });
        productsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        productsTableView.setEditable(false);
    }

    /**
     * Searches the parts table view based on the query in the parts search bar. Four things can happen:
     * 1. If the query is a valid id, the part with that id will be scrolled to and highlighted
     * 2. If the query is not an id, the parts list is filtered by name.
     * 3. If there is a valid query, but there is no part with that id or the parts list is empty, an error is displayed
     * 4. If there is an error or the query is blank, all parts are displayed in the TableView
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
                partsTableView.scrollTo(result);
                partsTableView.getSelectionModel().select(result);
            }
        } catch (NumberFormatException e) {
            ObservableList<Part> results = getInventory().lookupPart(query);
            if (results.isEmpty() && !query.isBlank()) {
                hasError = true;
            } else {
                partsTableView.setItems(results);
                partsTableView.getSelectionModel().clearSelection();
            }
        } finally {
            if (hasError) {
                partsTableView.setItems(getInventory().getAllParts());
                searchPartsErrorLabel.setVisible(true);
            }
        }
    }

    /**
     * Navigates to the Edit Part form and passes default values because we're adding a new part
     */
    public void addPart() {
        getScreenNavigator().switchToEditPartForm(-1,null);
    }

    /**
     * Navigates to the Edit Part form and passes the selected part in the parts TableView
     */
    public void modifyPart() {
        SelectionModel<Part> selectionModel = partsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        int selectedIndex = selectionModel.getSelectedIndex();
        if (selectedPart != null)
            getScreenNavigator().switchToEditPartForm(selectedIndex, selectedPart);
    }

    /**
     * Deletes the currently selected part in the parts TableView after confirmation via a confirmation dialog.
     */
    public void deletePart() {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            getDialogManager().showDeleteConfirmationDialog(() -> {
                getInventory().deletePart(selectedPart);
            }, null);
        }
    }

    /**
     * Exits the application after confirmation via a confirmation dialog.
     */
    public void exit() {
        getDialogManager().showExitConfirmationDialog(Platform::exit, null);
    }

    /**
     * Refreshes the products table view based on the query in the products search bar. Four things can happen:
     * 1. If the query is a valid id, the product with that id will be scrolled to and highlighted
     * 2. If the query is not an id, the products list is filtered by name.
     * 3. If there is a valid query, but there is no product with that id or the products list is empty, an error is displayed
     * 4. If there is an error or the query is blank, all products are displayed in the TableView
     */
    public void searchProducts() {
        String query = searchProductsTextField.getText();
        boolean hasError = false;
        searchProductsErrorLabel.setVisible(false);
        try {
            int id = Integer.parseInt(query);
            Product result = getInventory().lookupProduct(id);
            if (result == null) {
                hasError = true;
            }
            else {
                productsTableView.scrollTo(result);
                productsTableView.getSelectionModel().select(result);
            }
        } catch (NumberFormatException e) {
            ObservableList<Product> results = getInventory().lookupProduct(query);
            if (results.isEmpty() && !query.isBlank()) {
                hasError = true;
            } else {
                productsTableView.setItems(results);
                productsTableView.getSelectionModel().clearSelection();
            }
        } finally {
            if (hasError) {
                productsTableView.setItems(getInventory().getAllProducts());
                searchProductsErrorLabel.setVisible(true);
            }
        }
    }

    /**
     * Navigates to the Edit Product form and passes default values because we're adding a new Product
     */
    public void addProduct() {
        getScreenNavigator().switchToEditProductForm(-1,null);
    }

    /**
     * Navigates to the Edit Product form and passes the selected product in the products TableView
     */
    public void modifyProduct() {
        SelectionModel<Product> selectionModel = productsTableView.getSelectionModel();
        Product selectedProduct = selectionModel.getSelectedItem();
        int selectedIndex = selectionModel.getSelectedIndex();
        if (selectedProduct != null)
            getScreenNavigator().switchToEditProductForm(selectedIndex, selectedProduct);
    }

    /**
     * Deletes the currently selected product in the products TableView after confirmation via a confirmation dialog.
     */
    public void deleteProduct() {
        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            getDialogManager().showDeleteConfirmationDialog(() -> {
                if (getInventory().lookupProduct(selectedProduct.getId()).getAllAssociatedParts().isEmpty())
                    getInventory().deleteProduct(selectedProduct);
                else
                    getDialogManager().showAlertDialog("Cannot delete, please remove associated parts before deleting this product.");
            }, null);
        }
    }
}
