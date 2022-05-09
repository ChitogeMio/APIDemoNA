package com.example.apidemo1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apidemo1a.api.ApiService;
import com.example.apidemo1a.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private TextView    txtTemrs;
    private TextView    txtSuorce;
    private TextView    txtusdvnd;
    private Button      btnCallAPT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSuorce = findViewById(R.id.tv_source);
        txtusdvnd = findViewById(R.id.tv_usdvnd);
        txtTemrs = findViewById(R.id.tv_terms);
        btnCallAPT = findViewById(R.id.btn_callApi);

        btnCallAPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickcallAPI();

            }
        });

    }

    private void clickcallAPI() {
//http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
        ApiService.apiService.converUSDtoVND("843d4d34ae72b3882e3db642c51e28e6"
                ,"VND","USD",1).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();
                if (currency!=null&&currency.getSuccess()){

                    txtTemrs.setText(currency.getTerms());
                    txtSuorce.setText(currency.getSource());
                    txtusdvnd.setText(String.valueOf(currency.getQuotes().getUSDVND()));

                }

            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}