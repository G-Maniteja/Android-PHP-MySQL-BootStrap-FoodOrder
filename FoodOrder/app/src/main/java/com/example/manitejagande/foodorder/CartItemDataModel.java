package com.example.manitejagande.foodorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class CartItemDataModel {
    public String itemname;
    public String itemprice;
    public String orderid;
    public String qty;

    public CartItemDataModel(JSONObject object) {

        try
        {
            this.itemname = object.getString("itemname");
            this.itemprice = object.getString("itemprice");
            this.orderid =object.getString("orderid");
            this.qty=object.getString("qty");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    //itemname and itemprice values retrieval from JSON Array..
    public static ArrayList<CartItemDataModel> fromJson(JSONArray jsonObjects)
    {
        ArrayList<CartItemDataModel> cartitems = new ArrayList<CartItemDataModel>();
        for (int i = 0; i < jsonObjects.length(); i++)
        {
            try
            {
                cartitems.add(new CartItemDataModel(jsonObjects.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return cartitems;
    }

}
