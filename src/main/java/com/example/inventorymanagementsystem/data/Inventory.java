package com.example.inventorymanagementsystem.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private final ObservableList<Part> allParts = FXCollections.observableList(new LinkedList<>(List.of(new InHouse(0, "Wheel", 99.9, 3, 1, 5, 199), new Outsourced(1, "Bike Frame", 159.90, 5, 5, 10, "RAZOR"))));
    private final ObservableList<Product> allProducts = FXCollections.observableList(new LinkedList<>());

    private int nextPartId = 0;
    private int nextProductId = 0;

    public void addPart(Part newPart) {
        newPart.setId(nextPartId++);
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        newProduct.setId(nextProductId++);
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
        if (0 <= index) {
            allParts.set(index, selectedPart);
        }
    }
    public void updateProduct(int index, Product selectedProduct) {
        if (0 <= index) {
            allProducts.set(index, selectedProduct);
        }
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
