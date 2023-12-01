package response;

public class UpdateContentResponse implements IResponse {
    private Boolean isUpdated;
    private String context;

    public UpdateContentResponse(Boolean isUpdated, String context){
        this.isUpdated = isUpdated;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public Boolean getUpdated() {
        return isUpdated;
    }
}
