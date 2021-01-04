package com.example.shoppinglist_volkanfilazi.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;
import com.example.shoppinglist_volkanfilazi.MyApp;
import com.example.shoppinglist_volkanfilazi.interfaces.OnCheckListener;
import com.example.shoppinglist_volkanfilazi.R;
import com.example.shoppinglist_volkanfilazi.adapters.SecondListAdapter;
import com.example.shoppinglist_volkanfilazi.SharedDataStorage;
import com.example.shoppinglist_volkanfilazi.ShoppingList;
import com.example.shoppinglist_volkanfilazi.ShoppingListEntry;
import com.example.shoppinglist_volkanfilazi.adapters.ThirdListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;


public class BlankFragment_CreateNewList extends Fragment implements OnCheckListener {

    RecyclerView recyclerView,recyclerView2;
    SecondListAdapter mAdapter;
    ThirdListAdapter thirdListAdapter;
    View view;

    Context context;
    ShoppingListEntry shoppingListEntry;
    SharedDataStorage sharedDataStorage;
    List<ShoppingListEntry> uncheckedEntries;
    IExtras data;
    ShoppingList shoppingList;



    private String bok;
    private String id;

    public BlankFragment_CreateNewList(String id){
        this.id = id;
    }





    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_blank__create_new_list, container, false);



        recyclerView = view.findViewById(R.id.rvEdit);
        recyclerView2 = view.findViewById(R.id.rvEdit2);

        floatingAction();




        Gson gson = new Gson();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));




        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        data = ((MyApp) getActivity().getApplication()).getExtrasData();


        shoppingList = data.getShoppingList(this.id);

        thirdListAdapter = new ThirdListAdapter(shoppingList.getCheckedEntries(),shoppingList,data,getContext(),this);
        mAdapter = new SecondListAdapter(shoppingList.getUncheckedEntries(),shoppingList,data,getContext(),this);
        recyclerView2.setAdapter(thirdListAdapter);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.after_iconclick_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (item.getItemId() == R.id.back) {
            transaction.replace(R.id.frameLayout, new BlankFragment_Einkaufslisten());
            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void floatingAction() {

        final FloatingActionButton btnFloating = view.findViewById(R.id.btnFloating);


        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("New List")
                        .setMessage("Write a new list! ");

                final EditText input = new EditText(getContext());


                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.ic_baseline_edit_36);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        bok = input.getText().toString();
                        if (bok.equals(null)) {
                            Toast.makeText(getContext(), "You have to write something", Toast.LENGTH_LONG).show();

                        }
                        else{
                          data.addUncheckedEntry(new ShoppingListEntry(UUID.randomUUID(),bok,false),id);
                          //data.checkEntry(new ShoppingListEntry(UUID.randomUUID(),bok,true),id);
                            refreshAdapters();

                        }


                    }

                });

                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });


    }


    @Override
    public void onCheckBoxCheked(ShoppingListEntry shoppingListEntry, int position) {
        data.checkEntry(shoppingListEntry,id);
        refreshAdapters();
    }

    @Override
    public void onCheckBoxUnchecked(ShoppingListEntry shoppingListEntry, int position) {
       data.uncheckEntry(shoppingListEntry,id);
       refreshAdapters();

    }

    private void refreshAdapters(){
        ShoppingList list = data.getShoppingList(id);
        mAdapter.setUncheckedEntries(list.getUncheckedEntries());
        mAdapter.notifyDataSetChanged();
        thirdListAdapter.setCheckedEntries(list.getCheckedEntries());
        thirdListAdapter.notifyDataSetChanged();
    }
}