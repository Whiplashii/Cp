package com.lavalamp.serverlauncher;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerLauncher {

    public void WaitClients(){
        try(ServerSocket serverSocket = new ServerSocket(6666)){
            System.out.println("ServerStarted...\n" + "Waiting for clients...");
            while (true){
                Thread thread = new Thread(new Server(serverSocket));
                thread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
