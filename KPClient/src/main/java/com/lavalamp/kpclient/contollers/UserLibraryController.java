package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.UserLibraryModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;

import java.io.IOException;
import java.util.ArrayList;

public class UserLibraryController {
    private UserLibraryModule userLibraryModule;
    private final ArrayList<Item> items = new ArrayList<>();
    private User user;

    @FXML
    private VBox itemsVBox;
    public UserLibraryController(){
        userLibraryModule  = new UserLibraryModule();
    }
    public void Initialize(User user, ArrayList<Content> contentList){
        this.user = user;

        SetContent(contentList);
    }
    private void SetContent(ArrayList<Content> contentList){
        for(var content:contentList) {
            Item item = new Item();
            item.setItemID(content.getContentID());
            item.SetTitle(content.getContentName());
            item.setPriceVisible(false);
            item.setImage(content.getImageURL());
            items.add(item);
            itemsVBox.getChildren().add(item);
        }
    }

    @FXML
    private void BackButtonClick(ActionEvent event) {
        ArrayList<Content> contentList =  userLibraryModule.GetContent();
        LoadMainMenu(event,contentList);
    }

    private void LoadMainMenu(ActionEvent event,ArrayList<Content> contentList){
        try {
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

    private void ClearVbox(){
        itemsVBox.getChildren().clear();
    }
}
