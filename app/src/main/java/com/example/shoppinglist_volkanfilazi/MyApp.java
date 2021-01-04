package com.example.shoppinglist_volkanfilazi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;

import timber.log.Timber;

public class MyApp extends Application {
    private SharedPreferences sharedPreferences;
    private IExtras extrasData;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("test1", Context.MODE_PRIVATE);
        extrasData = new SharedDataStorage(sharedPreferences);
        Timber.plant(new Timber.DebugTree());
    }

    public IExtras getExtrasData(){
        return extrasData;
    }
}
