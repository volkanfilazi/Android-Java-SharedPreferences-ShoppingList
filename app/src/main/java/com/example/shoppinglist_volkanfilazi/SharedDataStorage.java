package com.example.shoppinglist_volkanfilazi;

import android.content.SharedPreferences;

import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedDataStorage implements IExtras {

    SharedPreferences sharedPreferences;

    public SharedDataStorage (SharedPreferences sharedPreferences){
        this.sharedPreferences =sharedPreferences;
    }
    Gson gson = new Gson();

    /******************** FIRST ADAPTER *****************************/

    @Override
    public void addList(ShoppingList shoppingList) {
        List<ShoppingList> shoppingListList = getShoppingListList();
        shoppingListList.add(shoppingList);
        safeList(shoppingListList);

    }



    @Override
    public void safeList(List<ShoppingList> shoppingListList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String shopStringJson = gson.toJson(shoppingListList);/*startList*/
        editor.putString("jsonlist1",shopStringJson);
        editor.apply();
    }



    @Override
    public List<ShoppingList> getShoppingListList() {
        String defaultValue = "[]";
        String shopStringJson = sharedPreferences.getString("jsonlist1",defaultValue);
        Type x = new TypeToken<ArrayList<ShoppingList>>(){}.getType();
        List<ShoppingList> shoppingListList = gson.fromJson(shopStringJson,x);
        return shoppingListList;
    }

    /*************************** SECOND ADAPTER **********************/

    @Override
    public void addUncheckedEntry(ShoppingListEntry entry,String shoppingListId) {


        List<ShoppingList> shoppingListList = getShoppingListList();

        for (ShoppingList list : shoppingListList){
            if (list.getId().toString().equals(shoppingListId)){
                list.getUncheckedEntries().add(entry);

            }
        }

        safeList(shoppingListList);
    }

    @Override
    public void safeList2(List<ShoppingListEntry> uncheckedEntries) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String testtest =gson.toJson(uncheckedEntries);
        editor.putString("johnlist2",testtest);
        editor.apply();
    }

    @Override
    public List<ShoppingListEntry> getUncheckedEntries() {
        String defaultValue ="[]";
        String testtest = sharedPreferences.getString("johnlist2",defaultValue);
        Type y = new TypeToken<ArrayList<ShoppingListEntry>>(){}.getType();
        List<ShoppingListEntry> uncheckedEntries =gson.fromJson(testtest,y);

        return uncheckedEntries;
    }

    @Override
    public void uncheckEntry(ShoppingListEntry shoppingListEntry, String shoppingListId) {
        List<ShoppingList> shoppingListList = getShoppingListList();

        for (ShoppingList list : shoppingListList){
            if (list.getId().toString().equals(shoppingListId)){
                list.getCheckedEntries().remove(shoppingListEntry);
                list.getUncheckedEntries().add(shoppingListEntry);
            }
        }



        safeList(shoppingListList);
    }

    /*********************** THIRD ADAPTER ************************************/


    @Override
    public void checkEntry(ShoppingListEntry entry,String shoppingListId) {
        List<ShoppingList> shoppingListList = getShoppingListList();

        for (ShoppingList list : shoppingListList){
            if (list.getId().toString().equals(shoppingListId)){
                list.getUncheckedEntries().remove(entry);
                list.getCheckedEntries().add(entry);
            }
        }



        safeList(shoppingListList);
    }

    @Override
    public void safeList3(List<ShoppingListEntry> checkedEntries) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String checkedJhon =gson.toJson(checkedEntries);
        editor.putString("johnList3",checkedJhon);
        editor.apply();
    }

    @Override
    public List<ShoppingListEntry> getCheckedEntries() {

        String defaultValue ="[]";
        String checkedJhon = sharedPreferences.getString("johnList3",defaultValue);
        Type z = new TypeToken<ArrayList<ShoppingListEntry>>(){}.getType();
        List<ShoppingListEntry> checkedEntries = gson.fromJson(checkedJhon,z);

        return checkedEntries;


    }


    @Override
    public ShoppingList getShoppingList(String id) {
        ShoppingList shoppingList = null;

        for (ShoppingList list : getShoppingListList()){
            if (list.getId().toString().equals(id)){
                shoppingList = list;
            }
        }

        return shoppingList;
    }


}
