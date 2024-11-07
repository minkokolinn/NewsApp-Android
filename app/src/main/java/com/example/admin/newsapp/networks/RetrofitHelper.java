package com.example.admin.newsapp.networks;

import android.support.v7.widget.RecyclerView;

import com.example.admin.newsapp.MyNews;
import com.example.admin.newsapp.Utils.AppConstants;
import com.example.admin.newsapp.deligate.TakeDelegate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 9/20/2018.
 */

public class RetrofitHelper {
    private static RetrofitHelper INS;

    public static RetrofitHelper getINS(){
        if(INS==null){
            INS=new RetrofitHelper();
        }
        return INS;
    }

    ApiService api;

    private RetrofitHelper(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api=retrofit.create(ApiService.class);

    }

    public void TakeGlobalNews(final TakeDelegate delegate){
        api.getGlobalNews().enqueue(new Callback<MyNews>() {
            @Override
            public void onResponse(Call<MyNews> call, Response<MyNews> response) {
                delegate.onGetData(response.body());
            }

            @Override
            public void onFailure(Call<MyNews> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }

    public void TakeLocalNews(final TakeDelegate delegate){
        api.getLocalNews().enqueue(new Callback<MyNews>() {
            @Override
            public void onResponse(Call<MyNews> call, Response<MyNews> response) {
                delegate.onGetData(response.body());
            }

            @Override
            public void onFailure(Call<MyNews> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }

    public void TakeEnglishNews(final TakeDelegate delegate){
        api.getEnglishNews().enqueue(new Callback<MyNews>() {
            @Override
            public void onResponse(Call<MyNews> call, Response<MyNews> response) {
                delegate.onGetData(response.body());
            }

            @Override
            public void onFailure(Call<MyNews> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }


}
