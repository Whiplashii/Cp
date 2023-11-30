package com.lavalamp.kpclient.modules;

import client.ServerClient;

public class AddContentScreenModule {
    private final ServerClient serverClient;
    public AddContentScreenModule(){
        serverClient = ServerClient.ConnectToServer();
    }
}
