package com.example.inventorymanagementsystem;

import javafx.scene.Node;

public class Util {
    public static void hideAndRemoveFromDocumentFlow(Node node) {
        node.setManaged(false);
        node.setVisible(false);
    }

    public static void showAndAddBackToDocumentFlow(Node node) {
        node.setManaged(true);
        node.setVisible(true);
    }
}
