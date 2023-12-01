package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.ContentManagementModule;
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

public class ContentManagementController {

    @FXML
    private VBox itemsVBox;
    private final ContentManagementModule contentManagementModule;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Content> contentList = new ArrayList<>();
    private User user;

    public ContentManagementController() {
        contentManagementModule = new ContentManagementModule();
    }

    public void Initialize(User user) {
        this.user = user;
        contentList = contentManagementModule.GetCreatorsContent();
        SetContent();
    }

    public void SetContent() {
        for (var content : contentList) {
            Item item = new Item();
            item.setItemID(content.getContentID());
            item.SetTitle(content.getContentName());
            item.getClickableArea().setOnAction((event -> OnContentClick(event, item.getItemID())));
            item.SetPrice(content.getContentPrice() + "$");
            items.add(item);
            itemsVBox.getChildren().add(item);
        }
    }

    @FXML
    private void LogoutButtonClick(ActionEvent event) {
        var contentList = contentManagementModule.GetContent();
        LoadMainMenu(event, contentList);
    }

    @FXML
    private void AddButtonCLick(ActionEvent event) {
        LoadAddScreen(event, null);
    }

    private void OnContentClick(ActionEvent event, int id) {
        var content = GetContentById(id);
        LoadAddScreen(event, content);
    }

    private Content GetContentById(int id) {
        for (var content : contentList) {
            if (content.getContentID() == id) {
                return content;
            }
        }
        return null;
    }

    private void LoadMainMenu(ActionEvent event, ArrayList<Content> contentList) {
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

    private void LoadAddScreen(ActionEvent event, Content content) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("add-content-view.fxml"));
            Parent root = loader.load();

            AddContentScreenController mainMenuController = loader.getController();
            mainMenuController.Initialize(user, content);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
