package com.lavalamp.kpclient.modules;

import client.ServerClient;
import pojo.Content;
import request.BecomeCreatorRequest;
import request.BuyContentRequest;
import request.GetLibraryRequest;
import request.LogoutRequest;
import response.BecomeCreatorResponse;
import response.BuyContentResponse;
import response.GetLibraryResponse;

public class MainMenuModule {

    private final ServerClient serverClient;
    public MainMenuModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public void LogOut(){
        serverClient.SendRequest(new LogoutRequest());
        serverClient.GetResponse();
    }
    public GetLibraryResponse GetLibrary(){
        serverClient.SendRequest(new GetLibraryRequest());
        return (GetLibraryResponse) serverClient.GetResponse();
    }
    public BecomeCreatorResponse BecomeCreator(){
        serverClient.SendRequest(new BecomeCreatorRequest());
        return (BecomeCreatorResponse) serverClient.GetResponse();
    }

    public BuyContentResponse BuyContent(Content content) {
        serverClient.SendRequest(new BuyContentRequest(content));
        return (BuyContentResponse) serverClient.GetResponse();
    }
}
