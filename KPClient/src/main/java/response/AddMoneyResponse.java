package response;

import pojo.User;

public class AddMoneyResponse implements IResponse{
    private User user;
    private String context;
    public AddMoneyResponse(User user,String context) {
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public String getContext() {
        return context;
    }
}
