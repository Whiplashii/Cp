package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.UserManagementModule;
import enums.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;

import java.io.IOException;
import java.util.ArrayList;

public class UserManagementController {

    @FXML
    private Label userNameLabel;
    @FXML
    private VBox contentItemVBox;
    @FXML
    private CheckBox isBannedCheckBox;
    @FXML
    private ChoiceBox<String> userRoleChoiceBox;
    private UserManagementModule module;
    private User adminUser;
    private User redactedUser;
    private ObservableList<String> roles = FXCollections.observableArrayList("Пользователь", "Администратор", "Создатель");
    private ArrayList<Content> contentList;
    private ArrayList<Item> itemList = new ArrayList<>();
    public UserManagementController(){
        module = new UserManagementModule();
    }
    public void Initialize(User adminUser,User redactedUser){
        this.adminUser = adminUser;
        this.redactedUser = redactedUser;
        if(redactedUser.getUserRole().equals(UserRole.creator)){
            contentList = module.GetContentByID(redactedUser);
            SetContent();
        }
        SetVisual();
    }
    private void SetVisual(){
        userNameLabel.setText(redactedUser.getUserName());
        isBannedCheckBox.setSelected(redactedUser.getBanned());
        userRoleChoiceBox.setItems(roles);
        userRoleChoiceBox.setValue(roles.get(redactedUser.getUserRole().getInt() - 1));
        userRoleChoiceBox.setOnAction((this::ChangeUserRole));
    }

    private void SetContent() {
        for (var content : contentList) {
            Item item = new Item();
            item.setItemID(content.getContentID());
            item.SetTitle(content.getContentName());
            item.SetPrice(content.getContentPrice() + "$");
            item.setImage(content.getImageURL());
            itemList.add(item);
            contentItemVBox.getChildren().add(item);
        }
    }
    @FXML
    private void ChangeUserRole(ActionEvent event){
        redactedUser.setUserRole(UserRole.setInt(roles.indexOf(userRoleChoiceBox.getValue()) + 1));
    }
    @FXML
    private void ChangeIsBanned(ActionEvent event){
        redactedUser.setBanned(isBannedCheckBox.isSelected());
    }
    @FXML
    private void OnSubmitButtonClick(ActionEvent event){
        System.out.println(redactedUser.getBanned() + redactedUser.getUserRole().toString());
        var response = module.UpdateUser(redactedUser);
        if(response.isUpdated()){
            System.out.println(response.getContext());
            return;
        }
        LoadAdminMainMenu(event,adminUser);
    }
    @FXML
    private void BackButtonClick(ActionEvent event) {
        LoadAdminMainMenu(event,adminUser);
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
