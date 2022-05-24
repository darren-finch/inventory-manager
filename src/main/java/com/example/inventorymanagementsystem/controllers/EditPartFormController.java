package com.example.inventorymanagementsystem.controllers;

import com.example.inventorymanagementsystem.data.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditPartFormController extends BaseController {
    private Part part;

    public void setArgs(Part part) {
        this.part = part;
        System.out.println(part == null ? "No part, adding new one" : "Editing " + part.getName());
    }

    public void saveChanges(ActionEvent e) {
        getScreenNavigator().switchToMainForm();
    }

    public void cancel(ActionEvent e) {
        getScreenNavigator().switchToMainForm();
    }

    @Override
    public void setupUI() {

    }
}
