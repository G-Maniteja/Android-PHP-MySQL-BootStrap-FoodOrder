package com.example.manitejagande.foodorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class registerActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public static EditText r_username;
    public static EditText r_email;
    public static EditText r_phone;
    public static EditText r_password;
    public static EditText r_dob;
//    public static RadioButton r_customer;
//    public static RadioButton r_manager;
//    public static String r_role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        r_username = (EditText) findViewById(R.id.r_username);
        r_email = (EditText) findViewById(R.id.r_email);
        r_phone = (EditText) findViewById(R.id.r_phone);
        r_password = (EditText) findViewById(R.id.r_password);
        r_dob=(EditText)findViewById(R.id.r_dob);



        Button reg=(Button)findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("q145432","entered into register method");
                new AsyncRegister().execute(r_username.getText().toString(),r_email.getText().toString(),r_phone.getText().toString(),r_password.getText().toString(),r_dob.getText().toString());
                Log.i("q12345","Async register completed");
            }
        });

    }

    public void registerUser() {
        Log.i("q145432","entered into register method");
        new AsyncRegister().execute(r_username.getText().toString(),r_email.getText().toString(),r_phone.getText().toString(),r_password.getText().toString(),r_dob.getText().toString());
        Log.i("q12345","Async register completed");
    }

    private class AsyncRegister extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(registerActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            Log.i("w134","before progress dialog");
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
            Log.i("w1324","after progress dialog");


        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                //url = new URL("https://inamtginamtg.000webhostapp.com/register.php");
                url = new URL(LoginActivity.weburl+"register.php");


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("email", params[1])
                        .appendQueryParameter("phone", params[2])
                        .appendQueryParameter("password", params[3])
                        .appendQueryParameter("dob", params[4]);

                String query = builder.build().getEncodedQuery();
                Log.i("url",query);

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
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

                    line = reader.readLine();
                    result=new StringBuilder(line);
//                    while ((line = reader.readLine()) != null) {
//                        result.append(line);
//                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
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

            if (result.equalsIgnoreCase("successful")) {


                Toast.makeText(registerActivity.this, "Register successfull", Toast.LENGTH_LONG).show();


                Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                startActivity(intent);
                registerActivity.this.finish();

            }
            else
            {
                Toast.makeText(registerActivity.this, "Register unsuccessfull", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                startActivity(intent);
                registerActivity.this.finish();
            }
        }
    }
}