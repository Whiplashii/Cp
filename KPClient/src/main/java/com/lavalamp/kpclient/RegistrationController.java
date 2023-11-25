package com.lavalamp.kpclient;

import client.ServerClient;
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
import request.RegistrationRequest;
import response.RegistrationResponse;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {

    private ServerClient serverClient;
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
        TransactToLogin(event);
        /*if (serverClient == null) {
            serverClient = ServerClient.ConnectToServer();
        }
        User user = SetAccount();
        serverClient.SendRequest(new RegistrationRequest(user));
        RegistrationResponse registrationResponse = (RegistrationResponse) serverClient.GetResponse();
        if (!registrationResponse.accepted) return;
        TransactToLogin(event);*/
    }

    private User SetAccount() {
        System.out.println(usernameTextField.getText());
        System.out.println(passwordField.getText());
        System.out.println(emailTextField.getText());
        User user = new User(usernameTextField.getText(), passwordField.getText(), emailTextField.getText());
        return user;
    }

    @FXML
    protected void OnCancelButtonClick(ActionEvent event) {
        TransactToLogin(event);
    }

    private void TransactToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static class MainMenuController {
    }
}
