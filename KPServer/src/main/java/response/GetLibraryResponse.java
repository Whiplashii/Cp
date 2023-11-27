package response;

import pojo.Content;

import java.util.ArrayList;

public class GetLibraryResponse implements IResponse{
    private ArrayList<Content> contentList;
    private String context;

    public GetLibraryResponse(ArrayList contentList,String context){
        this.contentList = contentList;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<Content> getContentList() {
        return contentList;
    }
}
