package com.lavalamp.JDBC;

import enums.sqlqueries.ContentQueries;
import pojo.Content;
import pojo.User;

import java.sql.*;
import java.util.ArrayList;

public class ContentDAO {
    private Connection connection;

    public ArrayList<Content> GetContent() {
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            ArrayList<Content> contentList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.getContent.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                contentList.add(GetContentFromResultSet(resultSet));
            }
            return contentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Content> GetCreatorContent(int id){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            ArrayList<Content> contentList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.getCreatorContent.toString());
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                contentList.add(GetContentFromResultSet(resultSet));
            }
            return contentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Content GetContentFromResultSet(ResultSet resultSet)throws SQLException{
        Content content = new Content();
        content.setContentID(resultSet.getInt("contentid"));
        content.setContentName(resultSet.getString("contentname"));
        content.setContentDescription("contentdescription");
        content.setContentPrice(resultSet.getFloat("contentprice"));
        content.setContentTypeID(resultSet.getInt("contenttypeid"));
        content.setDate(resultSet.getDate("dateadd"));
        content.setUserID(resultSet.getInt("userid"));
        content.setImageURL(resultSet.getString("url"));
        return content;
    }

    public boolean SetContent(Content content, User user){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            Date date = new Date(System.currentTimeMillis());
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.insert.toString());
            preparedStatement.setString(1,content.getContentName());
            preparedStatement.setString(2,content.getContentDescription());
            preparedStatement.setFloat(3,content.getContentPrice());
            preparedStatement.setInt(4,content.getContentTypeID());
            preparedStatement.setInt(5,user.getId());
            preparedStatement.setInt(6,user.getUserCurrencyID());
            preparedStatement.setDate(7, Date.valueOf(date.toLocalDate()));
            preparedStatement.setString(8,content.getImageURL());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void RemoveContent(Content content){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.remove.toString());
            preparedStatement.setInt(1,content.getCurrencyID());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public boolean UpdateContent(Content content){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.updateContent.toString());
            preparedStatement.setString(1,content.getContentName());
            preparedStatement.setString(2,content.getContentDescription());
            preparedStatement.setFloat(3,content.getContentPrice());
            preparedStatement.setString(4,content.getImageURL());
            preparedStatement.setInt(5,content.getContentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void UpdateDescription(Content content){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.updateDescription.toString());
            preparedStatement.setString(1,content.getContentDescription());
            preparedStatement.setInt(2,content.getContentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdatePrice(Content content){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.updatePrice.toString());
            preparedStatement.setFloat(1,content.getContentPrice());
            preparedStatement.setInt(2,content.getContentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Content> GetUsersLibrary(int userID){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<Content> contentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.getUserLibrary.toString());
            preparedStatement.setInt(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                contentList.add(GetContentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return contentList;
    }
    public float GetContentPrice(int contentID){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        float price = -1.0f;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.getContentPrice.toString());
            preparedStatement.setInt(1,contentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                price = resultSet.getFloat("contentprice") * resultSet.getFloat("currencyrate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return price;
    }
}
