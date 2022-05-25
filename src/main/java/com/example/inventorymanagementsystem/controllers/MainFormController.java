package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @Override
    public void setupUI() {
        setupPartsTableView();
        setupProductsTableView();
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
        productsTableView.getColumns().addAll(idColumn, nameColumn, stockColumn, priceColumn);
        productsTableView.setEditable(false);
    }

    public void searchParts() {
        String query = searchPartsTextField.getText();
        // Inventory really should handle this logic of whether to search via id or not, but I'm adhering to the UML diagrams
        try {
            int partId = Integer.parseInt(query);
            partsTableView.setItems(FXCollections.observableList(List.of(getInventory().lookupPart(partId))));
        } catch (NumberFormatException exception) {
            System.out.println("QUERY: " + query);
            ObservableList<Part> parts = getInventory().lookupPart(query);
            partsTableView.setItems(parts);
        } catch (Exception exception) {
            partsTableView.setItems(getInventory().getAllParts());
        }
    }
    public void addPart() {
        getScreenNavigator().switchToEditPartForm(-1,null);
    }
    public void modifyPart(ActionEvent e) {
        SelectionModel<Part> selectionModel = partsTableView.getSelectionModel();
        Part selectedPart = selectionModel.getSelectedItem();
        int selectedIndex = selectionModel.getSelectedIndex();
        if (selectedPart != null)
            getScreenNavigator().switchToEditPartForm(selectedIndex, selectedPart);
    }
    public void deletePart(ActionEvent e) {
        getDialogManager().showDeleteConfirmationDialog(() -> {
            Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            if (selectedPart != null)
                getInventory().deletePart(selectedPart);
            // Shouldn't be doing this manually
            searchParts();
        }, null);
    }
    public void exit() {
        getDialogManager().showExitConfirmationDialog(Platform::exit, null);
    }
    public void searchProducts() {
        String query = searchProductsTextField.getText();
        // Inventory really should handle this logic of whether to search via id or not, but I'm adhering to the UML diagrams
        try {
            int productId = Integer.parseInt(query);
            productsTableView.setItems(FXCollections.observableList(List.of(getInventory().lookupProduct(productId))));
        } catch (NumberFormatException exception) {
            ObservableList<Product> products = getInventory().lookupProduct(query);
            productsTableView.setItems(products);
        } catch (Exception exception) {
            productsTableView.setItems(getInventory().getAllProducts());
        }
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
        getDialogManager().showDeleteConfirmationDialog(() -> {
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null)
                getInventory().deleteProduct(selectedProduct);
            // A manual refresh shouldn't be necessary
            searchProducts();
        }, null);
    }
}
