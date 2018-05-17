package besteburhan.currency;

import com.google.gson.JsonObject;

public class Currency {
    Boolean success;
    String date;
    JsonObject rates;

    public Boolean getSuccess() {
        return success;
    }

    public String getDate() {
        return date;
    }

    public JsonObject getRates() {
        return rates;
    }

    public double getRate(String code) {
        return rates.has(code) ? rates.get(code).getAsDouble() : 0;
    }
}
