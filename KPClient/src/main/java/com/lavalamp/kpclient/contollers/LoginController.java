package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.LoginModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;
import response.LoginResponse;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    private final LoginModule loginModule;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;

    public LoginController() {
        loginModule = new LoginModule(this);
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        User user = new User(userName.getText(), passwordField.getText());
        LoginResponse loginResponse = loginModule.LogIn(user);
        if (loginResponse.getUser() == null) {
            System.out.println(loginResponse.getContext());
            ShowDialog(Alert.AlertType.ERROR,"Ошибка",null,loginResponse.getContext());
            return;
        }
        DecideMainMenuType(event, loginResponse.getUser());
    }

    @FXML
    public void onRegistrationButtonClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource("registration-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void DecideMainMenuType(ActionEvent event, User user) {
        switch (user.getUserRole()) {
            case user, creator -> LoadMainMenu(event, user);
            case admin -> LoadAdminMainMenu(event, user);
        }
    }

    private void ShowDialog(Alert.AlertType alertType,String dialogTitle,String dialogHeader,String dialogContent){
        Alert alert = new Alert(alertType);
        alert.setTitle(dialogTitle);
        alert.setHeaderText(dialogHeader);
        alert.setContentText(dialogContent);
        alert.showAndWait();
    }

    private void LoadMainMenu(ActionEvent event, User user) {
        try {
            ArrayList<Content> contentList = loginModule.GetContent();
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("main-menu.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenuController = loader.getController();
            mainMenuController.Initialize(user, contentList);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadAdminMainMenu(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("admin-main-menu.fxml"));
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
}