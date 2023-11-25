package com.lavalamp.JDBC;

import enums.sqlqueries.UserQueries;
import pojo.Content;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private User user;
    private Connection connection;

    public ArrayList<Content> GetContent() {
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            ArrayList<Content> contentList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from content");
            ResultSet resultSet = preparedStatement.executeQuery();
            /*while (resultSet.next()){
                contentList.add(GetContentFromResultSet(resultSet));
            }*/
            for(int i = 0;i < 10;i++){
                contentList.add(GetContentFromResultSet(resultSet));
            }
            return contentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Content GetContentFromResultSet(ResultSet resultSet){
        Content content = new Content();
        content.setContentName("From server with love");
        content.setContentPrice(3.0f);
        //todo get content from result set;
        return content;
    }

    public boolean InsertNewUser(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            //todo implement inserting User
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (`username`, `useremail`, `userpassword`, `userroleid`) VALUES (?, ?, ?, '1')");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean FindUserByLogin(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        boolean userExists = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.findUserByLogin.toString());
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            userExists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }
    public void CloseConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
