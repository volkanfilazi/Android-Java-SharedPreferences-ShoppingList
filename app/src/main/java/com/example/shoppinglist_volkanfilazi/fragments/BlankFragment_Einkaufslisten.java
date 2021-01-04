package com.example.shoppinglist_volkanfilazi.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.shoppinglist_volkanfilazi.interfaces.ICallBack;
import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;
import com.example.shoppinglist_volkanfilazi.MyApp;
import com.example.shoppinglist_volkanfilazi.R;
import com.example.shoppinglist_volkanfilazi.ShoppingList;
import com.example.shoppinglist_volkanfilazi.adapters.ShoppingListAdapter;
import com.example.shoppinglist_volkanfilazi.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

public class BlankFragment_Einkaufslisten extends Fragment implements ICallBack {

    View view;
    RecyclerView einkaufRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private IExtras dataExtras;
    ShoppingListAdapter shoppingListAdapter;
    List<ShoppingList> shoppingListList;
    ICallBack callBack;
    Context context;
    ConstraintLayout constraintLayout;
    Animation open, close, fromBottom, toBottom;
    boolean isOpen = false;


    private Utilities utilities;
    private Gson gson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        dataExtras.safeList(shoppingListAdapter.getShoppingListList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank__einkaufslisten, container, false);

        einkaufRecyclerView = view.findViewById(R.id.my_recycler_view);
        einkaufRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        setBackgroundColorandEdit();


        gson = new Gson();

        dataExtras = ((MyApp) getActivity().getApplication()).getExtrasData();
        shoppingListAdapter = new ShoppingListAdapter(dataExtras.getShoppingListList(), this, this.dataExtras, getContext());


        einkaufRecyclerView.setAdapter(shoppingListAdapter);


        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);


    }

    /**************************** ACTIONBAR ******************************************/

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_option_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (item.getItemId() == R.id.infoo) {
            Toast.makeText(getContext(),"The icons have longclick(delete)",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callMe(ShoppingList shoppingList) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        BlankFragment_CreateNewElement blankFragment_createNewElement = new BlankFragment_CreateNewElement();
        Bundle bundle = new Bundle();
        bundle.putSerializable("EditKey", shoppingList);
        blankFragment_createNewElement.setArguments(bundle);
        transaction.replace(R.id.frameLayout, blankFragment_createNewElement);
        transaction.commit();
    }


    /**************************** ACTIONBAR ******************************************/


    /************************ FLOATING ACTION BUTTON ******************************/

    public void setBackgroundColorandEdit() {
        constraintLayout = view.findViewById(R.id.constraintLayout);

        final FloatingActionButton floatingActionButton = view.findViewById(R.id.firstFloating);
        final FloatingActionButton floatingAddNewList = view.findViewById(R.id.secondFloating);



        open = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_open_anim);
        close = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getContext(), R.anim.to_bottom_anim);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {
                    floatingAddNewList.startAnimation(close);
                    floatingActionButton.startAnimation(fromBottom);
                    floatingAddNewList.setClickable(false);


                    isOpen = false;

                } else {
                    floatingAddNewList.startAnimation(open);
                    floatingActionButton.startAnimation(toBottom);


                    floatingAddNewList.setClickable(true);

                    isOpen = true;
                }

            }
        });

        floatingAddNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, new BlankFragment_CreateNewElement());
                transaction.commit();
            }
        });


        /************************ FLOATING ACTION BUTTON ******************************/


    }

}