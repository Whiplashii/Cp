package request;

import pojo.User;

import java.io.Serializable;

public class RegistrationRequest implements IRequest{
    private User user;
    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
