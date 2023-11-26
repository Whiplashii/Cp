package response;

import pojo.User;

import javax.naming.Context;

public class LoginResponse implements IResponse{
    private User user;
    private String context;

    public User getUser() {
        return user;
    }
    public String getContext(){
        return context;
    }
    public LoginResponse(User user, String context){
        this.user = user;
        this.context = context;
    }
}
