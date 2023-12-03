package request;

import pojo.User;

import java.io.Serializable;

public class UpdateUserRequest implements IRequest {
    private final User user;
    public UpdateUserRequest(User user) {
        this.user = user;
    }
    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
