package com.example.shoppinglist_volkanfilazi.interfaces;

import com.example.shoppinglist_volkanfilazi.ShoppingList;
import com.example.shoppinglist_volkanfilazi.ShoppingListEntry;

import java.util.List;
import java.util.UUID;

public interface IExtras {

    void addList(ShoppingList shoppingList);
    void safeList(List<ShoppingList> shoppingListList);
    List<ShoppingList> getShoppingListList();

    void addUncheckedEntry(ShoppingListEntry entry, String shoppingListId);
    void safeList2(List<ShoppingListEntry> uncheckedEntries);
    List<ShoppingListEntry> getUncheckedEntries();

    void uncheckEntry(ShoppingListEntry shoppingListEntry,String shoppingListId );
    void checkEntry(ShoppingListEntry entry,String shoppingListId);
    void safeList3(List<ShoppingListEntry> checkedEntries);
    List<ShoppingListEntry> getCheckedEntries();



    public ShoppingList getShoppingList(String id);









}
