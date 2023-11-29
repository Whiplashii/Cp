package response;

import pojo.User;

public class BuyContentResponse implements IResponse{
    private User user;
    private String context;

    public BuyContentResponse(User user, String context){
        this.user = user;
        this.context = context;
    }

    public User getUser() {
        return user;
    }
    public String getContext() {
        return context;
    }

}
