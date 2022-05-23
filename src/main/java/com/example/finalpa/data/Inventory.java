package com.example.finalpa.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Inventory {
    private final ObservableList<Part> allParts = FXCollections.observableList(new LinkedList<>());
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
                    .filter(p -> p.getName().startsWith(partName))
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
                    .filter(p -> p.getName().startsWith(productName))
                    .toList()
        );
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
