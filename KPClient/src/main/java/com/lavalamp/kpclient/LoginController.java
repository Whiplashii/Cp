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
import pojo.Content;
import pojo.User;
import request.GetContentRequest;
import request.IRequest;
import response.GetContentResponse;
import response.LoginResponse;

import java.io.IOException;
import java.util.ArrayList;
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


    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        System.out.println(UserName.getText());
        System.out.println(passwordField.getText());

        LoginRequestCreator loginRequestCreator = new LoginRequestCreator();
        IRequest request = loginRequestCreator.CreateRequest(new User(UserName.getText(), passwordField.getText()));
        if (serverClient == null) {
            serverClient = ServerClient.ConnectToServer();
        }
        serverClient.SendRequest(request);
        LoginResponse loginResponse = (LoginResponse) serverClient.GetResponse();
        if (!loginResponse.accessGranted) {
            return;
        }

        GetContentRequest getContentRequest = new GetContentRequest(new User());
        serverClient.SendRequest(getContentRequest);
        GetContentResponse getContentResponse = (GetContentResponse) serverClient.GetResponse();
        LoadMainMenu(event,getContentResponse.contentList);
        //LoadNewScene(event, "main-menu.fxml");
    }

    private void LoadMainMenu(ActionEvent event, ArrayList<Content> contentList){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenuController = loader.getController();
            mainMenuController.SetContent(contentList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void onRegistrationButtonClick(ActionEvent event) {
        LoadNewScene(event, "registration-view.fxml");
    }

    private void LoadNewScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(sceneName)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}