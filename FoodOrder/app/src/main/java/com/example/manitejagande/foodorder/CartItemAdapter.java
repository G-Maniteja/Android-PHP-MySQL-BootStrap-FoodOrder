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




public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>
{
    Context context;
    int total=0;
    ArrayList<CartItemDataModel> data;

    public CartItemAdapter(Context context,ArrayList<CartItemDataModel> data) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cart_item, parent, false);
        CartItemViewHolder myViewHolder = new CartItemViewHolder(view);
        return myViewHolder;

    }
    public String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        TextView c_itemname=holder.c_itemname;
        TextView c_itemprice=holder.c_itemprice;
        TextView orderid=holder.orderid;
        TextView qty=holder.qty;
        ImageButton remove=holder.remove;

        final String name=data.get(position).itemname.toString();
        final String price=data.get(position).itemprice.toString();
        final String order_id=data.get(position).orderid.toString();
        final String qty1=data.get(position).qty.toString();

        c_itemname.setText(name);
        c_itemprice.setText(price);
        orderid.setText(order_id);
        qty.setText(qty1);


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = LoginActivity.weburl + "removecart.php";

                String email = getDefaults("userid", context);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", email)
                        .appendQueryParameter("itemname", name)
                        .appendQueryParameter("itemprice", price)
                        .appendQueryParameter("orderid",order_id);


                String query = builder.build().getEncodedQuery();
                Log.i("url", query);
               // Toast.makeText(context, "Item removed Successfully..!", Toast.LENGTH_SHORT).show();
                Async_Task a = new Async_Task(context, url, query, true);
                a.execute();
                //Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(context,CartActivity.class);
                context.startActivity(i);
            }

        });

        int intprice=Integer.parseInt(price);
        int intprice1=Integer.parseInt(price);
        total=total+intprice;

    }

    int grandTotal(ArrayList<CartItemDataModel> data){

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


    public static class CartItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView c_itemname;
        TextView c_itemprice;
        TextView orderid;
        ImageButton remove;
        TextView qty;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            this.c_itemname=(TextView)itemView.findViewById(R.id.c_itemname);
            this.c_itemprice=(TextView)itemView.findViewById(R.id.c_itemprice);
            this.orderid=(TextView)itemView.findViewById(R.id.orderid);
            this.remove=(ImageButton)itemView.findViewById(R.id.remove);
            this.qty=(TextView)itemView.findViewById(R.id.c_qty);

        }
    }
}
