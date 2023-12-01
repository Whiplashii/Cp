package request;

import pojo.Content;

import java.io.Serializable;

public class AddNewContentRequest implements IRequest{
    private Content content;

    @Override
    public Serializable GetPOJO() {
        return content;
    }

    public AddNewContentRequest(Content content){
        this.content = content;
    }
}
