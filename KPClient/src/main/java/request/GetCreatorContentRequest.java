package request;

import pojo.User;

import java.io.Serializable;

public class GetCreatorContentRequest implements IRequest {
    private User user;

    public GetCreatorContentRequest(User user) {
        this.user = user;
    }

    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
