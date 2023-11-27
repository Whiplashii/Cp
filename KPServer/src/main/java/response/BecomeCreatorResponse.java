package response;

import pojo.User;

public class BecomeCreatorResponse implements IResponse {
    private User user;
    private String context;

    public BecomeCreatorResponse(User user,String context){
        this.user = user;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public User getUser() {
        return user;
    }
}
