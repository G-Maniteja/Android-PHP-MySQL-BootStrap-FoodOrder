package com.example.manitejagande.foodorder;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.BreakIterator;

public class ProfileActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public static TextView u1;
    public static TextView e1;
    public static TextView r1;
    public static TextView p1;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

        u1=(TextView)findViewById(R.id.u1);
        e1=(TextView)findViewById(R.id.e1);
        r1=(TextView)findViewById(R.id.r1);
        p1=(TextView)findViewById(R.id.p1);
        e1.setText(MainActivity.getDefaults("userid",this));
       /* HttpURLConnection conn;
        URL url = null;
        StringBuilder result=null;


        String user = getDefaults("user",this);


        try {
            url = new URL("https://inamtginamtg.000webhostapp.com/getuserid.php");
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("email", user);
            String query = builder.build().getEncodedQuery();

            // Open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();



        int response_code = conn.getResponseCode();

            // Check if successful connection made
        if (response_code == HttpURLConnection.HTTP_OK) {

            // Read data sent from server
            InputStream input = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Pass data to onPostExecute method

        }else{

            result=new StringBuilder("unsuccessful");
        }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        if(result.toString()!="unsuccessful")
//        {
            try {
                JSONArray arr=new JSONArray(result);
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jo=(JSONObject)arr.get(i);
                    u1.setText(jo.getString("id"));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        //}

        fetchData process=new fetchData(this);
        process.execute();
        //u1.setText(process.data);

    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }


}
