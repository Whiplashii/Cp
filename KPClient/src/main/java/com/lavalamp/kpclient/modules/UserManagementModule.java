package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import pojo.User;
import request.GetCreatorContentRequest;
import request.UpdateUserRequest;
import response.GetCreatorContentResponse;
import response.UpdateUserResponse;

import java.util.ArrayList;

public class UserManagementModule {
    private final ServerClient serverClient;

    public UserManagementModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<Content> GetContentByID(User user){
        serverClient.SendRequest(new GetCreatorContentRequest(user));
        return ((GetCreatorContentResponse)serverClient.GetResponse()).getContentList();
    }
    public UpdateUserResponse UpdateUser(User user){
        serverClient.SendRequest(new UpdateUserRequest(user));
        return ((UpdateUserResponse) serverClient.GetResponse());
    }
}
