package response;

import pojo.User;

public class LoginResponse implements IResponse{
    public User user;
    public Boolean accessGranted;
}
