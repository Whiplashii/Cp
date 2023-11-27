package com.lavalamp.serverlauncher;

import com.lavalamp.requestHandler.RequestHandler;
import request.*;
import response.IResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private RequestHandler requestHandler;
    private Socket socket;
    Server(ServerSocket serverSocket){
        try {
            this.serverSocket = serverSocket;
            socket = serverSocket.accept();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            requestHandler = new RequestHandler();
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
                SendResponse(CreateResponse(request));
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
    private void SendResponse(IResponse response)throws IOException{
        objectOutputStream.writeObject(response);
    }
    private IResponse CreateResponse(IRequest request) {
        if (request.getClass() == LoginRequest.class) {
            return requestHandler.HandleRequest((LoginRequest) request);
        }
        if (request.getClass() == RegistrationRequest.class) {
            return requestHandler.HandleRequest((RegistrationRequest) request);
        }
        if (request.getClass() == LogoutRequest.class) {
            return requestHandler.HandleRequest((LogoutRequest) request);
        }
        if(request.getClass() == GetContentRequest.class) {
            return requestHandler.HandleRequest((GetContentRequest) request);
        }
        if(request.getClass() == GetLibraryRequest.class) {
            return requestHandler.HandleRequest((GetLibraryRequest) request);
        }
        return null;
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
