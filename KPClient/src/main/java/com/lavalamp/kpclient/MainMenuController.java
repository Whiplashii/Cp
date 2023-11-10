package com.lavalamp.kpclient;

import client.ServerClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import request.LogoutRequest;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {
    ServerClient serverClient;

    @FXML
    private VBox objectsVBox;

    @FXML
    public void LogoutButtonClick(ActionEvent event){
        if(serverClient == null){
            serverClient = ServerClient.ConnectToServer();
        }
        serverClient.SendRequest(new LogoutRequest());
        serverClient.GetResponse();
        LoadNewScene(event,"login-view.fxml");
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
    @FXML
    private void fun(){
        for (int i = 0;i < 10;i++) {
            HBox hBox = new HBox();
            hBox.getChildren().add(new ImageView());
            hBox.getChildren().add(new Label("fuck"));
            objectsVBox.getChildren().add(hBox);
        }
    }
    @FXML
    private void clear(){
        objectsVBox.getChildren().clear();
    }

}
