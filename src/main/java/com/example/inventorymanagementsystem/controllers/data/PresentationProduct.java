package com.example.inventorymanagementsystem.controllers.data;

import com.example.inventorymanagementsystem.data.Part;
import com.example.inventorymanagementsystem.data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

/**
 * A version of the Product POJO built to more easily work with the UI
 */
public class PresentationProduct {
    /**
     * A list of all associated parts
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableList(new LinkedList<>());
    /**
     * The id (as a String, allows for TextFields to more easily work with this data)
     */
    private String id = "0";

    /**
     * The name
     */
    private String name = "";

    /**
     * The price (as a String, allows for TextFields to more easily work with this data)
     */
    private String price = "0.0";
    /**
     * The amount of this product currently in stock
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String stock = "0";

    /**
     * The minimum amount of this product currently in stock
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String min = "0";

    /**
     * The maximum amount of this product currently in stock
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String max = "0";

    /**
     * Whether this product already exists in the inventory or not
     */
    private boolean isExistingProduct = true;

    /**
     * Constructs a new PresentationProduct from an existing Product (or pass null if creating a new Product)
     * @param product The product that this PresentationProduct is based on
     */
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

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The name
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price The price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return The amount of this product currently available
     */
    public String getStock() {
        return stock;
    }

    /**
     * @param stock The stock to set
     */
    public void setStock(String stock) {
        this.stock = stock;
    }

    /**
     * @return The minimum amount of this product currently available
     */
    public String getMin() {
        return min;
    }

    /**
     * @param min The min to set
     */
    public void setMin(String min) {
        this.min = min;
    }

    /**
     * @return The maximum amount of this product currently available
     */
    public String getMax() {
        return max;
    }

    /**
     * @param max The max to set
     */
    public void setMax(String max) {
        this.max = max;
    }

    /**
     * @return Whether this product already exists in inventory or not
     */
    public boolean isExistingProduct() {
        return isExistingProduct;
    }

    /**
     * Associates a new part to this product
     * @param part The new part to associate with this product
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Disassociates a part from this product
     * @param selectedAssociatedPart The part to disassociate
     * @return The success of the delete operation
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return All associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * Converts this PresentationProduct back into a Product
     * @return The new Product
     */
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
