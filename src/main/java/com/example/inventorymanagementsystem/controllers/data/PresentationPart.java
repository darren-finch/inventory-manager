package com.example.inventorymanagementsystem.controllers.data;

import com.example.inventorymanagementsystem.data.InHouse;
import com.example.inventorymanagementsystem.data.Outsourced;
import com.example.inventorymanagementsystem.data.Part;

/**
 * A version of the Part POJO built to more easily work with the UI.
 * Built to handle both InHouse and Outsourced parts
 */
public class PresentationPart {
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
     * The amount of this part currently in stock
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String stock = "0";

    /**
     * The minimum amount of this part currently in stock
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String min = "0";

    /**
     * The maximum amount of this part currently in stock
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String max = "0";

    /**
     * The machine id if this part is InHouse
     * (as a String, allows for TextFields to more easily work with this data)
     */
    private String machineId = "0";

    /**
     * The company name if this part is Outsourced
     */
    private String companyName = "";

    /**
     * Whether this part is InHouse or not
     */
    private boolean isInHousePart = true;

    /**
     * Whether this part already exists in inventory or not
     */
    private boolean isExistingPart = false;

    /**
     * Constructs a new PresentationPart from an existing Part (or pass null if creating a new Part)
     * @param part
     */
    public PresentationPart(Part part) {
        if (part == null) {
            return;
        }

        isExistingPart = true;
        this.id = Integer.toString(part.getId());
        this.name = part.getName();
        this.stock = Integer.toString(part.getStock());
        this.price = Double.toString(part.getPrice());
        this.min = Integer.toString(part.getMin());
        this.max = Integer.toString(part.getMax());

        if (part instanceof InHouse) {
            this.machineId = Integer.toString(((InHouse) part).getMachineId());
        } else {
            this.companyName = ((Outsourced) part).getCompanyName();
            isInHousePart = false;
        }
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
     * @return The amount of this part currently available
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
     * @return The minimum amount of this part currently available
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
     * @return The maximum amount of this part currently available
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
     * @return The machine id if the part is InHouse
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * @param machineId The machine id to set
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * @return The company name if this part is Outsourced
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName The company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return Whether this part is InHouse or not
     */
    public boolean getIsInHousePart() {
        return isInHousePart;
    }

    /**
     * @param isInHousePart Whether the part should be InHouse or not
     */
    public void setIsInHousePart(boolean isInHousePart) {
        this.isInHousePart = isInHousePart;
    }

    /**
     * @return Whether this part already exists in inventory or not
     */
    public boolean isExistingPart() {
        return isExistingPart;
    }

    /**
     * Converts this PresentationPart into a new Part
     * @return The new Part
     */
    public Part toPart() {
        try {
            if (isInHousePart) {
                return new InHouse(Integer.parseInt(id), name, Double.parseDouble(price), Integer.parseInt(stock), Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(machineId));
            } else {
                return new Outsourced(Integer.parseInt(id), name, Double.parseDouble(price), Integer.parseInt(stock), Integer.parseInt(min), Integer.parseInt(max), companyName);
            }
        } catch (Exception e) {
            throw new RuntimeException("A PresentationPart field has invalid data.");
        }
    }
}