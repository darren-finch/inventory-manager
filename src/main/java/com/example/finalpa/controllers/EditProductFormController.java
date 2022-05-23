package com.example.finalpa.controllers;

import com.example.finalpa.data.Product;
import javafx.event.ActionEvent;
import org.jetbrains.annotations.Nullable;

public class EditProductFormController extends BaseController {
    private @Nullable Product product;
    public void setArgs(Product product) {
        this.product = product;
        System.out.println(product == null ? "No product, adding new one" : "Editing " + product.getName());
    }

    public void searchParts(ActionEvent e) {}
    public void addAssociatedPart(ActionEvent e) {
        System.out.println("addAssociatedPart");
    }
    public void modifyAssociatedPart(ActionEvent e) {
        System.out.println("modifyAssociatedPart");
    }
    public void removeAssociatedPart(ActionEvent e) {
        System.out.println("removeAssociatedParta");
    }
    public void savePart(ActionEvent e) {
        System.out.println("savePart");
    }
    public void cancel(ActionEvent e) {
        getScreenNavigator().switchToMainForm();
    }
    public void saveProduct(ActionEvent e) {}

    @Override
    public void setupUI() {

    }
}
