package com.example.admin.newsapp.deligate;

import com.example.admin.newsapp.MyNews;

/**
 * Created by Admin on 9/20/2018.
 */

public interface TakeDelegate {
    void onGetData(MyNews myNews);

    void onError(String str);
}
