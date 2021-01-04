package com.example.shoppinglist_volkanfilazi.interfaces;

import com.example.shoppinglist_volkanfilazi.ShoppingListEntry;

public interface OnCheckListener

{
    void onCheckBoxCheked(ShoppingListEntry shoppingListEntry, int position);
    void onCheckBoxUnchecked(ShoppingListEntry shoppingListEntry, int position);
}
