package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import pojo.User;
import request.GetContentRequest;
import request.GetUsersRequest;
import request.LogoutRequest;
import response.GetContentResponse;
import response.GetUsersResponse;
import response.LogoutResponse;

import java.util.ArrayList;

public class AdminMainMenuModule {
    private final ServerClient serverClient;
    public AdminMainMenuModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public LogoutResponse LogOut(){
        serverClient.SendRequest(new LogoutRequest());
        return (LogoutResponse) serverClient.GetResponse();
    }
    public ArrayList<Content> GetContent(User user){
        serverClient.SendRequest(new GetContentRequest(user));
        return ((GetContentResponse)serverClient.GetResponse()).contentList;
    }
    public ArrayList<User> GetUsers(){
        serverClient.SendRequest(new GetUsersRequest());
        return ((GetUsersResponse)serverClient.GetResponse()).getUsers();
    }
}
