package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import pojo.User;
import request.GetContentRequest;
import request.GetCreatorContentRequest;
import response.GetContentResponse;
import response.GetCreatorContentResponse;

import java.util.ArrayList;

public class ContentManagementModule {
    private final ServerClient serverClient;

    public ContentManagementModule() {
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<Content> GetContent(){
        GetContentRequest getContentRequest = new GetContentRequest(new User());
        serverClient.SendRequest(getContentRequest);
        return ((GetContentResponse) serverClient.GetResponse()).contentList;
    }
    public ArrayList<Content>GetCreatorsContent(User user){
        serverClient.SendRequest(new GetCreatorContentRequest(user.getId()));
        return ((GetCreatorContentResponse)serverClient.GetResponse()).getContentList();
    }
}
