package com.example.inventorymanagementsystem.data;

/**
 * An Outsourced part was created by a third-party company
 */
public class Outsourced extends Part {
    /**
     * The name of the company that created this part
     */
    private String companyName;

    /**
     * Constructs a new Outsourced part
     * @param id the id
     * @param name the name
     * @param price the price
     * @param stock the amount of parts currently available
     * @param min the minimum amount of parts currently available
     * @param max the maximum amount of parts currently available
     * @param companyName the name of the company that manufactured this part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return the name of the company that manufactured this part
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the new name of the company that manufactured this part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
