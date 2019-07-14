package com.example.manitejagande.foodorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class Async_Task extends AsyncTask<String, String, String> {
    Context context;
    String str_url;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    String query;
    boolean show;
    String r;

    ProgressDialog pdLoading;
    HttpURLConnection conn;
    URL url = null;
    Async_Task(Context context,String url, String query,boolean show)
    {
        this.context=context;
        str_url=url;
        this.query=query;
        this.show=show;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread
        if(show) {
            pdLoading = new ProgressDialog(context);
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }
    }



    @Override
    protected String doInBackground(String... params) {

        try
        {
            // Enter URL address where your php file resides
            url = new URL(str_url);
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "exception";
        }
        try
        {
            // Setup HttpURLConnection class to send and receive data from php and mysql
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");

            // setDoInput and setDoOutput method depict handling of both send and receive
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "exception";
        }

        try
        {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK)
            {
                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                Log.i("a13q345",result.toString());
                // Pass data to onPostExecute method
                return(result.toString());

            }else{

                return("unsuccessful");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "exception";
        }
        finally
        {
            conn.disconnect();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        r=result;

        //this method will be running on UI thread
        if(show) {
            pdLoading.dismiss();
        }

        if(result!=null)
        {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */


            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();



        }else{

            // If username and password does not match display a error message
            Toast.makeText(context, "unsuccessful", Toast.LENGTH_SHORT).show();

        }
    }
    String getResult()
    {
        return r;
    }

}
