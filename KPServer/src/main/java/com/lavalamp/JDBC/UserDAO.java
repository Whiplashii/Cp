package com.lavalamp.JDBC;

import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public void InsertNewUser(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            //todo implement inserting User
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean CheckUser(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            //todo implement checking user by username and password
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from user where UserName = ? and UserPassword = ?");
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
}
