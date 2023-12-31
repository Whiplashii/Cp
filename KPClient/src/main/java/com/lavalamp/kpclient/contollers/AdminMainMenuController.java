package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.AdminMainMenuModule;
import enums.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;

import java.io.IOException;
import java.util.ArrayList;

public class AdminMainMenuController {

    @FXML
    private Label userName;
    @FXML
    private ChoiceBox<String> userRolesChoiceBox;
    @FXML
    private TextField searchField;
    private AdminMainMenuModule module;
    private User user;
    private ArrayList<Content> contentList;
    private ArrayList<User> users;
    private ArrayList<UserItem> userItems = new ArrayList<>();

    private ObservableList<String> roles = FXCollections.observableArrayList("Пользователь", "Администратор", "Создатель");

    @FXML
    private VBox userListVBox;
    public AdminMainMenuController(){
        module = new AdminMainMenuModule();
    }
    public void Initialize(User user){
        this.user = user;
        userName.setText( user.getUserName());
        contentList = module.GetContent(this.user);
        users = module.GetUsers();
        userRolesChoiceBox.setItems(roles);
        userRolesChoiceBox.setOnAction(this::ShowUsersSelectedUserRole);
        SetUserItems();
    }

    private void SetUserItems(){
        for(var user : users){
            UserItem userItem = new UserItem();
            userItem.setUserID(user.getId());
            userItem.SetUserName(user.getUserName());
            userItem.SetUserRole(user.getUserRole());
            userItem.getClickableArea().setOnAction((event -> OnUserItemClick(event,userItem.getUserID())));
            userItems.add(userItem);
            userListVBox.getChildren().add(userItem);
        }
    }

    @FXML
    public void SearchFieldTyped() {
        userListVBox.getChildren().clear();
        for (var item : userItems) {
            if (item.GetUserName().contains(searchField.getText()))
                userListVBox.getChildren().add(item);
        }
    }
    private void OnUserItemClick(ActionEvent event , int userID){
        LoadUserManagementView(event,GetUserFromList(userID));
    }

    private void ShowUsersSelectedUserRole(ActionEvent event){
        userListVBox.getChildren().clear();
        for (var item : userItems) {
            if(GetUserRoleByID(item.getUserID()) == UserRole.setInt(roles.indexOf(this.userRolesChoiceBox.getValue()) + 1)){
                userListVBox.getChildren().add(item);
            }
        }
    }

    private UserRole GetUserRoleByID(int id){
        for(var user : users){
            if(user.getId() == id){
                return user.getUserRole();
            }
        }
        return UserRole.user;
    }

    private User GetUserFromList(int id){
        for(var user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
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
    private void LoadUserManagementView(ActionEvent event,User redactedUser) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("user-management-view.fxml"));
            Parent root = loader.load();

            UserManagementController controller = loader.getController();
            controller.Initialize(user,redactedUser);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
