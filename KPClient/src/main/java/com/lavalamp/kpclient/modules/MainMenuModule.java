package com.lavalamp.kpclient.modules;

import client.ServerClient;
import request.GetLibraryRequest;
import request.LogoutRequest;
import response.GetLibraryResponse;

public class MainMenuModule {

    private final ServerClient serverClient;
    float x;
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
}
