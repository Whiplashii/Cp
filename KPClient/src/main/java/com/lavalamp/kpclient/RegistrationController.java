package com.lavalamp.kpclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private DatePicker datePicker;

    private Scene scene;
    private Stage stage;

    @FXML
    protected void OnSubmitButtonClick(ActionEvent event){
        SetAccount();
        TransactToLogin(event);
    }
    private void SetAccount(){
        System.out.println(usernameTextField.getText());
        System.out.println(passwordField.getText());
        System.out.println(datePicker.getValue());
    }
    @FXML
    protected void OnCancelButtonClick(ActionEvent event){
        TransactToLogin(event);
    }
    private void TransactToLogin(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
