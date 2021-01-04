package com.example.shoppinglist_volkanfilazi;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Utilities  {

    SharedPreferences sharedPreferences;

    public Utilities(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    Gson gson = new Gson();



}
