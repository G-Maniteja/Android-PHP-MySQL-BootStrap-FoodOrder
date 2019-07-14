package com.example.manitejagande.foodorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MenuItemDataModel {
    public String itemname;
    public String itemprice;

    public MenuItemDataModel(JSONObject object) {

        try
        {
            this.itemname = object.getString("itemname");
            this.itemprice = object.getString("itemprice");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    //itemname and itemprice values retrieval from JSON Array..
    public static ArrayList<MenuItemDataModel> fromJson(JSONArray jsonObjects)
    {
        ArrayList<MenuItemDataModel> menuitems = new ArrayList<MenuItemDataModel>();
        for (int i = 0; i < jsonObjects.length(); i++)
        {
            try
            {
                menuitems.add(new MenuItemDataModel(jsonObjects.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return menuitems;
    }

}
