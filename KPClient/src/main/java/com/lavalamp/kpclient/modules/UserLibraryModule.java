package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import pojo.User;
import request.GetContentRequest;
import response.GetContentResponse;

import java.util.ArrayList;

public class UserLibraryModule {
    private ServerClient serverClient;

    public UserLibraryModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public void GetBackToMain() {
    }

    public ArrayList<Content> GetContent(){
        GetContentRequest getContentRequest = new GetContentRequest(new User());
        serverClient.SendRequest(getContentRequest);
        return ((GetContentResponse) serverClient.GetResponse()).contentList;
    }
}
