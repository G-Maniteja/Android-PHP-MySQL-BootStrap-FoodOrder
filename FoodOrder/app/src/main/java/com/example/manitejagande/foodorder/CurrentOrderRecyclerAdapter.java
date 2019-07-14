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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;




public class CurrentOrderRecyclerAdapter extends RecyclerView.Adapter<CurrentOrderRecyclerAdapter.CurrentOrderRecyclerViewHolder>
{
    Context context;
    int total=0;
    ArrayList<CartItemDataModel> data;

    public CurrentOrderRecyclerAdapter(Context context,ArrayList<CartItemDataModel> data) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public CurrentOrderRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currentorder_list_item, parent, false);
        CurrentOrderRecyclerViewHolder myViewHolder = new CurrentOrderRecyclerViewHolder(view);
        return myViewHolder;

    }
    public String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderRecyclerViewHolder holder, int position) {
        TextView c_itemname = holder.c_itemname;
        TextView c_itemprice = holder.c_itemprice;
        TextView orderid = holder.orderid;
        TextView qty=holder.qty;



        final String name = data.get(position).itemname.toString();
        final String price = data.get(position).itemprice.toString();
        final String order_id = data.get(position).orderid.toString();
        final String qty1= data.get(position).qty.toString();
        c_itemname.setText(name);
        c_itemprice.setText(price);
        orderid.setText(order_id);
        qty.setText(qty1);

        int intprice = Integer.parseInt(price);
        int intprice1 = Integer.parseInt(price);
        total = total + intprice;
    }

//    int grandTotal(){
//
//        int totalPrice = 0;
//        for(int i = 0 ; i < data.size(); i++) {
//            totalPrice += Integer.parseInt(data.get(i).itemprice);
//        }
//
//        return totalPrice;
//    }
        int grandTotal(){

            int totalPrice = 0;
            for(int i = 0 ; i < data.size(); i++) {
                int qty=Integer.parseInt(data.get(i).qty);
                totalPrice += Integer.parseInt(data.get(i).itemprice)*qty;
            }

            return totalPrice;
        }

    String return_oids(){

        String oids="";
        for(int i = 0 ; i < data.size(); i++) {
            oids = oids + "," + data.get(i).orderid;
        }

        return oids;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class CurrentOrderRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView c_itemname;
        TextView c_itemprice;
        TextView orderid;
        TextView qty;


        public CurrentOrderRecyclerViewHolder(View itemView) {
            super(itemView);
            this.c_itemname=(TextView)itemView.findViewById(R.id.itemname);
            this.c_itemprice=(TextView)itemView.findViewById(R.id.itemprice);
            this.orderid=(TextView)itemView.findViewById(R.id.orderid);
            this.qty=(TextView)itemView.findViewById(R.id.quantity);

        }
    }
}
