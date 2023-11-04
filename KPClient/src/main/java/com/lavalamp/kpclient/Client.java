package com.lavalamp.kpclient;

import client.ServerClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pojo.User;
import request.LoginRequest;

import java.io.IOException;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("OnlineShop");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ServerClient serverClient = new ServerClient();
        serverClient.ConnectToServer();
        serverClient.SendRequest(new LoginRequest(new User("debil","debil","debil@mail.ru")));
        launch();
        serverClient.CloseConnection();
    }
}
