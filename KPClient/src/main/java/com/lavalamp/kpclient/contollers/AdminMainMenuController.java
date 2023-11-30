package com.lavalamp.kpclient.contollers;

import client.ServerClient;
import com.lavalamp.kpclient.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pojo.User;
import request.LogoutRequest;

import java.io.IOException;
import java.util.Objects;

public class AdminMainMenuController {

    private ServerClient serverClient;

    public void Initialize(User user){

    }

    public void LogoutButtonClick(ActionEvent event) {
        if (serverClient == null) {
            serverClient = ServerClient.ConnectToServer();
        }
        serverClient.SendRequest(new LogoutRequest());
        serverClient.GetResponse();
        LoadNewScene(event, "login-view.fxml");
    }
    private void LoadNewScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource(sceneName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
