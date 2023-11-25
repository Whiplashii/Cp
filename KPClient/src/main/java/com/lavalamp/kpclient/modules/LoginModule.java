package com.lavalamp.kpclient.modules;

import client.ServerClient;
import com.lavalamp.kpclient.LoginController;
import com.lavalamp.kpclient.hashing.HashGenerator;
import pojo.User;
import request.LoginRequest;
import response.LoginResponse;

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
}
