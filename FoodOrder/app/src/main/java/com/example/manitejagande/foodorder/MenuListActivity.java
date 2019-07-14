package com.example.manitejagande.foodorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class MenuListActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    String response;
    ListView listView;
    Context context=this;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MenuItemDataModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_activity1);

        recyclerView = (RecyclerView) findViewById(R.id.menulistrecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AsyncGet obj=new AsyncGet();
        obj.execute();

/*        Log.i("a123456789","qwertyui");
// Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.list);


        //new AsyncGet().execute();
        AsyncGet obj=new AsyncGet();
        obj.execute();
        //response=obj.getjsonString();
        Log.i("a123456789","12");
//        JSONArray jsonArray = null;
//        try {
//            jsonArray = new JSONArray(response);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ArrayList<customer_menu_data> itemslist = customer_menu_data.fromJson(jsonArray);
//        UsersAdapter adapter = new UsersAdapter(this, itemslist);
//        adapter.addAll(itemslist);
//        listView.setAdapter(adapter);

*/
        FloatingActionButton m_fab=(FloatingActionButton)findViewById(R.id.m_fab);
        m_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MenuListActivity.this,MainActivity.class);
                startActivity(i);
            }


        });


    }



    private class AsyncGet extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(MenuListActivity.this);
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
               // url = new URL("https://inamtginamtg.000webhostapp.com/menu.php");
                url = new URL(LoginActivity.weburl+"menu.php");

                Log.i("url1",url.toString());

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

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

                }else{

                    return("unsuccessful");
                }

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
            /*ArrayList<customer_menu_data> itemslist = customer_menu_data.fromJson(jsonArray);
            UsersAdapter adapter = new UsersAdapter(MenuListActivity.this, itemslist);
            //adapter.addAll(itemslist);
            listView.setAdapter(adapter);*/

            data=MenuItemDataModel.fromJson(jsonArray);
            adapter=new MenuItemAdapter(context,data);
            recyclerView.setAdapter(adapter);


            if(result!=null)
            {

                Toast.makeText(MenuListActivity.this, "Items Fetched successfully", Toast.LENGTH_LONG).show();

            }else{

                // If username and password does not match display a error message
                Toast.makeText(MenuListActivity.this, "Fetching Failed.", Toast.LENGTH_LONG).show();

            }
        }
        String getjsonString()
        {
            return results;
        }

    }

}
