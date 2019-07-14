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

public class CartAdapter extends ArrayAdapter<customer_menu_data> {
    public static int total=0;
    Context context;
    public CartAdapter(Context context, ArrayList<customer_menu_data> users) {
        super(context, 0, users);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        customer_menu_data customerMenuData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_cart_item, parent, false);
        }
        // Lookup view for data population
        TextView tv1 = (TextView) convertView.findViewById(R.id.c_itemname);
        TextView tv2 = (TextView) convertView.findViewById(R.id.c_itemprice);
       // TextView tv3 = (TextView) convertView.findViewById(R.id.orderid);
        //TextView tv3=  (TextView) convertView.findViewById(R.id.c_itemquantity);
        // Populate the data into the template view using the data object
        tv1.setText(customerMenuData.itemname);
        tv2.setText(customerMenuData.itemprice);
        //tv3.setText();
//        final String name=customerMenuData.itemname;
//        final String price=customerMenuData.itemprice;
//        int p=Integer.parseInt(price);
//        total=total+p;
        /*b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://192.168.0.2/foodorder/menu.php";
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
        });*/
        // Return the completed view to render on screen
        return convertView;
    }
    public String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
    public int gettotal()
    {
        return total;
    }
}
