package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.modules.AddContentScreenModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;

import java.io.IOException;

public class AddContentScreenController {
    @FXML
    private Button backButton;
    private AddContentScreenModule addContentScreenModule;
    private User user;
    private Content content;
    public AddContentScreenController(){
        addContentScreenModule = new AddContentScreenModule();
    }
    public void Initialize(User user, Content content){
        this.user = user;
        if(content!=null){
            this.content = content;
            SetColumns();
        }
    }
    public void SetColumns(){
    }

    @FXML
    private void BackButtonClick(ActionEvent event) {
        LoadContentManagementScene(event);
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
