package com.lavalamp.kpclient;

import client.ServerClient;
import com.lavalamp.kpclient.requestCreator.LoginRequestCreator;
import enums.UserRole;
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
import request.GetContentRequest;
import request.IRequest;
import response.GetContentResponse;
import response.LoginResponse;
import java.io.IOException;
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
        User user = new User();
        //user.setUserRole(UserRole.admin);
        DecideMainMenuType(event,user);
    }

    private void DecideMainMenuType(ActionEvent event,User user){
        switch (user.getUserRole())
        {
            case user, creator ->LoadMainMenu(event,user);
            case admin ->LoadAdminMainMenu(event,user);
        }
    }

    private void LoadMainMenu(ActionEvent event,User user){
        try {
            GetContentRequest getContentRequest = new GetContentRequest(new User());
            serverClient.SendRequest(getContentRequest);
            GetContentResponse getContentResponse = (GetContentResponse) serverClient.GetResponse();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenuController = loader.getController();
            mainMenuController.Initialize(user,getContentResponse.contentList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadAdminMainMenu(ActionEvent event,User user){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-main-menu.fxml"));
            Parent root = loader.load();

            AdminMainMenuController adminMainMenuController = loader.getController();
            adminMainMenuController.Initialize(user);

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