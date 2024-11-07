package com.example.admin.newsapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.admin.newsapp.Fragments.NewsFrag;
import com.example.admin.newsapp.R;
import com.example.admin.newsapp.Utils.AppConstants;

/**
 * Created by Admin on 9/21/2018.
 */

public class Bnv extends AppCompatActivity{
    BottomNavigationView bnv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bnv);
        bnv=findViewById(R.id.bottomNavigationView);
        showFragment(NewsFrag.getInstance(AppConstants.Global));
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.local_id){
                    showFragment(NewsFrag.getInstance(AppConstants.Local));
                }else if(item.getItemId()==R.id.global_id) {
                    showFragment(NewsFrag.getInstance(AppConstants.Global));
                }else if(item.getItemId()==R.id.english_id){
                    showFragment(NewsFrag.getInstance(AppConstants.English));
                }
                return true;
            }
        });

    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }
}
