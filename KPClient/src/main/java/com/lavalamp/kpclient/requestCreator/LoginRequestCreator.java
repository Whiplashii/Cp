package com.lavalamp.kpclient.requestCreator;

import pojo.User;
import request.IRequest;
import request.LoginRequest;

import java.io.Serializable;

public class LoginRequestCreator implements IRequestCreator{

    @Override
    public IRequest CreateRequest(Serializable data) {
        User user  = (User) data;
        return new LoginRequest(user);
    }
}
