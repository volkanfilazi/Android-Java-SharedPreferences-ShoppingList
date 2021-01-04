package com.example.shoppinglist_volkanfilazi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.shoppinglist_volkanfilazi.fragments.BlankFragment_Einkaufslisten;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityFragment();
    }

    private void startActivityFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new BlankFragment_Einkaufslisten());
        transaction.commit();
    }
}