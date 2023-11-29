package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import pojo.User;
import request.AddMoneyRequest;
import request.GetContentRequest;
import response.AddMoneyResponse;
import response.GetContentResponse;

import java.util.ArrayList;

public class AddCurrencyModule {
    private final ServerClient serverClient;
    public AddCurrencyModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public AddMoneyResponse AddMoney(float user){
        serverClient.SendRequest(new AddMoneyRequest(user));
        return (AddMoneyResponse) serverClient.GetResponse();
    }

    public ArrayList<Content> GetContent(){
        GetContentRequest getContentRequest = new GetContentRequest(new User());
        serverClient.SendRequest(getContentRequest);
        return ((GetContentResponse) serverClient.GetResponse()).contentList;
    }
}
