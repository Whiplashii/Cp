package response;

import pojo.Content;

import java.util.ArrayList;

public class GetCreatorContentResponse implements IResponse{
    private ArrayList<Content> contentList;
    private String context;

    public GetCreatorContentResponse(ArrayList<Content> contentList,String context){
        this.contentList = contentList;
        this.context = context;
    }

    public ArrayList<Content> getContentList() {
        return contentList;
    }

    public String getContext() {
        return context;
    }
}
