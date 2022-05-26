package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.InHouse;
import com.example.inventorymanagementsystem.data.Outsourced;
import com.example.inventorymanagementsystem.data.Part;


public class PresentationPart {
    private String id = "0";
    private String name = "";
    private String price = "0.0";
    private String stock = "0";
    private String min = "0";
    private String max = "0";

    private String machineId = "0";

    private String companyName = "";

    private boolean isInHousePart = true;

    private boolean isExistingPart = false;

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

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean getIsInHousePart() {
        return isInHousePart;
    }

    public void setIsInHousePart(boolean isInHousePart) {
        this.isInHousePart = isInHousePart;
    }

    public Part toPart() {
        try {
            if (isInHousePart) {
                return new InHouse(Integer.parseInt(id), name, Double.parseDouble(price), Integer.parseInt(stock), Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(machineId));
            } else {
                return new Outsourced(Integer.parseInt(id), name, Double.parseDouble(price), Integer.parseInt(stock), Integer.parseInt(min), Integer.parseInt(max), companyName);
            }
        } catch (Exception e) {
            throw new RuntimeException("A field has invalid data.");
        }
    }

    public boolean isExistingPart() {
        return isExistingPart;
    }
}