package com.example.inventorymanagementsystem.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

/**
 * Contains an in-memory cache of parts and products that the user can perform CRUD operations on
 */
public class Inventory {
    /**
     * All the parts
     */
    private final ObservableList<Part> allParts = FXCollections.observableList(new LinkedList<>());
    /**
     * All the products
     */
    private final ObservableList<Product> allProducts = FXCollections.observableList(new LinkedList<>());

    /**
     * The id for the next part to be added (is auto-incremented)
     */
    private int nextPartId = 0;

    /**
     * The id for the next product to be added (is auto-incremented)
     */
    private int nextProductId = 0;

    /**
     * Adds a new part with an auto-generated id
     * @param newPart
     */
    public void addPart(Part newPart) {
        newPart.setId(nextPartId++);
        allParts.add(newPart);
    }

    /**
     * Adds a new product with an auto-generated id
     * @param newProduct
     */
    public void addProduct(Product newProduct) {
        newProduct.setId(nextProductId++);
        allProducts.add(newProduct);
    }

    /**
     * @param partId The id used to search
     * @return The first part with the specified id, if no part is found, returns null
     */
    public Part lookupPart(int partId) {
        return allParts
                .stream()
                .filter(p -> p.getId() == partId)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param partName The name of the part to search for
     * @return A list of parts, filtered by the partName parameter
     */
    public ObservableList<Part> lookupPart(String partName) {
        return FXCollections.observableList(
            allParts
                .stream()
                .filter(p -> p.getName().toLowerCase().contains(partName.toLowerCase()))
                .toList()
        );
    }

    /**
     * @param productId The id used to search
     * @return The first product with the specified id, if no product is found, returns null
     */
    public Product lookupProduct(int productId) {
        return allProducts
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param productName The name of the product to search for
     * @return A list of products, filtered by the productName parameter
     */
    public ObservableList<Product> lookupProduct(String productName) {
        return FXCollections.observableList(
            allProducts
                .stream()
                .filter(p -> p.getName().toLowerCase().contains(productName.toLowerCase()))
                .toList()
        );
    }

    /**
     * Updates a part
     * @param index The index of the part to update
     * @param selectedPart The new part data
     */
    public void updatePart(int index, Part selectedPart) {
        if (0 <= index) {
            allParts.set(index, selectedPart);
        }
    }

    /**
     * Updates a product
     * @param index The index of the product to update
     * @param newProduct The new product data
     */
    public void updateProduct(int index, Product newProduct) {
        if (0 <= index) {
            allProducts.set(index, newProduct);
        }
    }

    /**
     * Deletes a part
     * @param selectedPart The part to delete
     * @return The success of the delete operation
     */
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Deletes a product
     * @param selectedProduct The product to delete
     * @return The success of the delete operation
     */
    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return All parts in memory
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return All products in memory
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
