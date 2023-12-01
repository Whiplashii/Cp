package request;

import pojo.Content;

import java.io.Serializable;

public class UpdateContentRequest implements IRequest{
    private Content content;

    @Override
    public Serializable GetPOJO() {
        return content;
    }
    public UpdateContentRequest(Content content){
        this.content = content;
    }
}
