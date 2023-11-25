package com.lavalamp.kpclient.modules;

import client.ServerClient;
import com.lavalamp.kpclient.RegistrationController;
import com.lavalamp.kpclient.hashing.HashGenerator;
import pojo.User;
import request.RegistrationRequest;
import response.RegistrationResponse;

public class RegistrationModule {
    private final RegistrationController registrationController;
    private final ServerClient serverClient;
    public RegistrationModule(RegistrationController registrationController){
        this.registrationController = registrationController;
        serverClient = ServerClient.ConnectToServer();
    }

    public RegistrationResponse SignIn(User user){
        user.setUserSalt(HashGenerator.GenerateSalt());
        String password = HashGenerator.GenerateHashedPassword(user.getPassword());
        user.setPassword(HashGenerator.GenerateHashedPassword(password + user.getUserSalt()));
        serverClient.SendRequest(new RegistrationRequest(user));
        return (RegistrationResponse) serverClient.GetResponse();
    }
}
