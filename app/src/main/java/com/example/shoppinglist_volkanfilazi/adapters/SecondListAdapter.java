package com.example.shoppinglist_volkanfilazi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;
import com.example.shoppinglist_volkanfilazi.interfaces.OnCheckListener;
import com.example.shoppinglist_volkanfilazi.R;
import com.example.shoppinglist_volkanfilazi.ShoppingList;
import com.example.shoppinglist_volkanfilazi.ShoppingListEntry;

import java.util.List;

public class SecondListAdapter extends RecyclerView.Adapter<SecondListAdapter.ViewHolder> {

    private Context context;
    private ShoppingList shoppingList;
    private List<ShoppingListEntry> uncheckedEntries;
    private OnCheckListener onCheckListener;

    IExtras extras;

    public SecondListAdapter(List<ShoppingListEntry> uncheckedEntries, ShoppingList shoppingList, IExtras extras, Context context,OnCheckListener onCheckListener){
        this.context = context;
        this.uncheckedEntries = uncheckedEntries;
        this.extras = extras;
        this.shoppingList = shoppingList;
        this.onCheckListener = onCheckListener;


    }

    public IExtras getExtras() {
        return extras;
    }

    public void setExtras(IExtras extras) {
        this.extras = extras;
    }



    public List<ShoppingListEntry> getUncheckedEntries() {
        return uncheckedEntries;
    }

    public void setUncheckedEntries(List<ShoppingListEntry> uncheckedEntries) {
        this.uncheckedEntries = uncheckedEntries;
        notifyDataSetChanged();
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lists_after_iconclick, parent, false);
        SecondListAdapter.ViewHolder vh = new SecondListAdapter.ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ShoppingListEntry shoppingListEntry = uncheckedEntries.get(position);


        holder.textView.setText(shoppingListEntry.getName().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onCheckListener.onCheckBoxCheked(shoppingListEntry,position);


            }
        });





    }

    @Override
    public int getItemCount() {
        return uncheckedEntries.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView checkImage;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewList);
            textView = itemView.findViewById(R.id.tvTextView);
            checkImage = itemView.findViewById(R.id.closeImage);
        }
    }
}
