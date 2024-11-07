package com.example.admin.newsapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.admin.newsapp.Items;
import com.example.admin.newsapp.MyNews;
import com.example.admin.newsapp.R;
import com.example.admin.newsapp.ShowActivity;
import com.example.admin.newsapp.Utils.AppConstants;
import com.example.admin.newsapp.Utils.Utils;
import com.example.admin.newsapp.adapters.NewsAdapter;
import com.example.admin.newsapp.deligate.NewsDelegate;
import com.example.admin.newsapp.deligate.TakeDelegate;
import com.example.admin.newsapp.networks.RetrofitHelper;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by Admin on 9/20/2018.
 */

public class NewsFrag extends Fragment{
    public static NewsFrag getInstance(int i){
        NewsFrag nf=new NewsFrag();
        Bundle bd=new Bundle();
        bd.putInt("Key",i);
        nf.setArguments(bd);
        return nf;
    }

    LinearLayout llInternet,llNoInternet;
    RecyclerView rv;
    FlyBanner banner;
    Button btnRetry;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.news_fragment,null);
        rv=v.findViewById(R.id.rv_nf);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        banner=v.findViewById(R.id.banner_nf);
        llInternet=v.findViewById(R.id.ll_internet);
        llNoInternet=v.findViewById(R.id.ll_noInternet);
        btnRetry=v.findViewById(R.id.btn_internet);
        rv.setNestedScrollingEnabled(false);
        return v;
    }
    RetrofitHelper rh;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rh=RetrofitHelper.getINS();
        getData();


        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData(){
        final Bundle bd=getArguments();
        int i=bd.getInt("Key");
        if(Utils.isInternet(getActivity())) {

            llNoInternet.setVisibility(View.GONE);
            llInternet.setVisibility(View.VISIBLE);

            if (i == AppConstants.Global) {
                rh.TakeGlobalNews(new TakeDelegate() {
                    @Override
                    public void onGetData(MyNews myNews) {
                        displayData(myNews);
                    }

                    @Override
                    public void onError(String str) {

                    }
                });
            } else if (i == AppConstants.Local) {
                rh.TakeLocalNews(new TakeDelegate() {
                    @Override
                    public void onGetData(MyNews myNews) {
                        displayData(myNews);
                    }

                    @Override
                    public void onError(String str) {

                    }
                });
            } else if (i == AppConstants.English) {
                rh.TakeEnglishNews(new TakeDelegate() {
                    @Override
                    public void onGetData(MyNews myNews) {
                        displayData(myNews);
                    }

                    @Override
                    public void onError(String str) {

                    }
                });
            }

        }else {
            llNoInternet.setVisibility(View.VISIBLE);
            llInternet.setVisibility(View.GONE);
        }
    }

    public void displayData(MyNews mn){
        List<Items> items=mn.getItems();

        List<String> imgurl=new ArrayList<>();
        for(Items i:items){
            imgurl.add(i.getEnclosure().getLink());
        }
        banner.setImagesUrl(imgurl);

        NewsAdapter adapter=new NewsAdapter(getActivity(), items, new NewsDelegate() {
            @Override
            public void onNewsClick(Items items) {
                Intent i1=new Intent(getActivity(), ShowActivity.class);
                Bundle bd1=new Bundle();
                bd1.putParcelable("item1",items);
                bd1.putString("image1",items.getEnclosure().getLink());
                i1.putExtras(bd1);
                startActivity(i1);
            }
        });
        rv.setAdapter(adapter);

    }
}
