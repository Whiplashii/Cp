package com.lavalamp.requestHandler;

import com.lavalamp.JDBC.UserDAO;
import pojo.User;
import request.LoginRequest;
import request.RegistrationRequest;
import response.IResponse;
import response.LoginResponse;

public class RequestHandler {

    public IResponse HandleRequest(LoginRequest loginRequest){
        UserDAO userDAO = new UserDAO();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.accessGranted = userDAO.CheckUser((User) loginRequest.GetPOJO());
        return loginResponse;
    }
    public IResponse HandleRequest(RegistrationRequest registrationRequest){
        return null;
    }
}
