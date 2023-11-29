package com.lavalamp.requestHandler;

import com.lavalamp.JDBC.ContentDAO;
import com.lavalamp.JDBC.UserDAO;
import enums.UserRole;
import pojo.Content;
import pojo.User;
import request.*;
import response.*;


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
    public IResponse HandleRequest(BecomeCreatorRequest request){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        if(user.getUserRole() != UserRole.user){
            return new BecomeCreatorResponse(null,"Вы не можете стать создателем");
        }
        if(!userDAO.BecomeCreator(user.getId())){
            return new BecomeCreatorResponse(null,"Возникла ошибка");
        }
        user.setUserRole(UserRole.creator);
        return new BecomeCreatorResponse(user,"");
    }

    public IResponse HandleRequest(BuyContentRequest request){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        if(contentDAO == null){
            contentDAO = new ContentDAO();
        }
        if(user.getUserRole().equals(UserRole.admin)){
            return new BuyContentResponse(null,"Вы не можете покупать товары");
        }
        float contentPrice = contentDAO.GetContentPrice(((Content)request.GetPOJO()).getContentID());
        float userCurrencyRate = userDAO.GetCurrencyRate(user.getUserCurrencyID());
        float userWallet = user.getWallet() * userCurrencyRate;
        if(contentPrice < 0){
            return new BuyContentResponse(null,"Произошла ошибка");
        }
        if(userWallet < contentPrice){
            return new BuyContentResponse(null,"Не достаточно денег");
        }
        if(userDAO.BuyContent(user.getId(), ((Content) request.GetPOJO()).getContentID())){
            user.setWallet((userWallet - contentPrice)/userCurrencyRate);
            userDAO.ChangeMoney(user.getId(),user.getWallet());
            User user1 = new User();
            user1.setWallet(user.getWallet());
            return new BuyContentResponse(user1,"");
        }
        return new BuyContentResponse(null,"Возникла ошибка");
    }
    public IResponse HandleRequest(AddMoneyRequest addMoneyRequest){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        float value = user.getWallet() + (Float)addMoneyRequest.GetPOJO();
        userDAO.ChangeMoney(user.getId(),value);
        user.setWallet(value);
        User user1 = new User();
        user1.setWallet(value);
        return new AddMoneyResponse(user1,"");
    }
}
