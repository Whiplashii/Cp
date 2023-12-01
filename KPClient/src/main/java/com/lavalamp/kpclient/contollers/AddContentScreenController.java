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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;

import java.io.IOException;

public class AddContentScreenController {

    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private MenuButton contentTypeMenuButton;
    @FXML
    private TextField priceTextField;
    @FXML
    private Button backButton;
    private AddContentScreenModule addContentScreenModule;
    private User user;
    private Content content = null;
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
        titleTextField.setText(content.getContentName());
        descriptionTextArea.setText(content.getContentDescription());
        priceTextField.setText(String.valueOf(content.getContentPrice()));
    }
    public void GetColumns(){
        content.setContentName(titleTextField.getText());
        content.setContentDescription(descriptionTextArea.getText());
        content.setContentTypeID(1);
        content.setContentPrice(Float.parseFloat(priceTextField.getText()));
    }

    @FXML
    private void OnBackButtonClick(ActionEvent event) {
        LoadContentManagementScene(event);
    }
    @FXML
    private void OnSubmitButtonCLick(ActionEvent event) {
        boolean isNew = false;
        if (content == null) {
            isNew = true;
            content = new Content();
        }
        GetColumns();
        boolean isChanged;
        String context;
        if (isNew) {
            var response = addContentScreenModule.AddNewContent(content);
            isChanged = response.getAdded();
            context = response.getContext();
        } else {
            var response = addContentScreenModule.AddContentChanges(content);
            isChanged = response.getUpdated();
            context = response.getContext();
        }
        if (!isChanged) {
            System.out.println(context);
            return;
        }
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
