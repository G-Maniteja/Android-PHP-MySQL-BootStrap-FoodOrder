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

public class OrderHistoryRecyclerAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerAdapter.OrderHistoryRecyclerViewHolder>
{
    Context context;
    ArrayList<OrderHistoryDataModel> data;

    public OrderHistoryRecyclerAdapter(Context context,ArrayList<OrderHistoryDataModel> data) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public OrderHistoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderhistory_list_item, parent, false);
        OrderHistoryRecyclerViewHolder myViewHolder = new OrderHistoryRecyclerViewHolder(view);
        return myViewHolder;

    }
    public String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderHistoryRecyclerViewHolder holder, int position) {
        TextView orderid1=holder.orderid;
        TextView desc=holder.desc;
        TextView total1=holder.total;
        TextView status1=holder.status;

        final String orderid2 = data.get(position).orderid1.toString();
        final String desc2= data.get(position).desc.toString();
        final String total2 = data.get(position).total1.toString();
        final String status= data.get(position).status.toString();
        orderid1.setText(orderid2);
        desc.setText(desc2);
        total1.setText(total2);
        status1.setText(status);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class OrderHistoryRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderid;
        TextView desc;
        TextView total;
        TextView status;


        public OrderHistoryRecyclerViewHolder(View itemView) {
            super(itemView);
            this.orderid=(TextView)itemView.findViewById(R.id.orderid);
            this.desc=(TextView)itemView.findViewById(R.id.desc);
            this.total=(TextView)itemView.findViewById(R.id.total);
            this.status=(TextView)itemView.findViewById(R.id.status);

        }
    }
}
