package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.RegistrationModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.User;
import response.RegistrationResponse;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {

    private RegistrationModule registrationModule;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailTextField;

    private Scene scene;
    private Stage stage;

    public RegistrationController(){
        registrationModule = new RegistrationModule(this);
    }

    @FXML
    protected void OnSubmitButtonClick(ActionEvent event) {
        RegistrationResponse registrationResponse = registrationModule.SignIn(SetAccount());
        if(!registrationResponse.accepted){
            return;
        }
        LoadLoginView(event);
    }

    private User SetAccount() {
        User user = new User(usernameTextField.getText(), passwordField.getText(), emailTextField.getText());
        return user;
    }

    @FXML
    protected void OnCancelButtonClick(ActionEvent event) {
        LoadLoginView(event);
    }

    private void LoadLoginView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Client.class.getResource("login-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
