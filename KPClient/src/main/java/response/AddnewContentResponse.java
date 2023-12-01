package response;

public class AddnewContentResponse implements IResponse{
    private Boolean isAdded;
    private String context;

    public AddnewContentResponse(Boolean isAdded,String context){
        this.isAdded = isAdded;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public Boolean getAdded() {
        return isAdded;
    }
}
