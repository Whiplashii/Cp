package request;

import java.io.Serializable;

public class AddMoneyRequest implements IRequest {
    private Float moneyValue;
    public AddMoneyRequest(float value) {
        this.moneyValue = value;
    }

    @Override
    public Serializable GetPOJO() {
        return moneyValue;
    }
}
