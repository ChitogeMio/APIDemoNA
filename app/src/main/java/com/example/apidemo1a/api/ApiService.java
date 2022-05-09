package com.example.apidemo1a.api;

import com.example.apidemo1a.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH-mm-ss")
        .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://apilayer.net/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/live")
    Call<Currency>converUSDtoVND(@Query("access_key") String access_key
                                ,@Query("currencies") String currencies
                                ,@Query("source") String source
                                ,@Query("format") int format);
}
