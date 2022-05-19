package com.example.finalpa;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MainFormController extends BaseController {
    public void searchParts(ActionEvent e) {
        System.out.println("searchPart");
    }
    public void addPart(ActionEvent e) {
        getScreenNavigator().switchToEditPartForm(null);
    }
    public void modifyPart(ActionEvent e) {
        getScreenNavigator().switchToEditPartForm(new InHouse(0, "Bike", 99.99, 5, 3, 10, 199));
    }
    public void deletePart(ActionEvent e) {
        System.out.println("deletePart");
    }
    public void exit(ActionEvent e) {
        System.out.println("exit");
    }
    public void searchProducts(ActionEvent e) {
        System.out.println("searchProduct");
    }
    public void addProduct(ActionEvent e) {
        getScreenNavigator().switchToEditProductForm(null);
    }
    public void modifyProduct(ActionEvent e) {
        getScreenNavigator().switchToEditProductForm(new Product(0, "Bike", 99.99, 5, 3, 10));
    }
    public void deleteProduct(ActionEvent e) {
        System.out.println("deleteProduct");
    }
}
