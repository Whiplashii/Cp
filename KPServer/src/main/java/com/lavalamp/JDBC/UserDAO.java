package com.lavalamp.JDBC;

import com.lavalamp.hashing.HashGenerator;
import enums.UserRole;
import enums.sqlqueries.UserQueries;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private Connection connection;
    public boolean InsertNewUser(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.insertUser.toString());
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4,user.getUserSalt());
            preparedStatement.setFloat(5,user.getWallet());
            preparedStatement.setInt(6,user.getUserRole().getInt());
            preparedStatement.setInt(7,1);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User FindUserByLogin(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.findUserByLogin.toString());
            preparedStatement.setString(1,user.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String salt = resultSet.getString("usersalt");
                boolean isBanned = resultSet.getInt("isbanned") != 0;
                String password = resultSet.getString("userpassword");
                String hashedPassword = HashGenerator.GenerateHashedPassword(user.getPassword() + salt);
                if(password.equals(hashedPassword) && !isBanned){
                    user = SetUserFromResultSet(resultSet);
                }
                else {
                    user = null;
                }
            }
            else{
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
    public float GetCurrencyRate(int currencyID){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        float currencyRate = 1.0f;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.getCurrencyRate.toString());
            preparedStatement.setInt(1,currencyID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                currencyRate = resultSet.getFloat("currensyrate");
            }
        } catch (SQLException e) {
            return currencyRate;
        }
        return currencyID;
    }
    public boolean BecomeCreator(int userID){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.becomeCreator.toString());
            preparedStatement.setInt(1,UserRole.creator.getInt());
            preparedStatement.setInt(2,userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean BuyContent(int userID, int contentID){
        if (connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.buyContent.toString());
            preparedStatement.setInt(1,userID);
            preparedStatement.setInt(2,contentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
         return false;
        }
        return true;
    }
    public void ChangeMoney(int userID,float moneyValue){
        {
            if (connection == null){
                connection = JDBCConnector.GetConnection();
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.changeMoney.toString());
                preparedStatement.setFloat(1,moneyValue);
                preparedStatement.setInt(2,userID);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<User> GetUsers(int id){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.getUsers.toString());
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.add(SetUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }
    private User SetUserFromResultSet(ResultSet resultSet){
        User user = new User();
        try {
            boolean isBanned = resultSet.getInt("isbanned") != 0;
            user.setUserName(resultSet.getString("username"));
            user.setPassword(null);
            user.setUserSalt(null);
            user.setId(resultSet.getInt("userid"));
            user.setUserRole(UserRole.setInt(resultSet.getInt("userroleid")));
            user.setWallet(resultSet.getFloat("wallet"));
            user.setUserCurrencyID(resultSet.getInt("currensyid"));
            user.setBanned(isBanned);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean UpdateUser(User user){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.updatedUser.toString());
            preparedStatement.setInt(1, user.getUserRole().getInt());
            preparedStatement.setInt(2,user.getBanned()?1:0);
            preparedStatement.setInt(3,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void CloseConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
