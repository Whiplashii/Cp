package com.lavalamp.kpclient.modules;

import client.ServerClient;
import com.lavalamp.kpclient.LoginController;
import com.lavalamp.kpclient.hashing.HashGenerator;
import pojo.Content;
import pojo.User;
import request.GetContentRequest;
import request.LoginRequest;
import response.GetContentResponse;
import response.LoginResponse;

import java.util.ArrayList;

public class LoginModule {
    private final LoginController loginController;
    private final ServerClient serverClient;
    public LoginModule(LoginController loginController){
        this.loginController = loginController;
        serverClient = ServerClient.ConnectToServer();
    }

    public LoginResponse LogIn(User user){
        user.setPassword(HashGenerator.GenerateHashedPassword(user.getPassword()));
        serverClient.SendRequest(new LoginRequest(user));
        return (LoginResponse) serverClient.GetResponse();
    }
    public ArrayList<Content> GetContent(){
        GetContentRequest getContentRequest = new GetContentRequest(new User());
        serverClient.SendRequest(getContentRequest);
        return ((GetContentResponse) serverClient.GetResponse()).contentList;
    }
}
