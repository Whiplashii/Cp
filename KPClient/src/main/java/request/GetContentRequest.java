package request;

import pojo.User;

import java.io.Serializable;

public class GetContentRequest implements IRequest{

    private User user;

    public GetContentRequest(User user){
        this.user = user;
    }

    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
