package com.example.cryptonews;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cryptonews.Models.CryptoNewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getCryptoNewsHeadlines(OnFetchDataListener listener, String query)
    {
        CallCryptoNewsApi callCryptoNewsApi = retrofit.create(CallCryptoNewsApi.class);
        Call<CryptoNewsApiResponse> call = callCryptoNewsApi.callheadlines(  query,  context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<CryptoNewsApiResponse>() {
                @Override
                public void onResponse(Call<CryptoNewsApiResponse> call, Response<CryptoNewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("Veri: ",response.body().toString());
                    listener.onFetchData(response.body().getArticles(), response.message());

                }

                @Override
                public void onFailure(Call<CryptoNewsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public RequestManager(Context context) { this.context = context; }

    public interface CallCryptoNewsApi {
        @GET("everything")
        Call<CryptoNewsApiResponse> callheadlines(
                @Query("q") String query,
                @Query("apikey") String api_key
                );
    }

}
