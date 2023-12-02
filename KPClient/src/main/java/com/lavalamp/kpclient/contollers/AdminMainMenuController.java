package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.AdminMainMenuModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;

import java.io.IOException;
import java.util.ArrayList;

public class AdminMainMenuController {
    private AdminMainMenuModule module;
    private User user;
    private ArrayList<Content> contentList;
    private ArrayList<User> users;
    public AdminMainMenuController(){
        module = new AdminMainMenuModule();
    }
    public void Initialize(User user){
        this.user = user;
        contentList = module.GetContent(this.user);
        users = module.GetUsers();
        users.forEach(System.out::println);

    }
    @FXML
    private void LogoutButtonClick(ActionEvent event) {
        module.LogOut();
        LoadLoginView(event);
    }

    private void LoadLoginView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource("login-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
