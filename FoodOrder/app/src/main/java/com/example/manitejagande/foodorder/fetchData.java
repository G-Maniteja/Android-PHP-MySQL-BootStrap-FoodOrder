package com.example.manitejagande.foodorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data="";
    String un="";
    String em="";
    String ro="";
    String ph="";
    ProgressDialog pdLoading;

    Context c=null;
    fetchData(Context d)
    {
        c=d;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pdLoading = new ProgressDialog(c);
        //this method will be running on UI thread
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();

    }
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            em=getDefaults("userid",c);
            //URL url=new URL("https://inamtginamtg.000webhostapp.com/getuserid.php?email="+em);
            URL url=new URL(LoginActivity.weburl+"getuserid.php?email="+em);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null)
            {
                line=bufferedReader.readLine();
                data=data+line;
            }
//            Log.i("a1111111",data+"zxcv");
//            Log.i("qwerty","asdf");

            JSONObject jsonObject=new JSONObject(data);
            un=jsonObject.get("username").toString();
            ro=jsonObject.get("dob").toString();
            ph=jsonObject.get("phone").toString();




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        pdLoading.dismiss();
        super.onPostExecute(aVoid);
        ProfileActivity.u1.setText(this.un);
        ProfileActivity.r1.setText(this.ro);
        ProfileActivity.p1.setText(this.ph);
    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
