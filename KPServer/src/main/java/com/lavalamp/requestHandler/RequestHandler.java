package com.lavalamp.requestHandler;

import com.lavalamp.JDBC.UserDAO;
import pojo.User;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegistrationRequest;
import response.IResponse;
import response.LoginResponse;
import response.LogoutResponse;
import response.RegistrationResponse;

public class RequestHandler {
    private UserDAO userDAO;
    private User user;

    public IResponse HandleRequest(LoginRequest loginRequest) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.accessGranted = userDAO.CheckUserToLogin((User) loginRequest.GetPOJO());
        if (loginResponse.accessGranted) {
            user = (User) loginRequest.GetPOJO();
        }
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
}
