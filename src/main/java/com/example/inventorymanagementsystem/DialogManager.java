package com.example.inventorymanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Allows controllers to easily create dialogs without managing them directly
 */
public class DialogManager {
    /**
     * Whether we are currently showing a dialog or not
     */
    private boolean showingDialog = false;

    /**
     * Shows an exit confirmation dialog
     * @param onOk The function to execute when the user presses "Ok"
     * @param onCancel The function to execute when the user presses "Cancel"
     */
    public void showExitConfirmationDialog(Runnable onOk, Runnable onCancel) {
        showConfirmationDialog("Are you sure you want to quit?", onOk, onCancel);
    }

    /**
     * Shows a delete confirmation dialog
     * @param onOk The function to execute when the user presses "Ok"
     * @param onCancel The function to execute when the user presses "Cancel"
     */
    public void showDeleteConfirmationDialog(Runnable onOk, Runnable onCancel) {
        showConfirmationDialog("Are you sure you want to delete?", onOk, onCancel);
    }

    /**
     * Shows a cancel confirmation dialog
     * @param onOk The function to execute when the user presses "Ok"
     * @param onCancel The function to execute when the user presses "Cancel"
     */
    public void showCancelConfirmationDialog(Runnable onOk, Runnable onCancel) {
        showConfirmationDialog("Are you sure you want to cancel?", onOk, onCancel);
    }

    /**
     * Shows a generic confirmation dialog (user can choose Ok or Cancel)
     * @param confirmationPrompt The prompt displayed on the dialog
     * @param onOk The function to execute when the user presses "Ok"
     * @param onCancel The function to execute when the user presses "Cancel"
     */
    private void showConfirmationDialog(String confirmationPrompt, Runnable onOk, Runnable onCancel) {
        if (showingDialog) {
            return;
        }

        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION, confirmationPrompt);
        showingDialog = true;
        alertDialog.showAndWait().ifPresent(response -> {
            // This needs to be set to false before we run any responses to allow for dialog chaining
            showingDialog = false;
            if (response.equals(ButtonType.OK) && onOk != null) {
                onOk.run();
            } else if (response.equals(ButtonType.CANCEL) && onCancel != null) {
                onCancel.run();
            }
        });
    }

    /**
     * Shows a generic alert dialog (no choice, just an okay button)
     * @param alertText The alert text displayed on the dialog
     */
    public void showAlertDialog(String alertText) {
        if (showingDialog)
            return;

        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION, alertText);
        showingDialog = true;
        alertDialog.showAndWait().ifPresent(response -> {
            showingDialog = false;
        });
    }
}
