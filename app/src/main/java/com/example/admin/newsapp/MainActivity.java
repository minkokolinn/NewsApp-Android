package com.example.admin.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.admin.newsapp.Fragments.NewsFrag;
import com.example.admin.newsapp.Utils.AppConstants;

public class MainActivity extends AppCompatActivity {
    NavigationView nav;
    FrameLayout fl;
    Toolbar tb;
    DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav=findViewById(R.id.nav_view_am);
        fl=findViewById(R.id.fragmentHolder);
        tb=findViewById(R.id.tb_am);
        setSupportActionBar(tb);
        dl=findViewById(R.id.dlayout);



        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(MainActivity.this,dl,tb,R.string.openDrawer,R.string.closeDrawer);
        dl.setDrawerListener(toggle);
        toggle.syncState();
            showFragment(NewsFrag.getInstance(AppConstants.Global));
            nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.local_id){
                        showFragment(NewsFrag.getInstance(AppConstants.Local));
                    }else if(item.getItemId()==R.id.global_id) {
                        showFragment(NewsFrag.getInstance(AppConstants.Global));
                    }else if(item.getItemId()==R.id.english_id){
                        showFragment(NewsFrag.getInstance(AppConstants.English));
                    }
                    dl.closeDrawer(Gravity.START);
                    return true;
                }
            });
        }



    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder,fragment).commit();
    }
}
