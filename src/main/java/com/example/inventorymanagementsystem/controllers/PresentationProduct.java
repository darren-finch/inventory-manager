package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public class PresentationProduct {
    private final ObservableList<Part> associatedParts = FXCollections.observableList(new LinkedList<>());
    private String id = "0";
    private String name = "";
    private String price = "0.0";
    private String stock = "0";
    private String min = "0";
    private String max = "0";
    private boolean isExistingProduct = true;

    public PresentationProduct(Product product) {
        if (product == null) {
            isExistingProduct = false;
            return;
        }

        this.id = Integer.toString(product.getId());
        this.name = product.getName();
        this.price = Double.toString(product.getPrice());
        this.stock = Integer.toString(product.getStock());
        this.min = Integer.toString(product.getMin());
        this.max = Integer.toString(product.getMax());

        associatedParts.addAll(product.getAllAssociatedParts());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public boolean isExistingProduct() {
        return isExistingProduct;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public Product toProduct() {
        try {
            Product result = new Product(Integer.parseInt(id), name, Double.parseDouble(price), Integer.parseInt(stock), Integer.parseInt(min), Integer.parseInt(max));
            for (Part associatedPart : associatedParts) {
                result.addAssociatedPart(associatedPart);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("A PresentationProduct field has invalid data.");
        }
    }
}
