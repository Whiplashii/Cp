package com.lavalamp.kpclient;

import client.ServerClient;
import com.lavalamp.kpclient.requestCreator.LoginRequestCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.User;
import request.IRequest;
import response.LoginResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class LoginController {

    ServerClient serverClient;
    @FXML
    private Button loginButton;
    @FXML
    private Button registrationButton;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField passwordField;
    private Stage stage;
    private Scene scene;

    @FXML
    public void onLoginButtonClick(ActionEvent event){
        System.out.println(UserName.getText());
        System.out.println(passwordField.getText());
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            System.out.println(messageDigest.digest(passwordField.getText().getBytes(StandardCharsets.UTF_8)).toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        LoginRequestCreator loginRequestCreator = new LoginRequestCreator();
        IRequest request = loginRequestCreator.CreateRequest(new User(UserName.getText(),passwordField.getText()));
        if(serverClient == null) {
            serverClient = ServerClient.ConnectToServer();
        }
        serverClient.SendRequest(request);
       LoginResponse loginResponse =  (LoginResponse)serverClient.GetResponse();
       if(!loginResponse.accessGranted)return;
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    @FXML
    public void onRegistrationButtonClick(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}