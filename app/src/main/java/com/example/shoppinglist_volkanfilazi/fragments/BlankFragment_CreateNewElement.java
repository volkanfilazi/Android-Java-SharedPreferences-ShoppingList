package com.example.shoppinglist_volkanfilazi.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.shoppinglist_volkanfilazi.interfaces.ICallBack;
import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;
import com.example.shoppinglist_volkanfilazi.MyApp;
import com.example.shoppinglist_volkanfilazi.R;
import com.example.shoppinglist_volkanfilazi.SharedDataStorage;
import com.example.shoppinglist_volkanfilazi.ShoppingList;
import com.example.shoppinglist_volkanfilazi.adapters.ShoppingListAdapter;
import com.example.shoppinglist_volkanfilazi.ShoppingListEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;


public class BlankFragment_CreateNewElement extends Fragment implements IExtras {

    private EditText editText;
    private Button btnAdd, btnCancel;
    private ImageView imageView;

    int mDefaultColor;

    ShoppingList addToShoplist;
    ShoppingListAdapter adapter;

    private IExtras data;

    View view;

    private ConstraintLayout constraintLayout;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    List<ShoppingList> shoppingListList;
    ICallBack callBack;
    Context context;
    SharedDataStorage sharedDataStorage;


    private Spinner spinner;


    String name;
    int color;
    int uniqueID;

    private int flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_blank__create_new_element, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar1);

        Bundle bundle = getArguments();
        if (bundle != null) {
            // ShoppingList shoppingList = (ShoppingList) bundle.getSerializable("shopkey");
        }


        flag = 0;

        constraintLayout = view.findViewById(R.id.constraintLayout);
        layoutManager = new LinearLayoutManager(getActivity());


        editText = view.findViewById(R.id.etTitle);
        btnAdd = view.findViewById(R.id.create);
        btnCancel = view.findViewById(R.id.btnCancel);
        imageView = view.findViewById(R.id.imageView);





        //extras = (Applicatio) getActivity().getApplication().getSafeListItems();


        //final int icon1 = Integer.parseInt(imageView.toString());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = editText.getText().toString();


                data.addList(new ShoppingList(UUID.randomUUID(), name, flag, mDefaultColor, new ArrayList<ShoppingListEntry>(), new ArrayList<ShoppingListEntry>()));


                getActivity().getSupportFragmentManager().popBackStack();
                back();



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        data = ((MyApp) getActivity().getApplication()).getExtrasData();


        adapter = new ShoppingListAdapter(data.getShoppingListList(), callBack, data, context);


        //rvShop.setAdapter(shoppingListAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.createnewelement_menu_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (item.getItemId() == R.id.back) {
            transaction.replace(R.id.frameLayout, new BlankFragment_Einkaufslisten());
            transaction.commit();
        } else if (item.getItemId() == R.id.pickcolor) {

            mDefaultColor = ContextCompat.getColor(getActivity(), R.color.colorPrimary);
            openColorPicker();

        } else if (item.getItemId() == R.id.würfel) {


            if (flag == 0) {
                imageView.setImageResource(R.drawable.ic_baseline_menu_book_24);
                flag = 1;


            } else if (flag == 1) {
                imageView.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24);
                flag = 2;
            } else if (flag == 2) {
                imageView.setImageResource(R.drawable.ic_baseline_directions_bike_24);
                flag = 3;
            } else if (flag == 3) {
                imageView.setImageResource(R.drawable.icons8_add_property_96px);
                flag = 0;
            }

        }
        return super.onOptionsItemSelected(item);
    }


    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                imageView.setBackgroundColor(mDefaultColor);

            }
        });
        colorPicker.show();
    }

    public void back(){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new BlankFragment_Einkaufslisten());
        transaction.commit();
    }


    @Override
    public void addList(ShoppingList shoppingList) {

    }


    @Override
    public void safeList(List<ShoppingList> shoppingListList) {
        sharedDataStorage.safeList(adapter.getShoppingListList());
    }


    @Override
    public List<ShoppingList> getShoppingListList() {
        return null;
    }

    @Override
    public void addUncheckedEntry(ShoppingListEntry entry,String id) {

    }

    @Override
    public void safeList2(List<ShoppingListEntry> uncheckedEntries) {

    }

    @Override
    public void checkEntry(ShoppingListEntry entry, String shoppingListId) {

    }

    @Override
    public void safeList3(List<ShoppingListEntry> checkedEntries) {

    }

    @Override
    public List<ShoppingListEntry> getCheckedEntries() {
        return null;
    }


    @Override
    public List<ShoppingListEntry> getUncheckedEntries() {
        return null;
    }

    @Override
    public void uncheckEntry(ShoppingListEntry shoppingListEntry, String shoppingListId) {

    }

    @Override
    public ShoppingList getShoppingList(String id) {
        return null;
    }


}
