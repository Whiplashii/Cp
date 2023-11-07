package request;

import pojo.User;
import request.IRequest;

import java.io.Serializable;

public class RegistrationRequest implements IRequest {
    private final User user;

    public RegistrationRequest(User user) {
        this.user = user;
    }

    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
