package com.example.shoppinglist_volkanfilazi;

import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ShoppingList implements Serializable   {

    enum Category {
        Unchecked,
        Checked
    }

    @NonNull
    private UUID id;

    @NonNull
    private String name;


    private int icon;

    @ColorInt
    private int color;

    @NonNull
    private List<ShoppingListEntry> checkedEntries;

    @NonNull
    private List<ShoppingListEntry> uncheckedEntries;


    public ShoppingList(@NonNull UUID id, @NonNull String name, int icon, int color, @NonNull List<ShoppingListEntry> checkedEntries, @NonNull List<ShoppingListEntry> uncheckedEntries) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        if (checkedEntries == null) {
            throw new IllegalArgumentException("checkedEntries must not be null");
        }
        if (uncheckedEntries == null) {
            throw new IllegalArgumentException("uncheckedEntries must not be null");
        }
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.checkedEntries = checkedEntries;
        this.uncheckedEntries = uncheckedEntries;
    }

    public ShoppingList(@NonNull UUID id, @NonNull String name, int icon, int color) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.checkedEntries = new ArrayList<>();
        this.uncheckedEntries = new ArrayList<>();
    }



    public enum Icon implements Serializable {
        ICON1(R.drawable.ic_baseline_add_shopping_cart_24),
        ICON2(R.drawable.ic_baseline_directions_bike_24),
        ICON3(R.drawable.ic_baseline_menu_book_24),
        ICON4(R.drawable.icons8_add_property_96px);


        public final int drawable;


        private Icon (int drawable){
            this.drawable = drawable;
        }

        public int getIcons(){
            return this.drawable;
        }

        public static List<Icon> geticonList(){
            ArrayList a = new ArrayList<>();
            a.add(ICON1);
            a.add(ICON2);
            a.add(ICON3);
            a.add(ICON4);

            return a;
        }

    }


    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @NonNull
    public List<ShoppingListEntry> getCheckedEntries() {
        return checkedEntries;
    }

    public void setCheckedEntries(@NonNull List<ShoppingListEntry> checkedEntries) {
        if (checkedEntries == null) {
            throw new IllegalArgumentException("checkedEntries must not be null");
        }
        this.checkedEntries = checkedEntries;
    }

    @NonNull
    public List<ShoppingListEntry> getUncheckedEntries() {
        return uncheckedEntries;
    }

    public void setUncheckedEntries(@NonNull List<ShoppingListEntry> uncheckedEntries) {
        if (uncheckedEntries == null) {
            throw new IllegalArgumentException("uncheckedEntries must not be null");
        }
        this.uncheckedEntries = uncheckedEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return icon == that.icon &&
                color == that.color &&
                id.equals(that.id) &&
                name.equals(that.name) &&
                checkedEntries.equals(that.checkedEntries) &&
                uncheckedEntries.equals(that.uncheckedEntries);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, name, icon, color, checkedEntries, uncheckedEntries);
    }
}

