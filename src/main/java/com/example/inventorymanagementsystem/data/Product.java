package com.example.inventorymanagementsystem.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

/**
 * Contains similar data to a part, but contains a list of associated parts.
 * E.g a Bike contains a Bike Frame part and a Wheel part.
 */
public class Product {
    /**
     * A list of associated parts that make up this product.
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableList(new LinkedList<>());
    /**
     * The id
     */
    private int id;
    /**
     * The name
     */
    private String name;
    /**
     * The price
     */
    private double price;
    /**
     * The amount of products currently available
     */
    private int stock;
    /**
     * The minimum amount of products currently available
     */
    private int min;
    /**
     * The maximum amount of products currently available
     */
    private int max;

    /**
     * Constructs a new Product
     * @param id the product id
     * @param name the name
     * @param price the price
     * @param stock the amount of products currently available
     * @param min the minimum amount of products currently available
     * @param max the maximum amount of products currently available
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a new associated part
     * @param part the new part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Removes an associated part
     * @param selectedAssociatedPart the part to remove
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Gets all associated parts
     * @return a list of all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
