package com.lavalamp.kpclient;

import javafx.scene.control.Alert;

public class DialogScreen {
    public static void ShowDialog(Alert.AlertType alertType,String dialogTitle,String dialogHeader,String dialogContent){
        Alert alert = new Alert(alertType);
        alert.setTitle(dialogTitle);
        alert.setHeaderText(dialogHeader);
        alert.setContentText(dialogContent);
        alert.showAndWait();
    }
}
