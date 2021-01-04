package com.example.shoppinglist_volkanfilazi;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Objects;
import java.util.UUID;

public class ShoppingListEntry {

    @NonNull
    private UUID id;

    @NonNull
    private String name;

    private boolean checked;

    public ShoppingListEntry(@NonNull UUID id, @NonNull String name, boolean checked) {
        if(id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        if(name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        if(name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListEntry that = (ShoppingListEntry) o;
        return checked == that.checked &&
                id.equals(that.id) &&
                name.equals(that.name);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, name, checked);
    }
}
