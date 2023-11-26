package com.lavalamp.requestHandler;

import com.lavalamp.JDBC.UserDAO;
import pojo.User;
import request.GetContentRequest;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegistrationRequest;
import response.*;

public class RequestHandler {
    private UserDAO userDAO;
    private User user = null;

    public IResponse HandleRequest(LoginRequest loginRequest) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        user = userDAO.FindUserByLogin((User)loginRequest.GetPOJO());
        User responseUser = user;
        String context = "";
        if(user != null){
            responseUser.setId(null);
            responseUser.setWallet(user.getWallet() * userDAO.GetCurrencyRate(user.getUserCurrencyID()));
            System.err.println(user.getId());
        }
        else{
            context = "Неверный логин или пароль";
        }
        LoginResponse loginResponse = new LoginResponse(responseUser,context);
        return loginResponse;
    }

    public IResponse HandleRequest(RegistrationRequest registrationRequest) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.accepted = userDAO.InsertNewUser((User) registrationRequest.GetPOJO());
        return registrationResponse;
    }

    public IResponse HandleRequest(LogoutRequest logoutRequest) {
        user = null;
        userDAO.CloseConnection();
        userDAO = null;
        System.err.println("User logged out");
        return new LogoutResponse();
    }
    public IResponse HandleRequest(GetContentRequest getContentRequest){
        GetContentResponse getContentResponse = new GetContentResponse();
        getContentResponse.contentList = userDAO.GetContent();
        return getContentResponse;
    }
}
