package com.lavalamp.requestHandler;

import com.lavalamp.JDBC.ContentDAO;
import com.lavalamp.JDBC.UserDAO;
import enums.UserRole;
import pojo.User;
import request.*;
import response.*;

import java.util.ArrayList;

public class RequestHandler {
    private UserDAO userDAO;
    private ContentDAO contentDAO;
    private User user = null;

    public IResponse HandleRequest(LoginRequest request) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        user = userDAO.FindUserByLogin((User)request.GetPOJO());
        User responseUser = user;
        String context = "";
        if(user != null){
            responseUser.setWallet(user.getWallet() * userDAO.GetCurrencyRate(user.getUserCurrencyID()));
        }
        else{
            context = "Неверный логин или пароль";
        }
        LoginResponse loginResponse = new LoginResponse(responseUser,context);
        return loginResponse;
    }

    public IResponse HandleRequest(RegistrationRequest request) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.accepted = userDAO.InsertNewUser((User) request.GetPOJO());
        return registrationResponse;
    }
    public IResponse HandleRequest(LogoutRequest request) {
        user = null;
        userDAO.CloseConnection();
        userDAO = null;
        System.err.println("User logged out");
        return new LogoutResponse();
    }
    public IResponse HandleRequest(GetContentRequest getContentRequest){
        if(contentDAO == null){
            contentDAO = new ContentDAO();
        }
        if(user == null){
            GetContentResponse getContentResponse = new GetContentResponse();
            getContentResponse.contentList = null;
            return getContentResponse;
        }
        GetContentResponse getContentResponse = new GetContentResponse();
        getContentResponse.contentList = contentDAO.GetContent();
        return getContentResponse;
    }
    public IResponse HandleRequest(GetLibraryRequest request){
        if(contentDAO == null){
            contentDAO = new ContentDAO();
        }
        if(user.getUserRole().equals(UserRole.admin))
        {
            return new GetLibraryResponse(null,"У администратора не может быть библиотеки");
        }
        var contentList = contentDAO.GetUsersLibrary(user.getId());
        String context = "";
        if(contentList == null)
        {
            context = "Произошла ошибка";
        }
        return new GetLibraryResponse(contentList,context);
    }
}
