package request;

import pojo.User;

import java.io.Serializable;

public class LoginRequest implements IRequest{
    private User user;

    public LoginRequest(User user){
        this.user = user;
    }

    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
