package pojo;

import java.io.Serializable;
import java.util.Date;

public class Content implements Serializable {
    private int contentID;
    private String contentName;
    private String contentDescription;
    private float contentPrice;
    private int contentTypeID;
    private int userID;
    private Date date;

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
}
