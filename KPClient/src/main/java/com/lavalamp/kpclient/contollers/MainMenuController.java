package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.MainMenuModule;
import enums.UserRole;
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
import response.BuyContentResponse;
import response.GetLibraryResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenuController {
    private MainMenuModule mainMenuModule;
    @FXML
    private TextField searchField;
    @FXML
    private Button addContentButton;
    @FXML
    private Button becomeContentButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userWalletLabel;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Content> contentList = new ArrayList<>();
    private User user;
    @FXML
    private VBox objectsVBox;

    public MainMenuController() {
        mainMenuModule = new MainMenuModule();
    }

    public void Initialize(User user, ArrayList<Content> contentList) {
        this.user = Objects.requireNonNullElseGet(user, User::new);
        userNameLabel.setText(this.user.getUserName());
        userWalletLabel.setText(this.user.getWallet() + "$");
        this.contentList = contentList;
        if (user.getUserRole() == UserRole.creator) {
            addContentButton.setDisable(false);
            becomeContentButton.setDisable(true);
        }else {
            addContentButton.setDisable(true);
            becomeContentButton.setDisable(false);
        }
        SetContent();
    }

    public void SetContent() {
        for (var content : contentList) {
            Item item = new Item();
            item.setItemID(content.getContentID());
            item.SetTitle(content.getContentName());
            item.getClickableArea().setOnAction((event -> OnItemCLicked(event,item.getItemID())));
            item.SetPrice(content.getContentPrice() + "$");
            item.setImage(content.getImageURL());
            items.add(item);
            objectsVBox.getChildren().add(item);
        }
    }

    @FXML
    private void OnItemCLicked(ActionEvent event,int itemID) {
        BuyContentResponse response =  mainMenuModule.BuyContent(GetContentById(itemID));
        if(response.getUser() == null){
            System.out.println(response.getContext());
            return;
        }
        user.setWallet(response.getUser().getWallet());
        userWalletLabel.setText(user.getWallet() + "$");
    }

    private Content GetContentById(int id){
        for(var content:contentList){
            if(content.getContentID() == id){
                return content;
            }
        }
        return null;
    }

    @FXML
    private void LogoutButtonClick(ActionEvent event) {
        mainMenuModule.LogOut();
        LoadLoginScene(event, "login-view.fxml");
    }

    @FXML
    public void SearchFieldTyped() {
        objectsVBox.getChildren().clear();
        for (var item : items) {
            if (item.GetTitle().contains(searchField.getText()))
                objectsVBox.getChildren().add(item);
        }
    }

    @FXML
    public void LibraryButtonClick(ActionEvent event){
        GetLibraryResponse response = mainMenuModule.GetLibrary();
        if(response.getContentList() == null){
            System.out.println(response.getContext());
            return;
        }
        LoadLibraryScene(event,response.getContentList());
    }

    @FXML
    public void BecomeCreatorButtonClick(ActionEvent event){
     var response= mainMenuModule.BecomeCreator();
     if(response.getUser() == null){
         System.out.println(response.getContext());
         return;
     }
     user = response.getUser();
        addContentButton.setDisable(false);
        becomeContentButton.setDisable(true);
    }

    @FXML
    private void AddButtonCLick(ActionEvent event){
        LoadContentManagementScene(event);
    }

    @FXML
    public void AddMoneyButtonClick(ActionEvent event){
        LoadAddCurrency(event);
    }
    private void LoadAddCurrency(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("add-currency-view.fxml"));
            Parent root = loader.load();

            AddCurrencyController Controller = loader.getController();
            Controller.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadLoginScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource(sceneName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void LoadLibraryScene(ActionEvent event, ArrayList<Content> contentList){
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("user-library.fxml"));
            Parent root = loader.load();

            UserLibraryController Controller = loader.getController();
            Controller.Initialize(user, contentList);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadContentManagementScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("content-management-view.fxml"));
            Parent root = loader.load();

            ContentManagementController Controller = loader.getController();
            Controller.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
