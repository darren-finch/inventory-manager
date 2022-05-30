package com.example.inventorymanagementsystem;

import javafx.scene.Node;

/**
 * Provides utilities for the rest of the application
 */
public class Util {
    /**
     * Hides a node and keeps it from affecting the layout of other elements.
     * @param node the node to hide
     */
    public static void hideAndRemoveFromDocumentFlow(Node node) {
        node.setManaged(false);
        node.setVisible(false);
    }

    /**
     * Shows a node and allows it to affect the layout of other elements again
     * @param node the node to show
     */
    public static void showAndAddBackToDocumentFlow(Node node) {
        node.setManaged(true);
        node.setVisible(true);
    }
}
