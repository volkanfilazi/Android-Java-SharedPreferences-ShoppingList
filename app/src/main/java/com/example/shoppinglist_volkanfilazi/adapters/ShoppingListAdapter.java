package com.example.shoppinglist_volkanfilazi.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist_volkanfilazi.interfaces.ICallBack;
import com.example.shoppinglist_volkanfilazi.interfaces.IExtras;
import com.example.shoppinglist_volkanfilazi.R;
import com.example.shoppinglist_volkanfilazi.ShoppingList;
import com.example.shoppinglist_volkanfilazi.fragments.BlankFragment_CreateNewList;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>  {


    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alert;
    private List<ShoppingList> shoppingListList;
    private IExtras extra;
    public ICallBack callBack;


    private Context context;

    public ShoppingListAdapter(List<ShoppingList> shoppingListList,ICallBack callBack, IExtras extra, Context context){
        this.shoppingListList = shoppingListList;
        this.extra = extra;
        this.context = context;
        this.callBack = callBack;


    }



    public ICallBack getCallBack() {
        return callBack;
    }

    public IExtras getExtra() {
        return extra;
    }

    public void setExtra(IExtras extra) {
        this.extra = extra;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ShoppingList> getShoppingListList(){
        return shoppingListList;
    }

    public void setShoppingListList(List<ShoppingList> shoppingListList) {
        this.shoppingListList = shoppingListList;
        notifyDataSetChanged();
    }



    public void addShopList(ShoppingList shoppingList){
        this.shoppingListList.add(shoppingList);
        notifyDataSetChanged();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView,imageView1,closeImage;
        private TextView tvDes,tvDes1;
        private CardView cardView;
        private LinearLayout linearLayout;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvDes = itemView.findViewById(R.id.tvDes);


            closeImage = itemView.findViewById(R.id.closeImage);

            cardView = itemView.findViewById(R.id.cardView);
            linearLayout= itemView.findViewById(R.id.linearLayout);





        }
    }


    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.einkaufslisten_items, parent, false);
        //view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        ViewHolder vh = new ViewHolder(view);
        return vh;


    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ShoppingList shoppingList = shoppingListList.get(position);
        //
        //final ShoppingList.Icon shoppingList = ShoppingList.Icon.geticonList(position);

        holder.tvDes.setText(shoppingList.getName());




        int select;

        switch (shoppingList.getIcon()){
            case 0:
                select = R.drawable.ic_baseline_menu_book_24;
                break;
            case 1:
                select =R.drawable.ic_baseline_add_shopping_cart_24;
                break;
            case 2:
                select =R.drawable.ic_baseline_directions_bike_24;
                break;
            case 3 :
                select = R.drawable.icons8_add_property_96px;
                break;
            default:
                select = 0;
        }


        holder.imageView.setImageResource(select);
        holder.closeImage.setImageResource(R.drawable.icons8_circled_2_c_96px_1);
        holder.imageView.setColorFilter(shoppingList.getColor());
        holder.tvDes.setBackgroundColor(shoppingList.getColor());



        //BlankFragment_CreateNewElement blankFragment_createNewElement = new BlankFragment_CreateNewElement();
        //blankFragment_createNewElement.openColorPicker();

        //holder.imageView.setBackgroundColor();

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                return false;
            }
        });



        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                holder.closeImage.setImageResource(R.drawable.icons8_shutdown_96px);
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();

                holder.closeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final AlertDialog dialog = new AlertDialog.Builder(context)
                                .setTitle("Close")
                                .setMessage("Select an Option")
                                .setPositiveButton("Remove",null)
                                .setNegativeButton("Cancel",null)
                                .show();

                        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shoppingListList.remove(shoppingList);
                                dialog.dismiss();
                                notifyDataSetChanged();
                                notifyItemRemoved(position);
                            }
                        });


                        Button cancel = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                    }
                });



                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.closeImage.setImageResource(R.drawable.icons8_circled_2_c_96px_1);
                    }
                });

                return true;


            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Toast.makeText(context,"test",Toast.LENGTH_LONG).show();
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                BlankFragment_CreateNewList blankFragment_edit = new BlankFragment_CreateNewList(shoppingList.getId().toString());
                ft.replace(R.id.frameLayout,blankFragment_edit);

                ft.commit();


            }
        });
        /*holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                longClick();

                return false;
            }
        });*/


    }

    public void longClick(){


    }


@Override
    public int getItemCount() {
        return shoppingListList.size();

    }


}




    




