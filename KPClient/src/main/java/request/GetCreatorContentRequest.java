package request;

import java.io.Serializable;

public class GetCreatorContentRequest implements IRequest {
    private Integer id;

    public GetCreatorContentRequest(Integer id) {
        this.id = id;
    }

    @Override
    public Serializable GetPOJO() {
        return id;
    }
}
