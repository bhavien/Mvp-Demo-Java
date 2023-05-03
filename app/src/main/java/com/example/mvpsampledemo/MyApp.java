package com.example.mvpsampledemo;

import android.app.Application;
import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.data.SharedPref;


public class MyApp extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPref sharedPrefsHelper = new SharedPref(this);
        dataManager = new DataManager(sharedPrefsHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }


}
