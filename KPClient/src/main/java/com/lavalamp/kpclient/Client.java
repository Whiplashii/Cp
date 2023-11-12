package com.lavalamp.kpclient;

import client.ServerClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    public static void main(String[] args) {
        ServerClient serverClient = ServerClient.ConnectToServer();
        launch();
        serverClient.CloseConnection();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("OnlineShop");
        stage.setScene(scene);
        stage.show();
    }
}
