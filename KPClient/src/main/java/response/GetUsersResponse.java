package response;

import pojo.User;

import java.util.ArrayList;

public class GetUsersResponse implements IResponse{
    private ArrayList<User> users;
    private String context;

    public GetUsersResponse(ArrayList<User> users, String context){
        this.users = users;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
