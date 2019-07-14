package com.example.manitejagande.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


public class SuggestedActivity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Context context = this;
    String response;
    String email;
   // String data = "", str;
    // String[][] dataarray;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<MenuItemDataModel> data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested);


        recyclerView = (RecyclerView) findViewById(R.id.suggested_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        SuggestedActivity.AsyncGet1 a1 = new SuggestedActivity.AsyncGet1();
        a1.execute();

//        SuggestedActivity.AsyncGet a = new SuggestedActivity.AsyncGet();
//        a.execute();

    }

    private class AsyncGet1 extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(SuggestedActivity.this);
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
                url = new URL(LoginActivity.weburl+"get_suggested.php");

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

                Toast.makeText(SuggestedActivity.this, "Items Fetched successfully", Toast.LENGTH_LONG).show();

            }else{

                // If username and password does not match display a error message
                Toast.makeText(SuggestedActivity.this, "Fetching Failed.", Toast.LENGTH_LONG).show();

            }
        }
        String getjsonString()
        {
            return results;
        }

    }



    /*public class AsyncGet extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(SuggestedActivity.this);
        HttpURLConnection conn;
        URL url = null;
        URL url1 = null;
        String results = "";


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

                //url1=new URL(LoginActivity.weburl+"get_suggested.php");
                //url = new URL("https://inamtginamtg.000webhostapp.com/displaycurrentorders.php?email="+email);
                url = new URL(LoginActivity.weburl + "freqItemsets.txt");
                //url1 = new URL(LoginActivity.weburl+"associationRules.txt");

                Log.i("url1", url.toString());

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {


                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                //int i=0;
                while ((str = in.readLine()) != null) {
                    data = data + "\n" + str;
                    // dataarray[i]=new String[10];
                    //  dataarray[i]=str.split(",");
                    //i++;

                }


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }


            return (data);

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            String data1 = data.replaceAll(",", " + ");
            //String data1="";
//            for(int i=0;i<dataarray.length;i++)
//            {
//                for(int j=0;j<dataarray[i].length;j++)
//                {
//                    data1=data1+dataarray[i][j];
//                }
//            }


            TextView tv = (TextView) findViewById(R.id.suggested);
            tv.setText(data1);
            if (result != null) {
                Toast.makeText(SuggestedActivity.this, "Items Fetched successfully", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(SuggestedActivity.this, "Fetch Failed.", Toast.LENGTH_LONG).show();
            }


        }

        String getjsonString() {
            return results;
        }


    }*/

}

