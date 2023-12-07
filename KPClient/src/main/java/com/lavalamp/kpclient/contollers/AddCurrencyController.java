package com.lavalamp.kpclient.contollers;

import com.lavalamp.kpclient.Client;
import com.lavalamp.kpclient.DialogScreen;
import com.lavalamp.kpclient.contollers.MainMenuController;
import com.lavalamp.kpclient.modules.AddCurrencyModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Content;
import pojo.User;
import response.AddMoneyResponse;

import java.io.IOException;
import java.util.ArrayList;

public class AddCurrencyController {
    @FXML
    private Button logoutButton;
    @FXML
    private Button addMoneyButton;
    @FXML
    private TextField moneyValueTextField;
    private final AddCurrencyModule addCurrencyModule;
    private User user;

    public AddCurrencyController() {
        addCurrencyModule = new AddCurrencyModule();
    }

    public void Initialize(User user){
        this.user = user;
    }

    @FXML
    private void LogoutButtonClick(ActionEvent event){
        ArrayList<Content> contentList = addCurrencyModule.GetContent();
        LoadMainMenu(event,contentList);
    }

    @FXML
    private void AddMoneyButtonClick(ActionEvent event) {
        float value = Float.parseFloat(moneyValueTextField.getText());
        if (value < 0) {
            return;
        }
        AddMoneyResponse response = addCurrencyModule.AddMoney(value);
        if (response.getUser() == null) {
            DialogScreen.ShowDialog(Alert.AlertType.ERROR,"Error",null,response.getContext());
            return;
        }
        user.setWallet(response.getUser().getWallet());
        var contentList = addCurrencyModule.GetContent();
        LoadMainMenu(event, contentList);
    }

    @FXML
    private void TextFieldChanged(){
        if(!moneyValueTextField.getText().matches("\\d*")) {
            moneyValueTextField.setText(moneyValueTextField.getText().replaceAll("[^\\d*]",""));
        }
    }

    private boolean CheckTextField(){
        return false;
    }

    private void LoadMainMenu(ActionEvent event, ArrayList<Content> contentList){
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
}
