package com.example.inventorymanagementsystem.data;

/**
 * An InHouse part was created by an in-house machine
 */
public class InHouse extends Part {
    /**
     * The id of the machine where this part came from
     */
    private int machineId;

    /**
     * Constructs a new InHouse part
     * @param id the id
     * @param name the name
     * @param price the price
     * @param stock the amount of stock currently available
     * @param min the minimum amount of stock currently available
     * @param max the maximum amount of stock currently available
     * @param machineId the id of the machine where this part came from
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * @return The id of the machine where this part came from
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId the new machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
