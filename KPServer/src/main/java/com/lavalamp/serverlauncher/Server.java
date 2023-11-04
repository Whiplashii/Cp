package com.lavalamp.serverlauncher;

import pojo.User;
import request.IRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.Calendar;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    Socket socket;
    Server(ServerSocket serverSocket){
        try {
            this.serverSocket = serverSocket;
            socket = serverSocket.accept();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Client connected");
        while (!socket.isClosed()) {
            try {
                IRequest request = GetRequest();
                User user = (User) request.GetPOJO();
                System.out.println(user.getUserName() + "\n" + user.getPassword());
                //SendResponse();
            } catch (IOException ioe) {
                System.err.println("Client Disconnected");
                CloseConnection();
            }catch (ClassNotFoundException cle){
                cle.printStackTrace();
                System.err.println("invalid Request");
            }
        }
    }

    private IRequest GetRequest()throws IOException,ClassNotFoundException{
        return (IRequest)objectInputStream.readObject();
    }
    private void SendResponse()throws IOException{

    }

    private void CloseConnection() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
