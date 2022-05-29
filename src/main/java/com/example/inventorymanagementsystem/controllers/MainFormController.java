package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MainFormController extends BaseController {
    @FXML
    public TableView<Part> partsTableView;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TextField searchPartsTextField;
    @FXML
    private TextField searchProductsTextField;

    @FXML
    private Label searchPartsErrorLabel;

    @FXML
    private Label searchProductsErrorLabel;

    @Override
    public void setupUI() {
        setupPartsTableView();
        setupProductsTableView();
    }

    @Override
    public void tearDown() {

    }

    private void setupPartsTableView() {
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

        partsTableView.setItems(getInventory().getAllParts());
        getInventory().getAllParts().addListener((InvalidationListener) observable -> {
            refreshParts();
        });
        partsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        partsTableView.setEditable(false);
    }

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
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(getInventory().getAllProducts());
        getInventory().getAllProducts().addListener((InvalidationListener) observable -> {
            refreshProducts();
        });
        productsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        productsTableView.setEditable(false);
    }

    private void refreshParts() {
        String query = searchPartsTextField.getText();
        searchPartsErrorLabel.setVisible(false);
        // Inventory really should handle this logic of whether to search via id or not, but I'm adhering to the UML diagrams
        try {
            int partId = Integer.parseInt(query);
            Part queryByIdResult = getInventory().lookupPart(partId);
            if (queryByIdResult == null)
                throw new RuntimeException();

            partsTableView.setItems(FXCollections.observableList(List.of(queryByIdResult)));
        } catch (NumberFormatException exception) {
            ObservableList<Part> parts = getInventory().lookupPart(query);
            if (parts.isEmpty())
                throw new RuntimeException();

            partsTableView.setItems(parts);
        } catch (Exception exception) {
            partsTableView.setItems(getInventory().getAllParts());
            searchPartsErrorLabel.setVisible(true);
        }
    }

    public void searchParts() {
        refreshParts();
    }
    public void addPart() {
        getScreenNavigator().switchToEditPartForm(-1,null);
    }
    public void modifyPart() {
        SelectionModel<Part> selectionModel = partsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        int selectedIndex = selectionModel.getSelectedIndex();
        if (selectedPart != null)
            getScreenNavigator().switchToEditPartForm(selectedIndex, selectedPart);
    }
    public void deletePart() {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            getDialogManager().showDeleteConfirmationDialog(() -> {
                getInventory().deletePart(selectedPart);
            }, null);
        }
    }
    public void exit() {
        getDialogManager().showExitConfirmationDialog(Platform::exit, null);
    }

    private void refreshProducts() {
        String query = searchProductsTextField.getText();
        searchProductsErrorLabel.setVisible(false);
        // Inventory really should handle this logic of whether to search via id or not, but I'm adhering to the UML diagrams
        try {
            int productId = Integer.parseInt(query);
            Product queryByIdResult = getInventory().lookupProduct(productId);
            if (queryByIdResult == null)
                throw new RuntimeException();

            productsTableView.setItems(FXCollections.observableList(List.of(queryByIdResult)));
        } catch (NumberFormatException exception) {
            ObservableList<Product> products = getInventory().lookupProduct(query);
            if (products.isEmpty())
                throw new RuntimeException();

            productsTableView.setItems(products);
        } catch (Exception exception) {
            productsTableView.setItems(getInventory().getAllProducts());
            searchProductsErrorLabel.setVisible(true);
        }
    }

    public void searchProducts() {
        refreshProducts();
    }
    public void addProduct() {
        getScreenNavigator().switchToEditProductForm(-1,null);
    }
    public void modifyProduct() {
        SelectionModel<Product> selectionModel = productsTableView.getSelectionModel();
        Product selectedProduct = selectionModel.getSelectedItem();
        int selectedIndex = selectionModel.getSelectedIndex();
        if (selectedProduct != null)
            getScreenNavigator().switchToEditProductForm(selectedIndex, selectedProduct);
    }
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
