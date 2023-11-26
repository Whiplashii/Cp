package com.lavalamp.kpclient;

import client.ServerClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;
import request.LogoutRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenuController {
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userWalletLabel;
    private ServerClient serverClient;
    private ArrayList<Item> items = new ArrayList<>();

    private User user;

    @FXML
    private VBox objectsVBox;

    public void Initialize(User user,ArrayList<Content> contentList) {
        if (user != null) {
            this.user = user;
        } else {
            this.user = new User();
        }
        userNameLabel.setText(this.user.getUserName());
        userWalletLabel.setText(this.user.getWallet() + "$");
        SetContent(contentList);
    }

    @FXML
    public void LogoutButtonClick(ActionEvent event) {
        if (serverClient == null) {
            serverClient = ServerClient.ConnectToServer();
        }
        serverClient.SendRequest(new LogoutRequest());
        serverClient.GetResponse();
        LoadNewScene(event, "login-view.fxml");
    }

    @FXML
    public void SearchButtonClick(){
        objectsVBox.getChildren().clear();
        for(var item:items){
            if(item.GetTitle().contains(searchField.getText()))
                objectsVBox.getChildren().add(item);
            }
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
    private void fun() {
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.SetTitle(item.GetTitle() + i + 1);
            item.setOnMouseClicked(s -> {
                remove(item);
            });
            items.add(item);
            objectsVBox.getChildren().add(item);
        }
    }

    public void SetContent(ArrayList<Content> contentList){
        for(var content:contentList) {
            Item item = new Item();
            item.SetTitle(content.getContentName());
            item.SetPrice(content.getContentPrice() + "$");
            item.setOnMouseClicked((event)->remove(item));
            items.add(item);
            objectsVBox.getChildren().add(item);
        }
    }

    private void remove(Item item) {
        objectsVBox.getChildren().remove(item);
    }

    @FXML
    private void clear() {
        objectsVBox.getChildren().clear();
    }
}
