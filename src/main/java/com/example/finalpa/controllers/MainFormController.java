package com.example.finalpa.controllers;

import com.example.finalpa.data.InHouse;
import com.example.finalpa.data.Inventory;
import com.example.finalpa.data.Part;
import com.example.finalpa.data.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            ObservableList<Part> parts = getInventory().lookupPart(query);
            System.out.println(parts);
            partsTableView.setItems(parts);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    public void addPart(ActionEvent e) {
        getScreenNavigator().switchToEditPartForm(null);
    }
    public void modifyPart(ActionEvent e) {
        getScreenNavigator().switchToEditPartForm(new InHouse(0, "Wheel", 10.99, 2, 1, 8, 199));
    }
    public void deletePart(ActionEvent e) {
        System.out.println("deletePart");
    }
    public void exit(ActionEvent e) {
        Platform.exit();
    }
    public void searchProducts() {
        String query = searchProductsTextField.getText();
        // Inventory really should handle this logic of whether to search via id or not, but I'm adhering to the UML diagrams
        try {
            int productId = Integer.parseInt(query);
            productsTableView.setItems(FXCollections.observableList(List.of(getInventory().lookupProduct(productId))));
        } catch (NumberFormatException exception) {
            ObservableList<Product> products = getInventory().lookupProduct(query);
            System.out.println(products);
            productsTableView.setItems(products);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    public void addProduct(ActionEvent e) {
        getScreenNavigator().switchToEditProductForm(null);
    }
    public void modifyProduct(ActionEvent e) {
        getScreenNavigator().switchToEditProductForm(new Product(0, "Bike", 99.99, 5, 3, 10));
    }
    public void deleteProduct(ActionEvent e) {
        System.out.println("deleteProduct");
    }
}
