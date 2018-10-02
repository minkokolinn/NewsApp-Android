package com.example.admin.newsapp.networks;



import com.example.admin.newsapp.MyNews;
import com.example.admin.newsapp.Utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 9/19/2018.
 */

public interface ApiService {
    @GET(AppConstants.LOCAL_URL)
    Call<MyNews> getLocalNews();

    @GET(AppConstants.GLOBAL_URL)
    Call<MyNews> getGlobalNews();

    @GET(AppConstants.ENGLISH_URL)
    Call<MyNews> getEnglishNews();
}
