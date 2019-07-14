package com.example.manitejagande.foodorder;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<customer_menu_data> {
    Context context;
    public UsersAdapter(Context context, ArrayList<customer_menu_data> users) {
        super(context, 0, users);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        customer_menu_data customerMenuData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tv1 = (TextView) convertView.findViewById(R.id.itemname);
        TextView tv2 = (TextView) convertView.findViewById(R.id.itemprice);
        Button b=(Button)convertView.findViewById(R.id.additem);
        // Populate the data into the template view using the data object
        tv1.setText(customerMenuData.itemname);
        tv2.setText(customerMenuData.itemprice);
        final String name=customerMenuData.itemname;
        final String price=customerMenuData.itemprice;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://inamtginamtg.000webhostapp.com/cart.php";
                String email=getDefaults("userid",context);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", email)
                        .appendQueryParameter("itemname", name)
                        .appendQueryParameter("itemprice", price);


                String query = builder.build().getEncodedQuery();
                Log.i("url",query);

                Async_Task a=new Async_Task(context,url,query,false);
                a.execute();

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
    public String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
