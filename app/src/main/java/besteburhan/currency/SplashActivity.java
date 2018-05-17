package besteburhan.currency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FixerApi.BASE_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FixerApi api = retrofit.create(FixerApi.class);
        Call<Currency> call = api.getLatest();
        call.enqueue(new Callback<Currency>() {
                         @Override
                         public void onResponse(Call<Currency> call, Response<Currency> response) {
                             Currency curr = response.body();
                             Boolean succ =curr.getSuccess();
                             if(succ) {
                                 Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                 startActivity(intent);
                                 finish();
                             }
                             else{
                                 Toast.makeText(getApplicationContext(),"API IS NOT ACCESSIBLE!",Toast.LENGTH_LONG).show();
                                 finish();
                             }
                         }

                         @Override
                         public void onFailure(Call<Currency> call, Throwable t) {

                         }
                     });


    }
}
