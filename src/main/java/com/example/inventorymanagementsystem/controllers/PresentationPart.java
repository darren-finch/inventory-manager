package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.InHouse;
import com.example.inventorymanagementsystem.data.Outsourced;
import com.example.inventorymanagementsystem.data.Part;

public class PresentationPart {
    private int id = 0;
    private String name = "";
    private double price = 0.0;
    private int stock = 0;
    private int min = 0;
    private int max = 0;

    private int machineId = 0;

    private String companyName = "";

    private boolean isInHousePart = true;

    private boolean isExistingPart = false;

    public PresentationPart(Part part) {
        if (part == null) {
            return;
        }

        isExistingPart = true;
        this.id = part.getId();
        this.name = part.getName();
        this.stock = part.getStock();
        this.price = part.getPrice();
        this.min = part.getMin();
        this.max = part.getMax();

        if (part instanceof InHouse) {
            this.machineId = ((InHouse) part).getMachineId();
        } else {
            this.companyName = ((Outsourced) part).getCompanyName();
            isInHousePart = false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMachineId() {
        if (!isInHousePart)
            throw new RuntimeException("Part is Outsourced, has no machineId!");
        return machineId;
    }

    public void setMachineId(int machineId) {
        if (!isInHousePart)
            throw new RuntimeException("Part is Outsourced, has no machineId!");
        this.machineId = machineId;
    }

    public String getCompanyName() {
        if (isInHousePart)
            throw new RuntimeException("Part is InHouse, has no companyName!");
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (isInHousePart)
            throw new RuntimeException("Part is InHouse, has no companyName!");
        this.companyName = companyName;
    }

    public boolean getIsInHousePart() {
        return isInHousePart;
    }

    public void setIsInHousePart(boolean isInHousePart) {
        this.isInHousePart = isInHousePart;
    }

    public Part toPart() {
        if (isInHousePart) {
            return new InHouse(id, name, price, stock, min, max, machineId);
        } else {
            return new Outsourced(id, name, price, stock, min, max, companyName);
        }
    }

    public boolean isExistingPart() {
        return isExistingPart;
    }
}
