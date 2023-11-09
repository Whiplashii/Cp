package client;

import request.IRequest;
import response.IResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerClient {

    private static ServerClient serverClient;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private ServerClient() {
        try {
            Socket socket = new Socket("localhost", 6666);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.socket = socket;
        } catch (IOException ioe) {
            serverClient = null;
            ioe.printStackTrace();
        }
    }

    public static ServerClient ConnectToServer() {
        if (serverClient == null) {
            serverClient = new ServerClient();
        }
        return serverClient;
    }

    public void SendRequest(IRequest request) {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public IResponse GetResponse() {
        try {
            return (IResponse) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void CloseConnection() {
        try {
            serverClient = null;
            socket.close();
            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
