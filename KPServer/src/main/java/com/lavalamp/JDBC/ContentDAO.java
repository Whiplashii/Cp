package com.lavalamp.JDBC;

import enums.UserRole;
import enums.sqlqueries.ContentQueries;
import pojo.Content;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Content GetContentFromResultSet(ResultSet resultSet)throws SQLException{
        Content content = new Content();
        content.setContentName(resultSet.getString("contentname"));
        content.setContentDescription("contentdescription");
        content.setContentPrice(resultSet.getFloat("contentprice"));
        content.setContentTypeID(resultSet.getInt("contenttypeid"));
        content.setDate(resultSet.getDate("dateadd"));
        content.setUserID(resultSet.getInt("userid"));
        return content;
    }

    public void SetContent(Content content, User user){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            if(!user.getUserRole().equals(UserRole.creator)){
                return;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.insert.toString());
            preparedStatement.setString(1,content.getContentName());
            preparedStatement.setString(2,content.getContentDescription());
            preparedStatement.setFloat(3,content.getContentPrice());
            preparedStatement.setInt(4,content.getContentTypeID());
            preparedStatement.setInt(5,content.getUserID());
            preparedStatement.setInt(6,user.getUserCurrencyID());
            preparedStatement.setDate(7,content.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void UpdateName(Content content){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ContentQueries.updateName.toString());
            preparedStatement.setString(1,content.getContentName());
            preparedStatement.setInt(2,content.getContentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
