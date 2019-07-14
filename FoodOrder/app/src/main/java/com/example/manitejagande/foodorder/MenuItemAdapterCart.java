package com.example.manitejagande.foodorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MenuItemAdapterCart extends RecyclerView.Adapter<MenuItemAdapterCart.MenuItemViewHolder>
{
    Context context;
    ArrayList<MenuItemDataModel> data;

    public MenuItemAdapterCart(Context context, ArrayList<MenuItemDataModel> data) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_item, parent, false);
        MenuItemViewHolder myViewHolder = new MenuItemViewHolder(view);
        return myViewHolder;

    }
    public String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        TextView itemname=holder.itemname;
        TextView itemprice=holder.itemprice;
        Button additem=holder.additem;
        final Button qsub=holder.qsub;
        final TextView qty=holder.qty;
        Button qadd=holder.qadd;

        final String name=data.get(position).itemname.toString();
        final String price=data.get(position).itemprice.toString();

        itemname.setText(name);
        itemprice.setText(price);

        qty.setText("0");
        qsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty.getText().toString().equals("0"))
                {
                    //Log.i("qwer123456","12345"+qty.getText().toString());
                    Toast.makeText(qsub.getContext(), "Cannot decrease the Quantity..",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int qty1=Integer.parseInt(qty.getText().toString());
                    qty1--;
                    qty.setText(Integer.toString(qty1));
                }
            }
        });

        qadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty1=Integer.parseInt(qty.getText().toString());
                qty1++;
                qty.setText(Integer.toString(qty1));
            }
        });

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url="https://inamtginamtg.000webhostapp.com/cart.php";
                if(qty.getText().toString().equals("0"))
                {
                    Toast.makeText(context,"Please add Quantity of the Item..",Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = LoginActivity.weburl + "cart.php";

                    String email = getDefaults("userid", context);
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("email", email)
                            .appendQueryParameter("itemname", name)
                            .appendQueryParameter("itemprice", price)
                            .appendQueryParameter("qty",qty.getText().toString());


                    String query = builder.build().getEncodedQuery();
                    Log.i("url", query);
                    Toast.makeText(context, "Item Added Successfully..!", Toast.LENGTH_SHORT).show();
                    Async_Task a = new Async_Task(context, url, query, true);
                    a.execute();
                    Toast.makeText(context, "Item added to Cart", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(context,CartActivity.class);
                    context.startActivity(i);

                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView itemname;
        TextView itemprice;
        Button additem;
        Button qsub;
        Button qadd;
        TextView qty;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            this.itemname=(TextView)itemView.findViewById(R.id.itemname);
            this.itemprice=(TextView)itemView.findViewById(R.id.itemprice);
            this.additem=(Button)itemView.findViewById(R.id.additem);
            this.qsub=(Button)itemView.findViewById(R.id.qsub);
            this.qty=(TextView)itemView.findViewById(R.id.qty);
            this.qadd=(Button)itemView.findViewById(R.id.qadd);


        }
    }
}
