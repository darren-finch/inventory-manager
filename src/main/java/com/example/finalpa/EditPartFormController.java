package com.example.finalpa;

import javafx.event.ActionEvent;
import org.jetbrains.annotations.Nullable;

public class EditPartFormController extends BaseController {
    private @Nullable Part part;
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
}
