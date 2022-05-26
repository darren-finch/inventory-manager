package com.example.inventorymanagementsystem.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public class Inventory {
    private final ObservableList<Part> allParts = FXCollections.observableList(new LinkedList<>());
    private final ObservableList<Product> allProducts = FXCollections.observableList(new LinkedList<>());

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        return allParts
                .stream()
                .filter(p -> p.getId() == partId)
                .findFirst()
                .orElse(null);
    }
    public ObservableList<Part> lookupPart(String partName) {
        return FXCollections.observableList(
            allParts
                    .stream()
                    .filter(p -> p.getName().toLowerCase().contains(partName.toLowerCase()))
                    .toList()
        );
    }
    public Product lookupProduct(int productId) {
        return allProducts
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);
    }
    public ObservableList<Product> lookupProduct(String productName) {
        return FXCollections.observableList(
            allProducts
                    .stream()
                    .filter(p -> p.getName().toLowerCase().contains(productName.toLowerCase()))
                    .toList()
        );
    }
    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    public void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
    public ObservableList<Part> getAllParts() {
        return allParts;
    }
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
