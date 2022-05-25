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
        if (!isInHousePart)
            throw new RuntimeException("Part is Outsourced, has no machineId!");
        return machineId;
    }

    public void setMachineId(String machineId) {
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


//
//public class PresentationPart {
//    private final SimpleStringProperty id = new SimpleStringProperty("0");
//    private final SimpleStringProperty name = new SimpleStringProperty("");
//    private final SimpleStringProperty price = new SimpleStringProperty("0.0");
//    private final SimpleStringProperty stock = new SimpleStringProperty("0");
//    private final SimpleStringProperty min = new SimpleStringProperty("0");
//    private final SimpleStringProperty max = new SimpleStringProperty("0");
//
//    private final SimpleStringProperty machineId = new SimpleStringProperty("0");
//
//    private final SimpleStringProperty companyName = new SimpleStringProperty();
//
//    private boolean isInHousePart = true;
//
//    private boolean isExistingPart = false;
//
//    public PresentationPart(Part part) {
//        if (part == null) {
//            return;
//        }
//
//        isExistingPart = true;
//        this.id.set(part.getId());
//        this.name.set(part.getName());
//        this.stock.set(part.getStock());
//        this.price.set(part.getPrice());
//        this.min.set(part.getMin());
//        this.max.set(part.getMax());
//
//        if (part instanceof InHouse) {
//            this.machineId.set(((InHouse) part).getMachineId());
//        } else {
//            this.companyName.set(((Outsourced) part).getCompanyName());
//            isInHousePart = false;
//        }
//    }
//
//    public int getId() {
//        return id.get();
//    }
//
//    public void setId(int id) {
//        this.id.set(id);
//    }
//
//    public String getName() {
//        return name.get();
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//    }
//
//    public double getPrice() {
//        return price.get();
//    }
//
//    public void setPrice(double price) {
//        this.price.set(price);
//    }
//
//    public int getStock() {
//        return stock.get();
//    }
//
//    public void setStock(int stock) {
//        this.stock.set(stock);
//    }
//
//    public int getMin() {
//        return min.get();
//    }
//
//    public void setMin(int min) {
//        this.min.set(min);
//    }
//
//    public int getMax() {
//        return max.get();
//    }
//
//    public void setMax(int max) {
//        this.max.set(max);
//    }
//
//    public int getMachineId() {
//        if (!isInHousePart)
//            throw new RuntimeException("Part is Outsourced, has no machineId!");
//        return machineId.get();
//    }
//
//    public void setMachineId(int machineId) {
//        if (!isInHousePart)
//            throw new RuntimeException("Part is Outsourced, has no machineId!");
//        this.machineId.set(machineId);
//    }
//
//    public String getCompanyName() {
//        if (isInHousePart)
//            throw new RuntimeException("Part is InHouse, has no companyName!");
//        return companyName.get();
//    }
//
//    public void setCompanyName(String companyName) {
//        if (isInHousePart)
//            throw new RuntimeException("Part is InHouse, has no companyName!");
//        this.companyName.set(companyName);
//    }
//
//    public boolean getIsInHousePart() {
//        return isInHousePart;
//    }
//
//    public void setIsInHousePart(boolean isInHousePart) {
//        this.isInHousePart = isInHousePart;
//    }
//
//    public Part toPart() {
//        // TODO: Turn into a try/catch block where you convert all data into the proper data types from Strings
//        if (isInHousePart) {
//            return new InHouse(getId(), getName(), getPrice(), getStock(), getMin(), getMax(), getMachineId());
//        } else {
//            return new Outsourced(getId(), getName(), getPrice(), getStock(), getMin(), getMax(), getCompanyName());
//        }
//    }
//
//    public boolean isExistingPart() {
//        return isExistingPart;
//    }
//}
