package client;

import request.IRequest;
import response.IResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerClient {
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public void ConnectToServer() {
        try{
            Socket socket = new Socket("localhost",6666);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.socket = socket;
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void SendRequest(IRequest request) {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public IResponse GetResponse(){
        try {
            return (IResponse) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void CloseConnection(){
        try {
        socket.close();
        objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
