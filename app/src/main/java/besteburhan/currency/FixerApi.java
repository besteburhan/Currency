package besteburhan.currency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FixerApi {
    String BASE_Url= "http://data.fixer.io/api/";
    @GET("latest?access_key=e06fa38c728e1809b59a91124f2e8cb5&symbols=USD,TRY,CZK&format=1")
    Call<Currency> getLatest();
}
//http://data.fixer.io/api/latest?access_key=e06fa38c728e1809b59a91124f2e8cb5
