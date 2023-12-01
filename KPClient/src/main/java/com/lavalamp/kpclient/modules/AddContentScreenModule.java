package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import pojo.User;
import request.AddNewContentRequest;
import request.UpdateContentRequest;
import response.AddnewContentResponse;
import response.IResponse;
import response.UpdateContentResponse;

public class AddContentScreenModule {
    private final ServerClient serverClient;
    public AddContentScreenModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public AddnewContentResponse AddNewContent(Content content){
        serverClient.SendRequest(new AddNewContentRequest(content));
        return (AddnewContentResponse) serverClient.GetResponse();
    }
    public UpdateContentResponse AddContentChanges(Content content){
        serverClient.SendRequest(new UpdateContentRequest(content));
        return (UpdateContentResponse) serverClient.GetResponse();
    }
}
