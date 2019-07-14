package com.example.manitejagande.foodorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class customer_menu_data {
    public String itemname;
    public String itemprice;

    public customer_menu_data(JSONObject object) {
        try {
            this.itemname = object.getString("itemname");
            this.itemprice = object.getString("itemprice");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<customer_menu_data> fromJson(JSONArray jsonObjects) {
        ArrayList<customer_menu_data> menuitems = new ArrayList<customer_menu_data>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                menuitems.add(new customer_menu_data(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return menuitems;
    }

}
