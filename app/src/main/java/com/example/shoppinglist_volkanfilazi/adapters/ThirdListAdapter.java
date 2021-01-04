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

public class ThirdListAdapter extends RecyclerView.Adapter<ThirdListAdapter.ViewHolder> {

    private Context context;
    private ShoppingList shoppingList;
    List<ShoppingListEntry> checkedEntries;
    IExtras extras;
    private OnCheckListener onCheckListener;

    public ThirdListAdapter(List<ShoppingListEntry> checkedEntries,ShoppingList shoppingList,IExtras extras,Context context,OnCheckListener onCheckListener){
        this.checkedEntries = checkedEntries;
        this.shoppingList = shoppingList;
        this.context = context;
        this.extras = extras;
        this.onCheckListener = onCheckListener;
    }

    public OnCheckListener getOnCheckListener() {
        return onCheckListener;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public IExtras getExtras() {
        return extras;
    }

    public void setExtras(IExtras extras) {
        this.extras = extras;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<ShoppingListEntry> getCheckedEntries() {
        return checkedEntries;
    }

    public void setCheckedEntries(List<ShoppingListEntry> checkedEntries) {
        this.checkedEntries = checkedEntries;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lists_after_iconclick, parent, false);
        ThirdListAdapter.ViewHolder vh = new ThirdListAdapter.ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShoppingListEntry shoppingListEntry = checkedEntries.get(position);


                holder.textView.setText(shoppingListEntry.getName());
                holder.checkImage.setVisibility(View.VISIBLE);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onCheckListener.onCheckBoxUnchecked(shoppingListEntry,position);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return checkedEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView checkImage;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewList);
            textView = itemView.findViewById(R.id.tvTextView);
            checkImage = itemView.findViewById(R.id.closeImageList);
        }
    }
}
