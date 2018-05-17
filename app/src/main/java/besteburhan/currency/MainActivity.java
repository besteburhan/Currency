package besteburhan.currency;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewDate;
    TextView textViewCurr1;
    TextView textViewCurr2;
    TextView textViewCurr3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewCurr1 = (TextView) findViewById(R.id.textViewCurr1);
        textViewCurr2 = (TextView) findViewById(R.id.textViewCurr2);
        textViewCurr3 = (TextView) findViewById(R.id.textViewCurr3);

        final Handler handler =new Handler();
        Runnable test = new Runnable() {
            @Override
            public void run() {
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
                        Double doubleTRY =curr.getRate("TRY");
                        Double doubleCZK = curr.getRate("CZK");
                        Double doubleUSD = curr.getRate("USD");
                        textViewCurr1.setText("TRY = "+doubleTRY.toString() +" EUR");
                        textViewCurr2.setText("CZK = "+doubleCZK.toString() +" EUR");
                        textViewCurr3.setText("USD = "+doubleUSD.toString() +" EUR");

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy '  ' HH:mm:ss ");
                        String stringDate = simpleDateFormat.format(new Date());
                        textViewDate.setText(stringDate);
                    }

                    @Override
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"API IS NOT ACCESSIBLE!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                handler.postDelayed(this, 2000);
            }

        };
        handler.postDelayed(test, 0);
    }
}
