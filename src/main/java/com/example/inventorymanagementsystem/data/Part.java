package com.example.inventorymanagementsystem.data;
/**
* Supplied class Part.java
*
* @author Darren Finch
*/
public abstract class Part {
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
     * The amount of inventory currently available
     */
    private int stock;
    /**
     * The minimum amount of inventory currently available
     */
    private int min;
    /**
     * The maximum amount of inventory currently available
     */
    private int max;

    /**
     * Constructs a new part
     * @param id the id
     * @param name the name
     * @param price the price
     * @param stock the amount of inventory currently available
     * @param min the minimum amount of inventory currently available
     * @param max the maximum amount of inventory currently available
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
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
    
}