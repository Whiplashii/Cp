package com.lavalamp.kpclient.modules;

import client.ServerClient;

public class UserLibraryModule {
    private ServerClient serverClient;

    public UserLibraryModule(){
        serverClient = ServerClient.ConnectToServer();
    }
}
