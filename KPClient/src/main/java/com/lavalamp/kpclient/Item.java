package com.lavalamp.kpclient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Item extends Pane {
    @FXML
    private ImageView itemImage;
    @FXML
    private TextField itemTitle;
    @FXML
    private TextField itemPrice;

    public Item() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "content-item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String GetTitle() {
        return itemTitle.getText();
    }

    public String GetPrice() {
        return itemPrice.getText();
    }

    public void SetTitle(String title) {
        itemTitle.setText(title);
    }

    public void SetPrice(String price) {
        itemPrice.setText(price);
    }


}
