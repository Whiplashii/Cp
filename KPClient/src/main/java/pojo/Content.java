package pojo;

import java.io.Serializable;
import java.sql.Date;

public class Content implements Serializable {
    private int contentID;
    private String contentName;
    private String contentDescription;
    private float contentPrice;
    private int contentTypeID;
    private int userID;
    private Date date;
    private int currencyID;
    private String imageURL;

    public Content() {
    }

    public int getContentID() {
        return contentID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public float getContentPrice() {
        return contentPrice;
    }

    public void setContentPrice(float contentPrice) {
        this.contentPrice = contentPrice;
    }

    public int getContentTypeID() {
        return contentTypeID;
    }

    public void setContentTypeID(int contentTypeID) {
        this.contentTypeID = contentTypeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
