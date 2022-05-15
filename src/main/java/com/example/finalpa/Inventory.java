package com.example.finalpa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.Objects;

public class Inventory {
    private final ObservableList<Part> allParts = FXCollections.observableList(new LinkedList<Part>());
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
    public Part lookupPart(String partName) {
        return allParts
                .stream()
                .filter(p -> Objects.equals(p.getName(), partName))
                .findFirst()
                .orElse(null);
    }
    public Product lookupProduct(int productId) {
        return allProducts
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);
    }
    public Product lookupProduct(String productName) {
        return allProducts
                .stream()
                .filter(p -> Objects.equals(p.getName(), productName))
                .findFirst()
                .orElse(null);
    }
    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    public void updateProducts(int index, Product selectedProduct) {
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
