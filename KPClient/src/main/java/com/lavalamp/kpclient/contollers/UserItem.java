package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import enums.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class UserItem extends Pane {
    private int userID;
    @FXML
    private TextField userName;
    @FXML
    private TextField userRole;
    @FXML
    private Button clickableArea;

    public UserItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("user-item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String GetUserName() {
        return userName.getText();
    }

    public String GetUserRole() {
        return userRole.getText();
    }

    public void SetUserName(String title) {
        userName.setText(title);
    }

    public void SetUserRole(UserRole userRole) {
        this.userRole.setText(GetStringFromUserRole(userRole));
        switch (userRole) {
            case user -> this.userRole.setStyle("-fx-background-color :  green");
            case creator -> this.userRole.setStyle("-fx-background-color :  purple");
            case admin -> this.userRole.setStyle("-fx-background-color :  red");
        }
    }

    private String GetStringFromUserRole(UserRole userRole){
        if(userRole.equals(UserRole.user))return "Пользователь";
        if(userRole.equals(UserRole.creator))return "создатель";
        if(userRole.equals(UserRole.admin))return "Администратор";
        return "error";
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public Button getClickableArea() {
        return clickableArea;
    }
}
