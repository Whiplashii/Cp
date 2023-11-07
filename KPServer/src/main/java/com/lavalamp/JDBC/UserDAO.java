package com.lavalamp.JDBC;

import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

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
            int affectedRow = preparedStatement.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean CheckUserToLogin(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            //todo implement checking user by username and password
            PreparedStatement preparedStatement = connection.prepareStatement("Select username,userpassword from user where UserName = ? and UserPassword = ?");
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String username = resultSet.getString("UserName");
                String userPassword = resultSet.getString("UserPassword");
                if(user.getUserName().equals(username) && user.getPassword().equals(userPassword))return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void CloseConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
