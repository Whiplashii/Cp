package request;

import pojo.Content;

import java.io.Serializable;

public class BuyContentRequest implements IRequest{
    private Content content;
    @Override
    public Serializable GetPOJO() {
        return content;
    }
    public BuyContentRequest(Content content){
        this.content = content;
    }
}
