package com.example.manitejagande.foodorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.manitejagande.foodorder.CartActivity.CONNECTION_TIMEOUT;
import static com.example.manitejagande.foodorder.CartActivity.READ_TIMEOUT;

public class CartActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    Context context=this;
    String response;
    ListView listView;
    String email;
    String oids;
    int total=0;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView.Adapter adapter1;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private static RecyclerView recyclerView;
    private static RecyclerView recyclerView1;
    Button placeorder;
    static TextView total_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        email=getDefaults("userid",this);

        total_text=(TextView)findViewById(R.id.totalprice);

        recyclerView = (RecyclerView) findViewById(R.id.recyclercart);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        placeorder=(Button)findViewById(R.id.placeorder);



        recyclerView1 = (RecyclerView) findViewById(R.id.cart_suggestion_recycler);
        recyclerView1.setHasFixedSize(true);

        layoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());


        //String url="http://192.168.0.2/foodorder/showcart.php?email="+email;


        FloatingActionButton ca_fab=(FloatingActionButton)findViewById(R.id.ca_fab);
        ca_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CartActivity.this,MainActivity.class);
                startActivity(i);
            }


        });

        AsyncGet a=new AsyncGet();
        a.execute();








    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }



    public class AsyncGet extends AsyncTask<String, String, String>
    {

        ProgressDialog pdLoading = new ProgressDialog(CartActivity.this);
        HttpURLConnection conn;
        URL url = null;
        String results="";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                // url = new URL("https://inamtginamtg.000webhostapp.com/showcart.php?email="+email);
                url = new URL(LoginActivity.weburl+"showcart.php?email="+email);
                Log.i("url1",url.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                //Uri.Builder builder = new Uri.Builder();

                //String query = builder.build().getEncodedQuery();
                Log.i("url2",url.toString());

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                //               writer.write(query);
                writer.write(url.toString());

                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                //if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                results=result.toString();
                response=results;



                Log.i("result",results);
                // Pass data to onPostExecute method
                return(result.toString());

//            }else{
//
//                return("unsuccessful");
//            }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayList<CartItemDataModel> itemslist = CartItemDataModel.fromJson(jsonArray);
            //CartAdapter adapter = new CartAdapter(CartActivity.this, itemslist);
            //adapter.addAll(itemslist);
            //listView.setAdapter(adapter);

            adapter=new CartItemAdapter(context,itemslist);
            recyclerView.setAdapter(adapter);

            //total=((CartItemAdapter) adapter).getTotal();


            if(result!=null)
            {

                Toast.makeText(CartActivity.this, "Items Fetched successfully", Toast.LENGTH_LONG).show();

            }else{

                // If username and password does not match display a error message
                Toast.makeText(CartActivity.this, "Fetch Failed.", Toast.LENGTH_LONG).show();

            }
            total=((CartItemAdapter)adapter).grandTotal(itemslist);
            CartActivity.total_text.setText(String.valueOf(total));
            oids=((CartItemAdapter)adapter).return_oids();
            placeorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String url="https://inamtginamtg.000webhostapp.com/currentorders.php";
                    String url=LoginActivity.weburl+"currentorders.php";

                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("oids", oids);


                    String query = builder.build().getEncodedQuery();
                    Log.i("items to current orders",query);
                    //Toast.makeText(context,"Item removed Successfully..!",Toast.LENGTH_SHORT);
                    Async_Task a=new Async_Task(context,url,query,true);
                    a.execute();
                    String r=a.getResult();
                    Intent i=new Intent(context,CurrentOrder.class);
                    startActivity(i);

                }
            });

            AsyncGet1 a1=new AsyncGet1();
            a1.execute();


        }
        String getjsonString()
        {
            return results;
        }



    }


    public class AsyncGet1 extends AsyncTask<String, String, String>
    {

        ProgressDialog pdLoading = new ProgressDialog(CartActivity.this);
        HttpURLConnection conn;
        URL url = null;
        String results="";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {
                // url = new URL("https://inamtginamtg.000webhostapp.com/showcart.php?email="+email);
                url = new URL(LoginActivity.weburl+"cartsuggestions.php?email="+email);
                Log.i("url1",url.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Log.i("url2",url.toString());
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(url.toString());
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                e1.printStackTrace();
                return "exception";
            }

            try {
                int response_code = conn.getResponseCode();
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                results=result.toString();
                response=results;
                Log.i("result",results);
                return(result.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();

            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayList<MenuItemDataModel> itemslist = MenuItemDataModel.fromJson(jsonArray);
            //CartAdapter adapter = new CartAdapter(CartActivity.this, itemslist);
            //adapter.addAll(itemslist);
            //listView.setAdapter(adapter);

            //data=MenuItemDataModel.fromJson(jsonArray);
            adapter1 = new MenuItemAdapterCart(context, itemslist);
            recyclerView1.setAdapter(adapter1);


            if (result != null) {

                Toast.makeText(CartActivity.this, "Items Fetched successfully", Toast.LENGTH_LONG).show();

            } else {

                // If username and password does not match display a error message
                Toast.makeText(CartActivity.this, "Fetch Failed.", Toast.LENGTH_LONG).show();

            }



        }
    }

}
